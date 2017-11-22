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
    /** Objet Cerbère */
    private Cerbere cerbere;

    /**
     * Construction des propriétés utilisateur.
     * 
     * Si un objet déjà existant est trouvé en session, il est utilisé. Sinon
     * il est crée "à la volée" d'après la requête courante.
     * 
     * @param request requête HTTP courante
     */
    public User(HttpServletRequest request) throws IOException {
        try {
            cerbere = Cerbere.creation(request);
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Désauthentification de l'application.
     * 
     * Cette méthode supprime la référence éventuellement présente en session
     * de l'obkjet utilisateur et lance la désauthentification Cerbère.
     * 
     * @param request requête HTTP courante.
     * @param response réponse HTTP courante.
     */
    public void deconnexion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Effacement de l'objet en session.
            clearSession(request);
            // désauthentification
            cerbere.logoff(request, response);
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Ré-authentification sur l'application.
     * 
     * Cette méthode supprime la référence éventuellement présente en session
     * de l'objet utilisateur et lance la ré-authentification Cerbère.
     * 
     * @param request requête HTTP courante.
     * @param response réponse HTTP courante.
     * @param url adresse de redirection sur l'application.
     */
    public void reconnexion(HttpServletRequest request, HttpServletResponse response, String url)
            throws IOException {
        try {
            // Effacement de l'objet en session
            clearSession(request);
            // Réauthentification
            cerbere.reAuthentification(request, response, url);
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
     * Obtention  d'un pointeur sur l'objet Cerbère courant.
     * 
     * @return objet Cerbere associé à l'utilisateur
     */
    public Cerbere getCerbere() {
        return cerbere;
    }
}
