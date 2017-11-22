package levy.daniel.application.model.metier.authentification.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.authentification.AbstractCerbereUser;



/**
 * class CerbereUser :<br/>
 * Objet modélisant les droits de l'internaute en Session.<br/>
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
 * @since 28 mars 2013
 *
 */
public class CerbereUser extends AbstractCerbereUser {

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
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(CerbereUser.class);

	// *************************METHODES************************************/
	
	 /**
	 * method CONSTRUCTEUR CerbereUser() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public CerbereUser() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	 /**
	 * method CONSTRUCTEUR CerbereUser() :<br/>
	 * CONSTRUCTEUR INCOMPLET.<br/>
	 * - SANS Identifiant en base.<br/>
	 * <br/>
	 *
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
	 */
	public CerbereUser(
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
		
		super(pIdCerbere
				, pMel
				, pCivilite
				, pPrenom
				, pNom
				, pUnite
				, pEntreprise
				, pNomProfil
				, pPorteeProfil
				, pRestrictionProfil);

	} // Fin du CONSTRUCTEUR INCOMPLET.____________________________________


	 /**
	 * method CONSTRUCTEUR CerbereUser() :<br/>
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <br/>
	 *
	 * @param pIdBase : Long : Identifiant en base.<br/> 
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
	 */
	public CerbereUser(
			final Long pIdBase
			, final String pIdCerbere
			, final String pMel
			, final String pCivilite
			, final String pPrenom
			, final String pNom
			, final String pUnite
			, final String pEntreprise
			, final String pNomProfil
			, final String pPorteeProfil
			, final String pRestrictionProfil) {
		
		super(
				pIdBase
				, pIdCerbere
				, pMel
				, pCivilite
				, pPrenom
				, pNom
				, pUnite
				, pEntreprise
				, pNomProfil
				, pPorteeProfil
				, pRestrictionProfil);
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________



	
} // FIN DE LA CLASS CerbereUser.--------------------------------------------
