package levy.daniel.application.controllers.web.metier.authentification;

import i2.application.cerbere.commun.Cerbere;
import i2.application.cerbere.commun.CerbereConnexionException;
import i2.application.cerbere.commun.Entreprise;
import i2.application.cerbere.commun.Profil;
import levy.daniel.application.model.metier.authentification.impl.CerbereUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class AuthentificationServlet :<br/>
 * SERVLET chargée d'accueillir l'utilisateur en sortie du filtre
 * Cerbere.<br/>
 * - Permet à l'internaute de choisir entre plusieurs profils
 * si son habilitation le lui permet.<br/>
 * - Met en Session un Objet CerbereUser (plus petit que Cerbere)
 * suffisant pour la gestion des droits dans l'application.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 4 mars 2013
 *
 */
public class AuthentificationServlet extends HttpServlet {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(AuthentificationServlet.class);

	// *************************METHODES************************************/
	
	
	/**
	 * method doGet(
	 * HttpServletRequest pRequest
	 * , HttpServletResponse pResponse) :<br/>
	 * Intercepte et traite les requêtes GET.<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête traitée.<br/>
	 * @param pResponse : HttpServletResponse.<br/>
	 * 
	 * @throws ServletException : lorsque problème de forward.<br/>
	 * @throws IOException : lorsque problème du PrintWriter pour créer
	 * une réponse HTTP.<br/>
	 */
	@Override
	protected final void doGet(
			final HttpServletRequest pRequest
				, final HttpServletResponse pResponse)
			throws ServletException, IOException {
		
		Cerbere cerbere = null;
		List<Profil> listeProfils = null;
		
		/* Tentative de création de l'objet Cerbere. */
		try {
			
			cerbere = this.creerCerbere(pRequest);
			
		} catch (CerbereConnexionException e) {
			
			if (LOG.isErrorEnabled()) {
				LOG.error("Impossible de créer l'objet Cerbere : ", e);
			}
			
			throw new ServletException(
					"Impossible de créer l'objet Cerbere : ", e);
		}

		
		/* ************** TRAITEMENT DES ERREURS ********************* */
		
		/* Cerbere null. */
		if (cerbere == null) {
			
			final String message 
			= "Objet Cerbere null";
			
			if (LOG.isErrorEnabled()) {
				LOG.error(message);
			}
			
			throw new ServletException(message);
			
		}
		
		/* Si pas d'habilitation. */
		if (cerbere.getHabilitation() == null) {
			
			final String message 
			= "Impossible d'obtenir l'habilitation dans l'objet Cerbere";
			
			if (LOG.isErrorEnabled()) {
				LOG.error(message);
			}
			
			throw new ServletException(message);
		}
		
		/* Si pas de profils. */
		if (cerbere.getHabilitation().getTousProfils() == null) {
			
			final String message 
			= "Impossible d'obtenir les profils dans l'objet Cerbere";
			
			if (LOG.isErrorEnabled()) {
				LOG.error(message);
			}
			
			throw new ServletException(message);
		}
		

		/* Si pas d'utilisateur. */
		if (cerbere.getUtilisateur() == null) {
			
			final String message 
			= "Impossible d'obtenir l'Utilisateur dans l'objet Cerbere";
			
			if (LOG.isErrorEnabled()) {
				LOG.error(message);
			}
			
			throw new ServletException(message);

		}

		/* ************** TRAITEMENT NORMAL ********************* */
		
		pRequest.getSession().setAttribute("cerbere", cerbere);
		
		/* RECUPERATION DE LA LISTE DES PROFILS. ************/
		listeProfils 
		= cerbere.getHabilitation().getTousProfils();

		
		/* ***************PREMIER ACCES ********************************/
		/* Si la requête n'a pas de paramètres (AVANT sélection profil)*/
		if (StringUtils.isBlank(pRequest.getQueryString())) {

			
			/* ****PLUSIEURS PROFILS ****************/
			/* Si l'internaute a plusieurs profils. */
			if (listeProfils.size() > 1) {
				
				/* Mise en requête de la liste des profils. */
				pRequest.setAttribute("listeProfils", listeProfils);
				
				/* Forward vers la page de SELECTION DU PROFIL. */
				RequestDispatcher requestDispatcherProfil = null;
				
				requestDispatcherProfil 
				= pRequest.getRequestDispatcher(
						"/WEB-INF/jsp/application/formSelectionProfil.jsp");
				
				requestDispatcherProfil.forward(pRequest, pResponse);
			}
			
			
			/* *********UN SEUL PROFIL ****************/
			/* Si l'internaute n'a qu'un seul profil. */
			else {
				
				Entreprise entreprise = null;
				String entrepriseString = null;
				
				if (cerbere.getEntreprise() != null) {
					entreprise = cerbere.getEntreprise();
					entrepriseString = entreprise.getNom();
				}
				
				
				/* Instanciation du cerbereUser qui encapsule les données
				 * nécessaires à la gestion des droits. */
								
				final CerbereUser cerbereUser 
				= this.creerCerbereUser(
						cerbere.getUtilisateur().getIdentifiant()
						, cerbere.getUtilisateur().getMel()
						, cerbere.getUtilisateur().getCivilite()
						, cerbere.getUtilisateur().getPrenom()
						, cerbere.getUtilisateur().getNom()
						, cerbere.getUtilisateur().getUnite()
						, entrepriseString
						, cerbere.getHabilitation()
							.getTousProfils().get(0).getNom()
						, cerbere.getHabilitation()
							.getTousProfils().get(0).getPortee()
						, cerbere.getHabilitation()
							.getTousProfils().get(0).getRestriction());
				
				/* Mise en session du CerbereUser. */
				pRequest.getSession()
					.setAttribute("cerbereUser", cerbereUser);
				
				/* Forward vers la page d'ACCUEIL. */
				RequestDispatcher requestDispatcherAccueil = null;
				
				requestDispatcherAccueil 
			    = pRequest.getRequestDispatcher(
			    		"/WEB-INF/jsp/application/accueil.jsp");
			    	    
			    if (LOG.isInfoEnabled()) {
			    	LOG.info("Servlet AuthentificationServlet - doGet() : " 
			    			+ cerbere.getUtilisateur().getMel());
			    }
			    
			    requestDispatcherAccueil.forward(pRequest, pResponse);
		        
			}
		}
		
		/* SI RECUPERATION DU PROFIL CHOISI (cas où plusieurs profils). */
		else {
			
			/* Récupération du paramètre "choixProfil" fourni dans la requête
			 * provenant du formulaire de sélection du profil. */
			final String choixProfil = pRequest.getParameter("choixProfil");
			
			try {
				
				final Integer indexProfil = Integer.valueOf(choixProfil);
				
				final Profil profilChoisi = listeProfils.get(indexProfil);
				
				Entreprise entreprise = null;
				String entrepriseString = null;
				
				if (cerbere.getEntreprise() != null) {
					entreprise = cerbere.getEntreprise();
					entrepriseString = entreprise.getNom();
				}
				
				/* Instanciation du cerbereUser qui encapsule les données
				 * nécessaires à la gestion des droits. */
				
				final CerbereUser cerbereUser 
				= this.creerCerbereUser(
						cerbere.getUtilisateur().getIdentifiant()
						, cerbere.getUtilisateur().getMel()
						, cerbere.getUtilisateur().getCivilite()
						, cerbere.getUtilisateur().getPrenom()
						, cerbere.getUtilisateur().getNom()
						, cerbere.getUtilisateur().getUnite()
						, entrepriseString
						, profilChoisi.getNom()
						, profilChoisi.getPortee()
						, profilChoisi.getRestriction());
				
				/* Mise en session du CerbereUser. */
				pRequest.getSession()
					.setAttribute("cerbereUser", cerbereUser);
				
				/* Forward vers la page d'accueil. */
				RequestDispatcher requestDispatcherAccueil = null;
				
				requestDispatcherAccueil 
			    = pRequest.getRequestDispatcher(
			    		"/WEB-INF/jsp/application/accueil.jsp");
			    	    
			    if (LOG.isInfoEnabled()) {
			    	LOG.info("Servlet AuthentificationServlet - doGet() : " 
			    			+ cerbere.getUtilisateur().getMel());
			    }
			    
			    requestDispatcherAccueil.forward(pRequest, pResponse);
			    
			} catch (NumberFormatException e) {
				
				final String message 
				= "Le numéro de profil choisi devrait être entier";
				
				if (LOG.isErrorEnabled()) {
					LOG.error(message, e);
				}
				
				throw new ServletException(message, e);

			}
			
		}
		
				
	} // Fin de doGet(
	 // HttpServletRequest pRequest
	 // , HttpServletResponse pResponse).__________________________________
	
	

