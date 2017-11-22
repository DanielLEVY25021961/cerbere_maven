package cerbere.demo;

import i2.application.cerbere.commun.Cerbere;
import i2.application.cerbere.commun.CerbereConnexionException;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description de l'utilisateur courant.
 * 
 * <p>Cet objet masque les spécificités Cerbère à l'application, excepté sur la
 * méthode getCerbere, utilisé ici pour les besoins de la démonstration.</p>
 */
public class User implements Serializable {
	
    /** Identifiant de version */
    private static final long serialVersionUID = 1L;
    
    
    /**
     * cerbere : Cerbere :<br/>
     * Objet Cerbère.<br/>
     */
    private Cerbere cerbere;

    
    

     /**
     * method CONSTRUCTEUR User() :<br/>
     * Construction des propriétés utilisateur.<br/>
     * <br/>
     * Si un objet déjà existant est trouvé en session, il est utilisé. Sinon
     * il est crée "à la volée" d'après la requête courante.<br/>
     *
     * @throws IOException
     */
    public User() throws IOException {
        try {
            this.cerbere = Cerbere.creation();
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    
    
    
    /**
     * method deconnexion() :<br/>
     * Désauthentification de l'application.<br/>
     * Cette méthode supprime la référence éventuellement présente en session
     * de l'obkjet utilisateur et lance la désauthentification Cerbère.
     * <br/>
     *
     * @param pRequest : HttpServletRequest : requête HTTP courante.<br/>
     * @param pResponse : HttpServletResponse : réponse HTTP courante.<br/>
     * @throws IOException : void :  .<br/>
     */
    public void deconnexion(
    		final HttpServletRequest pRequest
    			, final HttpServletResponse pResponse) 
    						throws IOException {
    	
        try {
            // Effacement de l'objet en session.
            clearSession(pRequest);
            // désauthentification
            this.cerbere.logoff(pRequest, pResponse);
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    
    
    /**
     * method deconnexion() :<br/>
     * Désauthentification de l'application + redirection..<br/>
     * <br/>
     * Cette méthode supprime la référence éventuellement présente en session
     * de l'objet utilisateur et lance la désauthentification Cerbère.<br/>
     * <br/>
     *
     * @param pRequest : HttpServletRequest : requête HTTP courante.<br/>
     * @param pResponse : HttpServletResponse : réponse HTTP courante.<br/>
     * @param pUrl : String : URL de redirection.<br/> 
     * 
     * @throws IOException : void :  .<br/>
     */
    public void deconnexion(
    		final HttpServletRequest pRequest
    			, final HttpServletResponse pResponse
    				, final String pUrl) throws IOException {
    	
        try {
            // Effacement de l'objet en session.
            clearSession(pRequest);
            // désauthentification
            this.cerbere.logoff(pRequest, pResponse, pUrl);
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    
    
 
    /**
     * method reconnexion() :<br/>
     * Ré-authentification sur l'application.<br/>
     * Cette méthode supprime la référence éventuellement présente en session
     * de l'objet utilisateur et lance la ré-authentification Cerbère.<br/>
     * <br/>
     *
     * @param pRequest : HttpServletRequest : requête HTTP courante.<br/>
     * @param pResponse : HttpServletResponse : réponse HTTP courante.<br/>
     * @param pUrl : String : URL de redirection sur l'application.<br/> 
     * 
     * @throws IOException : void :  .<br/>
     */
    public void reconnexion(
    		final HttpServletRequest pRequest
    			, final HttpServletResponse pResponse
    				, final String pUrl)
    							throws IOException {
        try {
            // Effacement de l'objet en session
            clearSession(pRequest);
            // Réauthentification
            this.cerbere.reAuthentification(pRequest, pResponse, pUrl);
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    
    
    
    /**
     * Suppression de l'objet en session.
     * 
     * @param request requête HTTP courante.
     */
    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        session.invalidate();
    }
 
    
    
    /**
     * method getCerbere() :<br/>
     * Obtention  d'un pointeur sur l'objet Cerbère courant.<br/>
     * <br/>
     *
     * @return : Cerbere : objet Cerbere associé à l'utilisateur.<br/>
     */
    public Cerbere getCerbere() {
        return this.cerbere;
    }
    
    
    
}
