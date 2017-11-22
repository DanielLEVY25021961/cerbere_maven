package levy.daniel.application.controllers.web.accueil;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * class AccueilController :<br/>
 * Controller dans le Modele MVC.<br/>
 * <ul>
 * <li>déclarée en tant que contrôleur grâce à 
 * l'annotation Spring @Controller.</li>
 * <li>@RequestMapping("/") indique que le présent contrôleur 
 * traite les requêtes GET dont l'URI est /.</li>
 * </ul>
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
 * @author dan Lévy
 * @version 1.0
 * @since 11 août 2017
 *
 */
@Controller
public class AccueilController {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(AccueilController.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AccueilController() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AccueilController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method versPageAccueil() :<br/>
	 * <ul>
	 * <li>Traite les requêtes (URL) de la forme 
	 * "context/app/accueil".</li>
	 * <li>le préfixe "app/" ne figure pas dans la partie de l'URL 
	 * fournie dans le RequestMapping parce que le <b>web.xml</b> 
	 * précise que les URL interceptées par SPRING 
	 * débuteront forcément par /app/.<br/> 
	 * Dès lors, le préfixe "app/" est automatiquement rajouté 
	 * par SPRING dans l'URL de l'action dans le RequestMapping.</li>
	 * <li><b>retourne le chemin vers la JSP "accueil.jsp"</b>.</li>
	 * <li>Le chemin complet de la jsp est 
	 * "/WEB-INF/vues/web/accueil/accueil.jsp" 
	 * mais le <b>applicationContext.xml</b> précise 
	 * dans la balise <b>mvc:resources</b> que :
	 * <ul>
	 * <li>Toutes les vues jsp sont préfixées par le controller SPRING 
	 * avec "/WEB-INF/vues/web/" (applicationContext.xml).</li>
	 * <li>Toutes les vues jsp sont suffixées par le controller SPRING 
	 * avec ".jsp" (applicationContext.xml).</li>
	 * On ne met que la partie de l'URL nécessaire dans la méthode.<br/>
	 * </ul>
	 * </ul>
	 * @param pRequest : HttpServletRequest.<br/>
	 *
	 * @return : String : chemin vers la jsp .<br/>
	 */
	@RequestMapping(value="accueil")
	public String versPageAccueil(
			final HttpServletRequest pRequest) {
		
		/* Texte du lien clickable dans le fil d'ariane. */
		final String arianeAccueil 
			= "accueil";
		
		pRequest.setAttribute("ariane", arianeAccueil);
		
		/* retourne l'URL de la JSP. */
		return "accueil/accueil";
		
	} // Fin de versPageAccueil()._________________________________________
	

	
} // FIN DE LA CLASSE AccueilController.-------------------------------------
