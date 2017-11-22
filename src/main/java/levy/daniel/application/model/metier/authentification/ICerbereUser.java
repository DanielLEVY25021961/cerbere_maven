package levy.daniel.application.model.metier.authentification;

import java.io.Serializable;

/**
 * class ICerbereUser :<br/>
 * Interface factorisant tous les comportements des CerbereUser.<br/>
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
 * @author DAN Lévy
 * @version 1.0
 * @since 28 mars 2013
 *
 */
public interface ICerbereUser extends Serializable, Cloneable
												, Comparable<Object> {

	/**
	 * method hashCode() :<br/>
	 * Hashcode.<br/>
	 * <br/>
	 *
	 * @return int.<br/>
	 */
	@Override
	int hashCode();


	
	/**
	 * method equals(
	 * Object pObject) :<br/>
	 * Egalité métier.<br/>
	 * <br/>
	 *
	 * @param pObject : Object.<br/>
	 * @return boolean.<br/>
	 */
	@Override
	boolean equals(final Object pObject);
	
	
	
	/**
	 * method compareTo(
	 * Object pObject) :<br/>
	 * Comparaison pour affichage.<br/>
	 * <br/>
	 *
	 * @param pObject : Object.<br/>
	 * @return : int.<br/>
	 */
	@Override
	int compareTo(final Object pObject);
	
	

	/**
	 * method clone() :<br/>
	 * Clone.<br/>
	 * <br/>
	 *
	 * @return Object : clone.<br/>
	 * @throws CloneNotSupportedException : 
	 * lorsque impossible de cloner.<br/>
	 */
	Object clone() throws CloneNotSupportedException;
	
	

	/**
	 * method toString() :<br/>
	 * Affichage.<br/>
	 * <br/>
	 *
	 * @return String.<br/>
	 */
	@Override
	String toString();

	

	/**
	 * method getIdBase() :<br/>
	 * Getter de l'Identifiant en base.<br/>
	 * <br/>
	 *
	 * @return idBase : Long.<br/>
	 */
	Long getIdBase();

	
	
	/**
	 * method setIdBase(
	 * Long pIdBase) :<br/>
	 * Setter de l'Identifiant en base.<br/>
	 * <br/>
	 *
	 * @param pIdBase : Long : valeur à passer à idBase.<br/>
	 */
	void setIdBase(final Long pIdBase);
	
	

	/**
	 * method getIdCerbere() :<br/>
	 * Getter de l'Identifiant métier Cerbere de l'internaute.<br/>
	 * <br/>
	 *
	 * @return idCerbere : String.<br/>
	 */
	String getIdCerbere();
	
	

	/**
	 * method setIdCerbere(
	 * String pIdCerbere) :<br/>
	 * Setter de l'Identifiant métier Cerbere de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pIdCerbere : String : valeur à passer à idCerbere.<br/>
	 */
	void setIdCerbere(final String pIdCerbere);
	
	

	/**
	 * method getMel() :<br/>
	 * Getter du mel de l'internaute.<br/>
	 * <br/>
	 *
	 * @return mel : String.<br/>
	 */
	String getMel();
	
	

	/**
	 * method setMel(
	 * String pMel) :<br/>
	 * Setter du mel de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pMel : String : valeur à passer à mel.<br/>
	 */
	void setMel(final String pMel);
	
	

	/**
	 * method getCivilite() :<br/>
	 * Getter de la Civilité de l'internaute.<br/>
	 * <br/>
	 *
	 * @return civilite : String.<br/>
	 */
	String getCivilite();
	
	
	
	/**
	 * method setCivilite(
	 * String pCivilite) :<br/>
	 * Setter de la Civilité de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pCivilite : String : valeur à passer à civilite.<br/>
	 */
	void setCivilite(final String pCivilite);
	
	

	/**
	 * method getPrenom() :<br/>
	 * Getter du Prénom de l'internaute.<br/>
	 * <br/>
	 *
	 * @return prenom : String.<br/>
	 */
	String getPrenom();
	
	

	/**
	 * method setPrenom(
	 * String pPrenom) :<br/>
	 * Setter du Prénom de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pPrenom : String : valeur à passer à prenom.<br/>
	 */
	void setPrenom(final String pPrenom);
	
	

	/**
	 * method getNom() :<br/>
	 * Getter du Nom de l'internaute.<br/>
	 * <br/>
	 *
	 * @return nom : String.<br/>
	 */
	String getNom();
	
	

	/**
	 * method setNom(
	 * String pNom) :<br/>
	 * Setter du Nom de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pNom : String : valeur à passer à nom.<br/>
	 */
	void setNom(final String pNom);
	
	

	/**
	 * method getUnite() :<br/>
	 * Getter Unité (service) de l'internaute.<br/>
	 * <br/>
	 *
	 * @return unite : String.<br/>
	 */
	String getUnite();
	
	

	/**
	 * method setUnite(
	 * String pUnite) :<br/>
	 * Setter Unité (service) de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pUnite : String : valeur à passer à unite.<br/>
	 */
	void setUnite(final String pUnite);
	
	

	/**
	 * method getEntreprise() :<br/>
	 * Getter de l'Entreprise de l'internaute.<br/>
	 * <br/>
	 *
	 * @return entreprise : String.<br/>
	 */
	String getEntreprise();
	
	

	/**
	 * method setEntreprise(
	 * String pEntreprise) :<br/>
	 * Setter de l'Entreprise de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pEntreprise : String : valeur à passer à entreprise.<br/>
	 */
	void setEntreprise(final String pEntreprise);
	
	

	/**
	 * method getNomProfil() :<br/>
	 * Getter du Nom du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @return nomProfil : String.<br/>
	 */
	String getNomProfil();
	
	

	/**
	 * method setNomProfil(
	 * String pNomProfil) :<br/>
	 * Setter du Nom du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pNomProfil : String : valeur à passer à nomProfil.<br/>
	 */
	void setNomProfil(final String pNomProfil);
	
	

	/**
	 * method getPorteeProfil() :<br/>
	 * Getter de la Portée du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @return porteeProfil : String.<br/>
	 */
	String getPorteeProfil();
	
	

	/**
	 * method setPorteeProfil(
	 * String pPorteeProfil) :<br/>
	 * Setter de la Portée du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pPorteeProfil : String : valeur à passer à porteeProfil.<br/>
	 */
	void setPorteeProfil(final String pPorteeProfil);
	
	

	/**
	 * method getRestrictionProfil() :<br/>
	 * Getter de la Restriction du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @return restrictionProfil : String.<br/>
	 */
	String getRestrictionProfil();
	
	

	/**
	 * method setRestrictionProfil(
	 * String pRestrictionProfil) :<br/>
	 * Setter de la Restriction du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pRestrictionProfil : String : 
	 * valeur à passer à restrictionProfil.<br/>
	 */
	void setRestrictionProfil(final String pRestrictionProfil);

	
} // FIN DE L'INTERFACE ICerbereUser.----------------------------------------'