	/**
	 * method doPost(
	 * HttpServletRequest pRequest
	 * , HttpServletResponse pResponse) :<br/>
	 * Intercepte et traite les requêtes POST.<br/>.<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête traitée.<br/>
	 * @param pResponse : HttpServletResponse.<br/>
	 * 
	 * @throws ServletException : lorsque problème de forward.<br/>
	 * @throws IOException : lorsque problème du PrintWriter pour créer
	 * une réponse HTTP.<br/>
	 */
	@Override
	protected final void doPost(
			final HttpServletRequest pRequest
				, final HttpServletResponse pResponse)
			throws ServletException, IOException {
		
		System.out.println("doPost");
		this.doGet(pRequest, pResponse);
		
	} // Fiin de doPost(
	 // HttpServletRequest pRequest
	 // , HttpServletResponse pResponse).__________________________________


	
	/**
	 * method creerCerbere(
	 * HttpServletRequest pRequest) :<br/>
	 * Crée un i2.application.cerbere.commun.Cerbere.<br/>
	 * Cet objet de l'API Cerbere intercepte la requête de Login
	 * de l'internaute, la passe au Serveur d'authentification CERBERE,
	 * puis rapatrie les informations concernant l'utilisateur
	 * encapsulées dans l'objet "cerbere" en attribut.<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête de Login
	 * de l'Internaute.<br/>
	 * @return cerbere : i2.application.cerbere.commun.Cerbere :
	 *  Objet contenant les informations de l'internaute.<br/>
	 * @throws CerbereConnexionException : Si problème pendant la connexion
	 * au serveur d'authentification.<br/>
	 */
	public final Cerbere creerCerbere(
			final HttpServletRequest pRequest) 
			throws CerbereConnexionException {
		
		return Cerbere.creation(pRequest);
		
	} // Fin de creerCerbere(
	 // HttpServletRequest pRequest).______________________________________


	
	/**
	 * method creerCerbereUser(...) :<br/>
	 * Crée un objet encapsulant toutes les données provenant
	 * de Cerbere nécessaires à la gestion des droits.<br/>
	 * <br/>
	 *
	 * @param pIdCerbere : String : Identifiant métier 
	 * Cerbere de l'internaute.<br/>
	 * @param pMel : String : mel de l'internaute.<br/>
	 * @param pCivilite : String : Civilité de l'internaute.<br/>
	 * @param pPrenom : String : Prénom de l'internaute.<br/>
	 * @param pNom : String : Nom de l'internaute.<br/>
	 * @param pUnite : String : Unité (service) de l'internaute.<br/>
	 * @param pEntreprise : String : Entreprise de l'internaute.<br/>
	 * @param pNomProfil : String : Nom du profil de l'internaute.<br/>
	 * @param pPorteeProfil : String : Portée du profil de l'internaute.<br/>
	 * @param pRestrictionProfil : String : Restriction du profil de 
	 * l'internaute.<br/>
	 * 
	 * @return CerbereUser.<br/>
	 */
	public final CerbereUser creerCerbereUser(
			final String pIdCerbere
			, final String pMel
			, final String pCivilite
			, final String pPrenom
			, final String pNom
			, final String pUnite
			, final String pEntreprise
			, final String pNomProfil
			, final String pPorteeProfil
			, final String pRestrictionProfil) {
		
		final CerbereUser cerbereUser 
		= new CerbereUser(pIdCerbere
				, pMel
				, pCivilite
				, pPrenom
				, pNom
				, pUnite
				, pEntreprise
				, pNomProfil
				, pPorteeProfil
				, pRestrictionProfil);
		
		return cerbereUser;
		
	} // Fin de creerCerbereUser(...)._____________________________________
	

	
	/**
	 * method recupererListeAttributsRequete(
	 * HttpServletRequest pRequest) :<br/>
	 * - Récupère la liste des noms des attributs de la requête.<br/>
	 * - Fournit une liste HTML avec à chaque ligne 
	 * l'ordre et le nom de l'attribut de la requête.<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 * 
	 * @return : String :  une liste avec à chaque ligne 
	 * l'ordre et le nom de l'attribut de la requête.<br/>
	 */
	public final String recupererListeAttributsRequete(
			final HttpServletRequest pRequest) {
		
		/* Récupération de la liste des attributs de la requete. */
		final Enumeration<String> nomDesAttributs 
			= pRequest.getAttributeNames();
		
		int i = 0;
		final StringBuffer stb = new StringBuffer();
		final String attribut = "Attribut ";
		
		while (nomDesAttributs.hasMoreElements()) {
			i++;
			stb.append("<p>");
			stb.append(attribut);
			stb.append(i);
			stb.append(" = ");
			stb.append(nomDesAttributs.nextElement());
			stb.append("</p>");			
		}

		return stb.toString();
		
	} // Fin de recupererListeAttributsRequete(
	 // HttpServletRequest pRequest).______________________________________



