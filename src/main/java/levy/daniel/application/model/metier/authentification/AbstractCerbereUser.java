package levy.daniel.application.model.metier.authentification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class AbstractCerbereUser :<br/>
 * CLASSE ABSTRAITE MODELISANT LES CerbereUser.<br/>
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
public abstract class AbstractCerbereUser implements ICerbereUser {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * idBase : Long :<br/>
	 * Identifiant en base.<br/>
	 */
	private Long idBase = null;
	
	
	/**
	 * idCerbere : String :<br/>
	 * Identifiant métier Cerbere de l'internaute.<br/>
	 */
	private String idCerbere = null;
	
	
	/**
	 * mel : String :<br/>
	 * mel de l'internaute.<br/>
	 */
	private String mel = null;

	
	/**
	 * civilite : String :<br/>
	 * Civilité de l'internaute.<br/>
	 */
	private String civilite = null;
	
	
	/**
	 * prenom : String :<br/>
	 * Prénom de l'internaute.<br/>
	 */
	private String prenom = null;
	
	
	/**
	 * nom : String :<br/>
	 * Nom de l'internaute.<br/>
	 */
	private String nom = null;
	
	
	/**
	 * unite : String :<br/>
	 * Unité (service) de l'internaute.<br/>
	 */
	private String unite = null;
	
	
	/**
	 * entreprise : String :<br/>
	 * Entreprise de l'internaute.<br/>
	 */
	private String entreprise = null;

	
	/**
	 * nomProfil : String :<br/>
	 * Nom du profil de l'internaute.<br/>
	 */
	private String nomProfil = null;
	
	
	/**
	 * porteeProfil : String :<br/>
	 * Portée du profil de l'internaute.<br/>
	 */
	private String porteeProfil = null;
	
	
	/**
	 * restrictionProfil : String :<br/>
	 * Restriction du profil de l'internaute.<br/>
	 */
	private String restrictionProfil = null;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AbstractCerbereUser.class);



	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractCerbereUser() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractCerbereUser() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	 /**
	 * method CONSTRUCTEUR AbstractCerbereUser() :<br/>
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
	public AbstractCerbereUser(
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
		
		this(null
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
		
	} // Fin du CONSTRUCTEUR INCOMPLET.____________________________________


	
	 /**
	 * method CONSTRUCTEUR AbstractCerbereUser() :<br/>
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
	 */
	public AbstractCerbereUser(
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
		
		super();
		this.idBase = pIdBase;
		this.idCerbere = pIdCerbere;
		this.mel = pMel;
		this.civilite = pCivilite;
		this.prenom = pPrenom;
		this.nom = pNom;
		this.unite = pUnite;
		this.entreprise = pEntreprise;
		this.nomProfil = pNomProfil;
		this.porteeProfil = pPorteeProfil;
		this.restrictionProfil = pRestrictionProfil;
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * method hashCode() :<br/>
	 * Hashcode.<br/>
	 * <br/>
	 *
	 * @return int.<br/>
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.mel == null) ? 0 : this.mel.hashCode());
		result = prime * result
				+ ((this.nom == null) ? 0 : this.nom.hashCode());
		result = prime * result
				+ ((this.prenom == null) ? 0 : this.prenom.hashCode());
		return result;
	} // Fin de hashCode().________________________________________________



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
	public final boolean equals(
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		
		if (!(pObject instanceof AbstractCerbereUser)) {
			return false;
		}
		
		final AbstractCerbereUser other = (AbstractCerbereUser) pObject;
		
		if (this.mel == null) {
			if (other.mel != null) {
				return false;
			}
		} else if (!this.mel.equals(other.mel)) {
			return false;
		}
		if (this.nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!this.nom.equals(other.nom)) {
			return false;
		}
		if (this.prenom == null) {
			if (other.prenom != null) {
				return false;
			}
		} else if (!this.prenom.equals(other.prenom)) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(
	 // Object pObject).___________________________________________________


	
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
	public final int compareTo(
			final Object pObject) {
		
		if (this == pObject) {
			return 0;
		}
				
		if (pObject == null) {
			return -1;
		}
		
		if (!(pObject instanceof AbstractCerbereUser)) {
			return -1;
		}
		
		final ICerbereUser other = (ICerbereUser) pObject;
		
							
		/* Comparaison sur nom. */
		
		if (this.getNom() != null) {
			
			if (other.getNom() != null) {
				
				final int compNom = this.getNom().compareTo(other.getNom());
				
				if (compNom != 0) {
					return compNom;
				}
				
				/* Comparaison sur prénom. */
				if (this.getPrenom() != null) {
					
					if (other.getPrenom() != null) {
						
						final int compPrenom 
						= this.getPrenom()
							.compareTo(other.getPrenom());
						
						if (compPrenom != 0) {
							return compPrenom;
						}
						
						if (this.getMel() != null) {
							
							if (other.getMel() != null) {
								return this.getMel()
										.compareTo(other.getMel());
							}
							return -1;
						}
						return +1;
					}
					return -1;
				}
				return +1;
			}
			return -1;
		}
		
		return +1;
			
	} // Fin de compareTo(
	 // Object pObject).___________________________________________________
	
	

	/**
	 * method clone() :<br/>
	 * Clone.<br/>
	 * <br/>
	 *
	 * @return Object : clone.<br/>
	 * @throws CloneNotSupportedException : 
	 * lorsque impossible de cloner.<br/>
	 */
	@Override
	public final Object clone() throws CloneNotSupportedException {
		
		final ICerbereUser clone 
			= (ICerbereUser) super.clone();
		
		clone.setIdBase(this.idBase);
		clone.setIdCerbere(this.idCerbere);
		clone.setMel(this.mel);
		clone.setCivilite(this.civilite);
		clone.setPrenom(this.prenom);
		clone.setNom(this.nom);
		clone.setUnite(this.unite);
		clone.setEntreprise(this.entreprise);
		clone.setNomProfil(this.nomProfil);
		clone.setPorteeProfil(this.porteeProfil);
		clone.setRestrictionProfil(this.restrictionProfil);
		
		return clone;
		
	} // Fin de clone().___________________________________________________
	
	
	
	
	/**
	 * method toString() :<br/>
	 * Affichage.<br/>
	 * <br/>
	 *
	 * @return String.<br/>
	 */
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractCerbereUser [");
		if (this.idBase != null) {
			builder.append("idBase=");
			builder.append(this.idBase);
			builder.append(", ");
		}
		if (this.idCerbere != null) {
			builder.append("idCerbere=");
			builder.append(this.idCerbere);
			builder.append(", ");
		}
		if (this.mel != null) {
			builder.append("mel=");
			builder.append(this.mel);
			builder.append(", ");
		}
		if (this.civilite != null) {
			builder.append("civilite=");
			builder.append(this.civilite);
			builder.append(", ");
		}
		if (this.prenom != null) {
			builder.append("prenom=");
			builder.append(this.prenom);
			builder.append(", ");
		}
		if (this.nom != null) {
			builder.append("nom=");
			builder.append(this.nom);
			builder.append(", ");
		}
		if (this.unite != null) {
			builder.append("unite=");
			builder.append(this.unite);
			builder.append(", ");
		}
		if (this.entreprise != null) {
			builder.append("entreprise=");
			builder.append(this.entreprise);
			builder.append(", ");
		}
		if (this.nomProfil != null) {
			builder.append("nomProfil=");
			builder.append(this.nomProfil);
			builder.append(", ");
		}
		if (this.porteeProfil != null) {
			builder.append("porteeProfil=");
			builder.append(this.porteeProfil);
			builder.append(", ");
		}
		if (this.restrictionProfil != null) {
			builder.append("restrictionProfil=");
			builder.append(this.restrictionProfil);
		}
		
		builder.append(']');
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * method getIdBase() :<br/>
	 * Getter de l'Identifiant en base.<br/>
	 * <br/>
	 *
	 * @return idBase : Long.<br/>
	 */
	@Override
	public final Long getIdBase() {
		return this.idBase;
	} // Fin de getIdBase()._______________________________________________



	/**
	 * method setIdBase(
	 * Long pIdBase) :<br/>
	 * Setter de l'Identifiant en base.<br/>
	 * <br/>
	 *
	 * @param pIdBase : Long : valeur à passer à idBase.<br/>
	 */
	@Override
	public final void setIdBase(
			final Long pIdBase) {
		this.idBase = pIdBase;
	} // Fin de setIdBase(
	 // Long pIdBase)._____________________________________________________



	/**
	 * method getIdCerbere() :<br/>
	 * Getter de l'Identifiant métier Cerbere de l'internaute.<br/>
	 * <br/>
	 *
	 * @return idCerbere : String.<br/>
	 */
	@Override
	public final String getIdCerbere() {
		return this.idCerbere;
	} // Fin de getIdCerbere().____________________________________________



	/**
	 * method setIdCerbere(
	 * String pIdCerbere) :<br/>
	 * Setter de l'Identifiant métier Cerbere de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pIdCerbere : String : valeur à passer à idCerbere.<br/>
	 */
	@Override
	public final void setIdCerbere(
			final String pIdCerbere) {
		this.idCerbere = pIdCerbere;
	} // Fin de setIdCerbere(
	 // String pIdCerbere).________________________________________________



	/**
	 * method getMel() :<br/>
	 * Getter du mel de l'internaute.<br/>
	 * <br/>
	 *
	 * @return mel : String.<br/>
	 */
	@Override
	public final String getMel() {
		return this.mel;
	} // Fin de getMel().__________________________________________________



	/**
	 * method setMel(
	 * String pMel) :<br/>
	 * Setter du mel de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pMel : String : valeur à passer à mel.<br/>
	 */
	@Override
	public final void setMel(
			final String pMel) {
		this.mel = pMel;
	} // Fin de setMel(
	 // String pMel).______________________________________________________



	/**
	 * method getCivilite() :<br/>
	 * Getter de la Civilité de l'internaute.<br/>
	 * <br/>
	 *
	 * @return civilite : String.<br/>
	 */
	@Override
	public final String getCivilite() {
		return this.civilite;
	} // Fin de getCivilite()._____________________________________________



	/**
	 * method setCivilite(
	 * String pCivilite) :<br/>
	 * Setter de la Civilité de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pCivilite : String : valeur à passer à civilite.<br/>
	 */
	@Override
	public final void setCivilite(
			final String pCivilite) {
		this.civilite = pCivilite;
	} // Fin de setCivilite(
	 // String pCivilite)._________________________________________________



	/**
	 * method getPrenom() :<br/>
	 * Getter du Prénom de l'internaute.<br/>
	 * <br/>
	 *
	 * @return prenom : String.<br/>
	 */
	@Override
	public final String getPrenom() {
		return this.prenom;
	} // Fin de getPrenom()._______________________________________________



	/**
	 * method setPrenom(
	 * String pPrenom) :<br/>
	 * Setter du Prénom de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pPrenom : String : valeur à passer à prenom.<br/>
	 */
	@Override
	public final void setPrenom(
			final String pPrenom) {
		this.prenom = pPrenom;
	} // Fin de setPrenom(
	 // String pPrenom).___________________________________________________



	/**
	 * method getNom() :<br/>
	 * Getter du Nom de l'internaute.<br/>
	 * <br/>
	 *
	 * @return nom : String.<br/>
	 */
	@Override
	public final String getNom() {
		return this.nom;
	} // Fin de getNom().__________________________________________________



	/**
	 * method setNom(
	 * String pNom) :<br/>
	 * Setter du Nom de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pNom : String : valeur à passer à nom.<br/>
	 */
	@Override
	public final void setNom(
			final String pNom) {
		this.nom = pNom;
	} // Fin de setNom(
	 // String pNom).______________________________________________________



	/**
	 * method getUnite() :<br/>
	 * Getter Unité (service) de l'internaute.<br/>
	 * <br/>
	 *
	 * @return unite : String.<br/>
	 */
	@Override
	public final String getUnite() {
		return this.unite;
	} // Fin de getUnite().________________________________________________



	/**
	 * method setUnite(
	 * String pUnite) :<br/>
	 * Setter Unité (service) de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pUnite : String : valeur à passer à unite.<br/>
	 */
	@Override
	public final void setUnite(
			final String pUnite) {
		this.unite = pUnite;
	} // Fin de setUnite(
	 // String pUnite).____________________________________________________



	/**
	 * method getEntreprise() :<br/>
	 * Getter de l'Entreprise de l'internaute.<br/>
	 * <br/>
	 *
	 * @return entreprise : String.<br/>
	 */
	@Override
	public final String getEntreprise() {
		return this.entreprise;
	} // Fin de getEntreprise().___________________________________________



	/**
	 * method setEntreprise(
	 * String pEntreprise) :<br/>
	 * Setter de l'Entreprise de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pEntreprise : String : valeur à passer à entreprise.<br/>
	 */
	@Override
	public final void setEntreprise(
			final String pEntreprise) {
		this.entreprise = pEntreprise;
	} // Fin de setEntreprise(
	 // String pEntreprise)._______________________________________________



	/**
	 * method getNomProfil() :<br/>
	 * Getter du Nom du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @return nomProfil : String.<br/>
	 */
	@Override
	public final String getNomProfil() {
		return this.nomProfil;
	} // Fin de getNomProfil().____________________________________________



	/**
	 * method setNomProfil(
	 * String pNomProfil) :<br/>
	 * Setter du Nom du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pNomProfil : String : valeur à passer à nomProfil.<br/>
	 */
	@Override
	public final void setNomProfil(
			final String pNomProfil) {
		this.nomProfil = pNomProfil;
	} // Fin de setNomProfil(
	 // String pNomProfil).________________________________________________



	/**
	 * method getPorteeProfil() :<br/>
	 * Getter de la Portée du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @return porteeProfil : String.<br/>
	 */
	@Override
	public final String getPorteeProfil() {
		return this.porteeProfil;
	} // Fin de getPorteeProfil()._________________________________________



	/**
	 * method setPorteeProfil(
	 * String pPorteeProfil) :<br/>
	 * Setter de la Portée du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pPorteeProfil : String : valeur à passer à porteeProfil.<br/>
	 */
	@Override
	public final void setPorteeProfil(
			final String pPorteeProfil) {
		this.porteeProfil = pPorteeProfil;
	} // Fin de setPorteeProfil(
	 // String pPorteeProfil)._____________________________________________



	/**
	 * method getRestrictionProfil() :<br/>
	 * Getter de la Restriction du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @return restrictionProfil : String.<br/>
	 */
	@Override
	public final String getRestrictionProfil() {
		return this.restrictionProfil;
	} // Fin de getRestrictionProfil().____________________________________



	/**
	 * method setRestrictionProfil(
	 * String pRestrictionProfil) :<br/>
	 * Setter de la Restriction du profil de l'internaute.<br/>
	 * <br/>
	 *
	 * @param pRestrictionProfil : String : 
	 * valeur à passer à restrictionProfil.<br/>
	 */
	@Override
	public final void setRestrictionProfil(
			final String pRestrictionProfil) {
		this.restrictionProfil = pRestrictionProfil;
	} // Fin de setRestrictionProfil(
	 // String pRestrictionProfil).________________________________________

	
	
} // FIN DE LA CLASSE AbstractCerbereUser.-----------------------------------
