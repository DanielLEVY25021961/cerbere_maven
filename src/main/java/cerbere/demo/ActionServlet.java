package cerbere.demo;

import i2.application.cerbere.commun.CerbereUtilisateurException;
import i2.application.cerbere.commun.Entite;
import i2.application.cerbere.commun.Utilisateur;

import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gestionnaire des actions sur l'application démo.
 */
public class ActionServlet extends BaseActionServlet {
    /** Identifiant de version */
    private static final long serialVersionUID = 1L;
    /** Paramètre descriptif des actions */
    private static final String P_ACTION = "action";
    /** Action de retour à l'accueil */
    private static final int DO_HOME = 0;
    /** Action de recherche par service */
    private static final int DO_SEARCH_UNIT = 10;
    /** Action de recherche par profils */
    private static final int DO_SEARCH_PROFIL=11;
    /** Action de reconnexion */
    private static final int DO_RELOGIN = 2;
    /** Action de reconnexion */
    private static final int DO_LOGOUT = 30;
    /** Action de reconnexion */
    private static final int DO_LOGOUT_REDIRECT = 31;
    /** Action de detail utilisateur */
    private static final int DO_USERINFO = 12;
    /** Action de en-têtes de a requête */
    private static final int DO_DIVERS = 14;
    /** Action de en-têtes de a requête */
    private static final int DO_CERTIFICAT = 15;
    /** Action de demande de date */
    private static final int DO_TIME = 16;
    /** URL de redirection après authentification */
    private static final String LOGOUT_URL = "http://www.developpement-durable.gouv.fr";
    /** Gestionnaire de traces */
    private static final Logger LOG = LoggerFactory.getLogger(ActionServlet.class);
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
        if(this.isModeDev) {
            request.setAttribute("modeDev", this.isModeDev);
        }
        
        // Aiguillage vers l'action selectionné.
        switch(action) {
            case DO_LOGOUT :
                usr.deconnexion(request, response);
                break;
            case DO_LOGOUT_REDIRECT :
                usr.deconnexion(request, response, LOGOUT_URL);
                break;
            case DO_RELOGIN :
                usr.reconnexion(request, response, "index.jsp");
                break;
            case DO_DIVERS :
                doDivers(request, response);
                break;
            case DO_CERTIFICAT :
                doUserCertificate(request, response, usr);
                break;
            case DO_SEARCH_UNIT :
                if(!this.isModeDev) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Mode développeur inactif");
                    return;
                }
                doSearchUnit(request, response, usr);
                break;
            case DO_SEARCH_PROFIL :
                if(!this.isModeDev) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Mode développeur inactif");
                    return;
                }
                doSearchProfil(request, response, usr);
                break;
            case DO_USERINFO :
                if(!this.isModeDev) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Mode développeur inactif");
                    return;
                }
                doUserInfo(request, response, usr);
                break;
            case DO_TIME :
                doTime(request, response);
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
     * @throws ServletException 
     * @throws IOException 
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
     * @throws ServletException 
     * @throws IOException 
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
                LOG.error(e.getMessage(), e);
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
     * @throws ServletException 
     * @throws IOException 
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
            LOG.error(e.getMessage(), e);
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
     * @throws ServletException 
     * @throws IOException 
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
                LOG.error(e.getMessage(), e);
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
     * @throws ServletException 
     * @throws IOException 
     */
    public void doDivers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // affichage
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vues/divers.jsp");
        rd.forward(request, response);
    }

    /**
     * Transmission du certificat utilisateur.
     * 
     * @param request Requête HTTP courante.
     * @param response Réponse HTTP courante.
     * @param usr Utilisateur courant.
     * @throws ServletException 
     * @throws IOException 
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

            if (x509 != null) {
            	
                byte[] x509ASN1 = x509.getEncoded();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename=" + crtName);
                
                ServletOutputStream outstr = response.getOutputStream();
                outstr.write(x509ASN1);
                outstr.flush();                      	
            }
            
        } catch (CerbereUtilisateurException e) {
            throw new ServletException("Erreur Cerbère : " + e.getMessage(), e);
        } catch (CertificateEncodingException e) {
            throw new ServletException("Erreur de certificat : " + e.getMessage(), e);
        }
        
    }
    
    /**
     * Transmission de la date et heure.
     * 
     * @param request Requête HTTP courante.
     * @param response Réponse HTTP courante.
     * @throws ServletException 
     */
    private void doTime(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        if(request.getParameter("time") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String time = sdf.format(new Date());
            response.setContentType("text/plain");
            response.getWriter().print(time);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}