	/**
	 * method recupererListeHeadersRequete(
	 * HttpServletRequest pRequest) :<br/>
	 * Fournit une String HTML à mettre dans un tableau HTML
	 * pour l'affichage HTML des Headers de la requête.<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 * 
	 * @return : String : Contenu du tableau déjà au format HTML
	 * pour affichage dans un tableau HTML.<br/>
	 */
	public final String recupererListeHeadersRequete(
			final HttpServletRequest pRequest) {
		
		/* Récupération de la liste des noms des Headers de la requete. */
		final Enumeration<String> nomDesHeaders 
			= pRequest.getHeaderNames();
		
		final StringBuffer stb = new StringBuffer();
		
		while (nomDesHeaders.hasMoreElements()) {
			
			final String nomHeader = nomDesHeaders.nextElement();

			/* Début de ligne. */
			stb.append("<tr>");
			
			/* Nom du Header. */
			stb.append("<td>");
			stb.append("<b>");
			stb.append(nomHeader);
			stb.append("</b>");
			stb.append("</td>");
			
			/* Valeurs du Header. */
			final Enumeration<String> valeursHeader 
				= pRequest.getHeaders(nomHeader);
			
			while (valeursHeader.hasMoreElements()) {
				
				final String valeur = valeursHeader.nextElement();
				
				stb.append("<td>");
				stb.append(valeur);
				stb.append("</td>");
			}
			
			/* Fin de ligne. */
			stb.append("</tr>");			
		}

		return stb.toString();
		
	} // Fin de recupererListeHeadersRequete(
	 // HttpServletRequest pRequest).______________________________________

	

