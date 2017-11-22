package cerbere.demo;

import i2.application.cerbere.commun.CerbereUtilisateurException;
import i2.application.cerbere.commun.Entite;
import i2.application.cerbere.commun.Utilisateur;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Gestionnaire des actions sur l'application démo.
 */
public class ActionServlet extends BaseActionServlet {
    /** Identifiant de version */
    private static final long serialVersionUID = 1L;
    /** Paramètre descriptif des actions */
    private final String P_ACTION = "action";
    /** Action de retour à l'accueil */
    private final int DO_HOME = 0;
    /** Action de recherche par service */
    private final int DO_SEARCH_UNIT = 10;
    /** Action de recherche par profils */
    private final int DO_SEARCH_PROFIL=11;
    /** Action de reconnexion */
    private final int DO_RELOGIN = 2;
    /** Action de reconnexion */
    private final int DO_LOGOUT = 3;
    /** Action de detail utilisateur */
    private final int DO_USERINFO = 12;
    /** Action de en-têtes de a requête */
    private final int DO_HEADERS = 14;
    /** Action de en-têtes de a requête */
    private final int DO_CERTIFICAT = 15;
    /** Gestionnaire de traces */
    private static Logger logger = Logger.getLogger(ActionServlet.class);
    /** Mode développeur */
    private boolean isModeDev = false;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.isModeDev = "1".equals(config.getServletContext().getInitParameter("mode.dev"));
    }
        
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        // Récupération de l'action exécutée.
        int action;
        try {
            action = Integer.parseInt(request.getParameter(P_ACTION));
        } catch (NumberFormatException e) {
            action = DO_HOME;
        }

        // Utilisateur courant.
        User usr = (User) request.getSession().getAttribute("user");

        // Marquer le mode développeur pour gestion de l'affichage.
        if(isModeDev) {
            request.setAttribute("modeDev", isModeDev);
        }
        
        // Aiguillage vers l'action selectionné.
        switch(action) {
            case DO_LOGOUT :
                usr.deconnexion(request, response);
                break;
            case DO_RELOGIN :
                usr.reconnexion(request, response, "index.html");
                break;
            case DO_HEADERS :
                doUserHeaders(request, response);
                break;
            case DO_CERTIFICAT :
                doUserCertificate(request, response, usr);
                break;
            case DO_SEARCH_UNIT :
                if(!isModeDev) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Mode développeur inactif");
                    return;
                }
                doSearchUnit(request, response, usr);
                break;
            case DO_SEARCH_PROFIL :
                if(!isModeDev) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Mode développeur inactif");
                    return;
                }
                doSearchProfil(request, response, usr);
                break;
            case DO_USERINFO :
                if(!isModeDev) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Mode développeur inactif");
                    return;
                }
                doUserInfo(request, response, usr);
                break;
            default:
                doHome(request, response);
        }
    }

    /**
     * Page d'accueil, informations sur l'utilisateur.
     * 
     * @param request requête HTTP courante.
     * @param response réponse HTTP courante.
     */
    public void doHome(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/index.jsp");
        rd.forward(request, response);
    }
        
    /**
     * Formulaires de recherche d'unités.
     * 
     * @param request Requête HTTP courante.
     * @param response Réponse HTTP courante.
     * @param usr Utilisateur courant.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void doSearchUnit(HttpServletRequest request,
            HttpServletResponse response, User usr) throws ServletException, IOException {

        String alerte = "";
        String service = request.getParameter("service");
        boolean recursif = ("1".equals(request.getParameter("recursif")));
        List personnes =  new ArrayList();
        List services = new ArrayList();
        if((service != null) && (service.length() != 0)) {
            try {
                Entite e = usr.getCerbere().findEntite(service);
                if(e == null) {
                    alerte = "Entitée inconnue : " + service;
                } else {
                   personnes = e.findPersonnes(recursif);
                   Iterator iter = e.findSousEntites().iterator();
                   while(iter.hasNext()) {
                       Entite se = (Entite) iter.next();
                       Map<String, String> infos = new HashMap<String, String>();
                       infos.put("UNITE", se.getValeur(Entite.UNITE));
                       services.add(infos);
                   }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                alerte = e.getMessage();
            }
        }
        request.setAttribute("personnes", personnes);
        request.setAttribute("services", services);
        request.setAttribute("alerte", alerte);

        // Affichage de la réponse.
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/recherche.jsp");
        rd.forward(request, response);
    }
        
    /**
     * Recherche d'utilisateurs.
     * 
     * @param request Requête HTTP courante.
     * @param response Réponse HTTP courante.
     * @param usr Utilisateur courant.
     */
    @SuppressWarnings("rawtypes")
    public void doSearchProfil(HttpServletRequest request,
            HttpServletResponse response, User usr) throws ServletException, IOException {

        String alerte = "";
        String entite = request.getParameter("entite");
        String profil = request.getParameter("profil");
        String restriction = request.getParameter("restriction");
        boolean recursif = "1".equals(request.getParameter("recursif"));
        List utilisateurs = new ArrayList();
        try {
            utilisateurs = usr.getCerbere().findUtilisateurs(entite, recursif, profil, restriction);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            alerte = e.getMessage();
        }
        request.setAttribute("utilisateurs", utilisateurs);
        request.setAttribute("alerte", alerte);

        // affichage
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/recherche.jsp");
        rd.forward(request, response);
    }

    /**
     * Informations utilisateur de recherche utilisateur.
     * 
     * @param request Requête HTTP courante.
     * @param response Réponse HTTP courante.
     * @param usr Utilisateur courant.
     */
    public void doUserInfo(HttpServletRequest request,
            HttpServletResponse response, User usr) throws ServletException, IOException {

        String alerte = "";
        Utilisateur userInfos = null;
        String mel = request.getParameter("mel");
        if((mel != null) && (mel.trim().length() != 0)) {
            try {
                userInfos = usr.getCerbere().findUtilisateurByMel(mel);
                if(userInfos == null) {
                    throw new Exception("Utilisateur inconnu ou sans profil dans l'application : " + mel);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                alerte = e.getMessage();
            }
        }
        request.setAttribute("userinfos", userInfos);
        request.setAttribute("alerte", alerte);

        // affichage
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/infos-utilisateur.jsp");
        rd.forward(request, response);
    }

    /**
     * Affichage des en-têtes HTTP de la requête
     * 
     * @param request Requête HTTP courante.
     * @param response Réponse HTTP courante.
     */
    public void doUserHeaders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // affichage
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/en-tetes.jsp");
        rd.forward(request, response);
    }

    /**
     * Transmission du certificat utilisateur.
     * 
     * @param request Requête HTTP courante.
     * @param response Réponse HTTP courante.
     * @param usr Utilisateur courant.
     */
    public void doUserCertificate(HttpServletRequest request,
            HttpServletResponse response, User usr) throws ServletException, IOException {
    
        try {
            X509Certificate x509 = usr.getCerbere().getUtilisateur().getCertificat();
            if(x509 == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Certificat absent");
            }
            // Le nom du fichier téléchargé : Prenom.Nom.crt
            Utilisateur user = usr.getCerbere().getUtilisateur();
            String crtName = String.format("%s.%s.crt", user.getPrenom(), user.getNom());
            crtName = crtName.replaceAll("[^a-zA-Z0-9\\.]", "-");

            byte[] x509ASN1 = x509.getEncoded();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + crtName);
            
            ServletOutputStream outstr = response.getOutputStream();
            outstr.write(x509ASN1);
            outstr.flush();            
        } catch (CerbereUtilisateurException e) {
            throw new ServletException("Erreur Cerbère : " + e.getMessage(), e);
        } catch (CertificateEncodingException e) {
            throw new ServletException("Erreur de certificat : " + e.getMessage(), e);
        }
        
    }
}