	/**
	 * method recupererTableauCookies(
	 * HttpServletRequest pRequest) :<br/>
	 * Fournit le tableau des Cookies transmis dans la requête
	 * de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 * @return : Cookie[] : Le tableau des Cookies transmis par la
	 * requête de l'internaute.<br/>
	 */
	public final Cookie[] recupererTableauCookies(
			final HttpServletRequest pRequest) {
		
		return pRequest.getCookies();
		
	} // Fin de recupererTableauCookies(
	 // HttpServletRequest pRequest).______________________________________
	

	
	/**
	 * method recupererListeCookies(
	 * HttpServletRequest pRequest) :<br/>
	 * Récupère un tableau de commentaires
	 * décrivant chaque Cookie transmis par la requête 
	 * pour affichage à la console.<br/>
	 * <br/>
	 * Exemple avec un seul cookie : <br/>
	 * Cookie(1) 	Commentaire du Cookie : null	
	 * Domaine du Cookie : null	Age maximal du Cookie : -1	
	 * Nom du Cookie : CERBEREMOE_SESSIONID	
	 * Path du Cookie : null	
	 * Protocole sécurisé exclusivement pour les Cookies : false	
	 * Value du Cookie : 
	 * ZGFueS5sZXZ5QGRldmVsb3BwZW1lbnQtZHVyYW
	 * JsZS5nb3V2LmZyXmNlcmJlcmVeMF4xMzYyNTI3O
	 * DcyMjEzXmFkZWE2OTM2NWQxMTc3OTU4MmZiN
	 * mZlOGExNjIzMDE2	
	 * Version du Cookie : 0	<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 * @return : String[] : Tableau de commentaires
	 * décrivant chaque Cookie transmis par la requête 
	 * pour affichage à la console.<br/>
	 */
	public final String[] recupererListeCookies(
			final HttpServletRequest pRequest) {
		
		final Cookie[] cookies = this.recupererTableauCookies(pRequest);
		
		if (cookies == null) {
			return null;
		}
		
		if (cookies.length == 0) {
			return null;
		}
		
		/* Longueur du tableau de cookies. */
		final int longueurTableau = cookies.length;
		
		/* Instanciation d'un tableau de String contenant
		 * les lignes de description de chaque Cookie. */
		final String[] tableauStringCookies = new String[longueurTableau];
				
		/* Parcours des Cookies. */
		for (int i = 0; i < longueurTableau; i++) {
			
			final StringBuffer stb = new StringBuffer();
			
			final Cookie cookie = cookies[i];
			
			stb.append("Cookie(");
			stb.append(i + 1);
			stb.append(") ");
			stb.append('\t');
			
			stb.append("Commentaire du Cookie : ");
			stb.append(cookie.getComment());
			stb.append('\t');
			
			stb.append("Domaine du Cookie : ");
			stb.append(cookie.getDomain());
			stb.append('\t');
			
			stb.append("Age maximal du Cookie : ");
			stb.append(cookie.getMaxAge());
			stb.append('\t');
			
			stb.append("Nom du Cookie : ");
			stb.append(cookie.getName());
			stb.append('\t');
			
			stb.append("Path du Cookie : ");
			stb.append(cookie.getPath());
			stb.append('\t');
			
			stb.append("Protocole sécurisé exclusivement " 
			+ "pour les Cookies : ");
			stb.append(cookie.getSecure());
			stb.append('\t');
			
			stb.append("Value du Cookie : ");
			stb.append(cookie.getValue());
			stb.append('\t');
			
			stb.append("Version du Cookie : ");
			stb.append(cookie.getVersion());
			stb.append('\t');
			
			stb.append("toString() du Cookie : ");
			stb.append(cookie.toString());
			stb.append('\t');
						
			tableauStringCookies[i] = stb.toString();
		}
		
		return tableauStringCookies;
		
	} // Fin de recupererListeCookies(
	 // HttpServletRequest pRequest).______________________________________

	
		
	/**
	 * method recupererListeCookiesHTML(
	 * HttpServletRequest pRequest) :<br/>
	 * Récupère un tableau de commentaires
	 * décrivant chaque Cookie transmis par la requête 
	 * pour affichage HTML.<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 * @return : String[] : Tableau de commentaires
	 * décrivant chaque Cookie transmis par la requête 
	 * pour affichage à la console.<br/>
	 */
	public final String[] recupererListeCookiesHTML(
			final HttpServletRequest pRequest) {
		
		final Cookie[] cookies = this.recupererTableauCookies(pRequest);
		
		if (cookies == null) {
			return null;
		}
		
		if (cookies.length == 0) {
			return null;
		}
		
		/* Longueur du tableau de cookies. */
		final int longueurTableau = cookies.length;
		
		/* Instanciation d'un tableau de String contenant
		 * les lignes de description de chaque Cookie. */
		final String[] tableauStringCookies = new String[longueurTableau];
		
				
		/* Parcours des Cookies. */
		for (int i = 0; i < longueurTableau; i++) {
			
			final StringBuffer stb = new StringBuffer();
			
			final Cookie cookie = cookies[i];
			
						
			stb.append("<tr>");
			
			stb.append("<td>");
			stb.append("<b>Cookie(");
			stb.append(i + 1);
			stb.append(") </b>");
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>Commentaire du Cookie : </b>");
			stb.append(cookie.getComment());
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>Domaine du Cookie : </b>");
			stb.append(cookie.getDomain());
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>Age maximal du Cookie (" 
					+ "-1 veut dire que le cookie va persister " 
					+ "après la fermeture du navigateur) : </b>");
			stb.append(cookie.getMaxAge());
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>Nom du Cookie : </b>");
			stb.append(cookie.getName());
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>Path du Cookie : </b>");
			stb.append(cookie.getPath());
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>le navigateur utilise un Protocole sécurisé " 
			+ "pour envoyer les Cookies : </b>");
			stb.append(cookie.getSecure());
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>Value du Cookie : </b>");
			stb.append(cookie.getValue());
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>Version du Cookie : </b>");
			stb.append(cookie.getVersion());
			stb.append("</td>");
			
			stb.append("<td>");
			stb.append("<b>toString() du Cookie : </b>");
			stb.append(cookie.toString());
			stb.append("</td>");
			
			stb.append("</tr>");
			
			
			tableauStringCookies[i] = stb.toString();
		}
		
		return tableauStringCookies;
		
	} // Fin de recupererListeCookiesHTML(
	 // HttpServletRequest pRequest).______________________________________
	

	
	/**
	 * method imprimerRenseignements(
	 * HttpServletRequest pRequest
	 * , HttpServletResponse pResponse) :<br/>
	 * Fabrique une réponse HTTP à retourner au navigateur
	 * contenant divers renseignements sur la requête passée.<br/>
	 * <br/>
	 *
	 * @param pRequest : HttpServletRequest : la requête de Login
	 * de l'Internaute.<br/>
	 * @param pResponse : HttpServletResponse : réponse à renvoyer
	 * au navigateur.<br/>
	 * @throws IOException : lorsque problème du PrintWriter pour créer
	 * une réponse HTTP.<br/>
	 */
	public final void imprimerRenseignements(
			final HttpServletRequest pRequest
				, final HttpServletResponse pResponse) 
						throws IOException {
		
		/* Code pour afficher du texte dans le navigateur. */
		pResponse.setContentType("text/html; charset=UTF-8");
		
		/* Ouverture d'un flux PrintWriter. */
	    final PrintWriter out = pResponse.getWriter();
	    
	    out.println("<p>La requête est passée : </p>");
	    
	    /* Liste les attributs de la requete. */
	    this.imprimerListeAttributsRequete(out, pRequest);
	    
	    /* Système d'authentification de la servlet. */
	    this.imprimerSystemeAuthentification(out, pRequest);
	    
	   /* Jeu d'encodage du corps de la requête. */
	    this.imprimerEncodage(out, pRequest);
	    
	    /* Longueur du contenu. */
	    this.imprimerLongueurContenu(out, pRequest);
	    
	    /* Type MIME. */
	    this.imprimerTypeMime(out, pRequest);
	    
	    /* Contexte. */
	    this.imprimerContexte(out, pRequest);
	    
	    /* Cookies. */
	    this.imprimerCookiesRequete(out, pRequest);
	    
	    /* HEADERS. */
	    this.imprimerHeadersRequete(out, pRequest);
	    
	    /* Fermeture du flux. */
	    out.close();
	    
	} // Fin de imprimerRenseignements(
	 // HttpServletRequest pRequest
	 // , HttpServletResponse pResponse).__________________________________
	
	
	
	 /**
	 * method imprimerListeAttributsRequete(
	 * PrintWriter pOut
	 * , HttpServletRequest pRequest) :<br/>
	 * Imprime dans le flux PrintWriter pOut la liste 
	 * des noms des attributs de la requête en HTML.<br/>
	 * <br/>
	 *
	 * @param pOut : PrintWriter.<br/>
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 */
	public final void imprimerListeAttributsRequete(
			final PrintWriter pOut
				, final HttpServletRequest pRequest) {
		
		 pOut.println("<div><h3>Liste des attributs de la requête " 
		    		+ "(pRequest.getAttributeNames()) : </h3>"
		    		+ this.recupererListeAttributsRequete(pRequest) 
		    		+ "</div");
		 
	 } // Fin de imprimerListeAttributsRequete(
	 // PrintWriter pOut
	 // , HttpServletRequest pRequest).____________________________________
	

	
	/**
	 * method imprimerSystemeAuthentification(
	 * PrintWriter pOut
	 * , HttpServletRequest pRequest) :<br/>
	 * Imprime dans le flux PrintWriter pOut le système 
	 * d'authentification de la présente Servlet.<br/>
	 * <br/>
	 *
	 * @param pOut : PrintWriter.<br/>
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 */
	public final void imprimerSystemeAuthentification(
			final PrintWriter pOut
				, final HttpServletRequest pRequest) {
		
		pOut.println("<div><b>système d'authentification de la Servlet " 
		    		+ "(pRequest.getAuthType()) : </b>" 
		    		+ pRequest.getAuthType() + "</div>");
		
	} // Fin de imprimerSystemeAuthentification(
	 // PrintWriter pOut
	 // , HttpServletRequest pRequest).____________________________________
	

	
	/**
	 * method imprimerEncodage(
	 * PrintWriter pOut
	 * , HttpServletRequest pRequest) :<br/>
	 * Imprime dans le flux PrintWriter pOut 
	 * le système d'encodage du corps de la requête.<br/>
	 * <br/>
	 *
	 * @param pOut : PrintWriter.<br/>
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 */
	public final void imprimerEncodage(
			final PrintWriter pOut
				, final HttpServletRequest pRequest) {
		
		pOut.println("<div><b>Jeu de caractères dans le corps de la requête " 
	    		+ "(pRequest.getCharacterEncoding()) : </b>" 
	    		+ pRequest.getCharacterEncoding() + "</div>");
		
	} // Fin de imprimerEncodage(
	 // PrintWriter pOut
	 // , HttpServletRequest pRequest).____________________________________

	
	
	/**
	 * method imprimerLongueurContenu(
	 * PrintWriter pOut
	 * , HttpServletRequest pRequest) :<br/>
	 * Imprime dans le flux PrintWriter pOut 
	 * la longueur du contenu de la requête.<br/>
	 * <br/>
	 * - Retourne -1 si la longueur n'est pas connue.<br/>
	 * <br/>
	 *
	 * @param pOut : PrintWriter.<br/>
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 */
	public final void imprimerLongueurContenu(
			final PrintWriter pOut
				, final HttpServletRequest pRequest) {
		
		pOut.println("<div><b>Longueur du contenu de la requête " 
	    		+ "(pRequest.getContentLength()) : </b>" 
	    		+ pRequest.getContentLength() + "</div>");
		
	} // Fin de imprimerLongueurContenu(
	 // PrintWriter pOut
	 // , HttpServletRequest pRequest).____________________________________

	
		
	/**
	 * method imprimerTypeMime(
	 * PrintWriter pOut
	 * , HttpServletRequest pRequest) :<br/>
	 * Imprime dans le flux PrintWriter pOut 
	 * le type MIME de la requête.<br/>
	 * <br/>
	 * - retourne null si le type MIME n'est pas connu.<br/>
	 * <br/>
	 *
	 * @param pOut : PrintWriter.<br/>
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 */
	public final void imprimerTypeMime(
			final PrintWriter pOut
				, final HttpServletRequest pRequest) {
		
		pOut.println("<div><b>Type MIME de la requête " 
	    		+ "(pRequest.getContentType()) : </b>" 
	    		+ pRequest.getContentType() + "</div>");
		
	} // Fin de imprimerTypeMime(
	 // PrintWriter pOut
	 // , HttpServletRequest pRequest).____________________________________

	
	
	/**
	 * method imprimerContexte(
	 * PrintWriter pOut
	 * , HttpServletRequest pRequest) :<br/>
	 * Imprime dans le flux PrintWriter pOut 
	 * le CONTEXTE de la requête.<br/>
	 * <br/>
	 *
	 * @param pOut : PrintWriter.<br/>
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 */
	public final void imprimerContexte(
			final PrintWriter pOut
				, final HttpServletRequest pRequest) {
		
		pOut.println("<div><b>Contexte " 
	    		+ "(pRequest.getContextPath()) : </b>" 
	    		+ pRequest.getContextPath() + "</div>");
		
	} // Fin de imprimerContexte(
	 // PrintWriter pOut
	 // , HttpServletRequest pRequest).____________________________________
	

	
	/**
	 * method imprimerCookiesRequete(
	 * PrintWriter pOut
	 * , HttpServletRequest pRequest) :<br/>
	 * Imprime dans le flux PrintWriter pOut 
	 * la liste des cookies passés par la requête.<br/>
	 * <br/>
	 *
	 * @param pOut : PrintWriter.<br/>
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 */
	public final void imprimerCookiesRequete(
			final PrintWriter pOut
				, final HttpServletRequest pRequest) {
		
		pOut.println("<div><h3>Liste des cookies de la requête " 
	    		+ "(pRequest.getCookies()) : </h3>"
				+ "<table border=\"1\">"
	    		+ imprimerHTTPTableauString(
	    				this.recupererListeCookiesHTML(pRequest))
	    		+ "</table>"
	    		+ "</div");
		
	} // Fin de imprimerCookiesRequete(
	 // PrintWriter pOut
	 // , HttpServletRequest pRequest).____________________________________
	 
	 
	
	/**
	 * method imprimerHeadersRequete(
	 * PrintWriter pOut
	 * , HttpServletRequest pRequest) :<br/>
	 * Imprime dans le flux PrintWriter pOut 
	 * la liste des En-têtes (Headers) de la requête.<br/>
	 * <br/>
	 *
	 * @param pOut : PrintWriter.<br/>
	 * @param pRequest : HttpServletRequest : la requête de l'internaute
	 * interceptée par la présente Servlet.<br/>
	 */
	public final void imprimerHeadersRequete(
			final PrintWriter pOut
				, final HttpServletRequest pRequest) {
		
		pOut.println("<div><h3>Liste des Headers de la requête " 
	    		+ "(pRequest.getHeaderNames()) : </h3>"
				+ "<table border=\"1\">"
	    		+ this.recupererListeHeadersRequete(pRequest)
	    		+ "</table>"
	    		+ "</div");
		
	} // Fin de imprimerHeadersRequete(
	 // PrintWriter pOut
	 // , HttpServletRequest pRequest).____________________________________

	
	
	/**
	 * method imprimerConsoleTableauString(
	 * String[] pTableauString) :<br/>
	 * Fournit une String pour l'affichage 
	 * à la console d'un tableau de String
	 * déjà formattées pour la console.<br/>
	 * <br/>
	 *
	 * @param pTableauString : String[].<br/>
	 * @return : String : Chaque ligne du tableau de String 
	 * avec passage à la ligne.<br/>
	 */
	public static final String imprimerConsoleTableauString(
			final String[] pTableauString) {
		
		if (pTableauString == null) {
			return null;
		}
		
		if (pTableauString.length == 0) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (int i = 0; i < pTableauString.length; i++) {
			stb.append(pTableauString[i]);
			stb.append('\n');
		}
		
		return stb.toString();
		
	} // Fin de imprimerConsoleTableauString(
	 // String[] pTableauString).__________________________________________

	
	
	/**
	 * method imprimerHTTPTableauString(
	 * String[] pTableauString) :<br/>
	 * Fournit une String pour l'affichage 
	 * à la console d'un tableau de String déjà au format HTML.<br/>
	 * <br/>
	 *
	 * @param pTableauString : String[].<br/>
	 * @return : String : Chaque ligne du tableau de String 
	 * dans un tableau HTML.<br/>
	 */
	public static final String imprimerHTTPTableauString(
			final String[] pTableauString) {
		
		if (pTableauString == null) {
			return null;
		}
		
		if (pTableauString.length == 0) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (int i = 0; i < pTableauString.length; i++) {
			stb.append(pTableauString[i]);
		}
		
		return stb.toString();
		
	} // Fin de imprimerHTTPTableauString(
	 // String[] pTableauString).__________________________________________
	 
	 
	
} // FIN DE LA CLASSE AuthentificationServlet.-------------------------------
