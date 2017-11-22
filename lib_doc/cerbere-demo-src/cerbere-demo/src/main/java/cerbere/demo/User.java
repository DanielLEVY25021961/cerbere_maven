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
 * <p>Cet objet masque les sp�cificit�s Cerb�re � l'application, except� sur la
 * m�thode getCerbere, utilis� ici pour les besoins de la d�monstration.</p>
 */
public class User implements Serializable {
    /** Identifiant de version */
    private static final long serialVersionUID = 1L;
    /** Objet Cerb�re */
    private Cerbere cerbere;

    /**
     * Construction des propri�t�s utilisateur.
     * 
     * Si un objet d�j� existant est trouv� en session, il est utilis�. Sinon
     * il est cr�e "� la vol�e" d'apr�s la requ�te courante.
     * 
     * @param request requ�te HTTP courante
     */
    public User(HttpServletRequest request) throws IOException {
        try {
            cerbere = Cerbere.creation(request);
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * D�sauthentification de l'application.
     * 
     * Cette m�thode supprime la r�f�rence �ventuellement pr�sente en session
     * de l'obkjet utilisateur et lance la d�sauthentification Cerb�re.
     * 
     * @param request requ�te HTTP courante.
     * @param response r�ponse HTTP courante.
     */
    public void deconnexion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Effacement de l'objet en session.
            clearSession(request);
            // d�sauthentification
            cerbere.logoff(request, response);
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * R�-authentification sur l'application.
     * 
     * Cette m�thode supprime la r�f�rence �ventuellement pr�sente en session
     * de l'objet utilisateur et lance la r�-authentification Cerb�re.
     * 
     * @param request requ�te HTTP courante.
     * @param response r�ponse HTTP courante.
     * @param url adresse de redirection sur l'application.
     */
    public void reconnexion(HttpServletRequest request, HttpServletResponse response, String url)
            throws IOException {
        try {
            // Effacement de l'objet en session
            clearSession(request);
            // R�authentification
            cerbere.reAuthentification(request, response, url);
        } catch (CerbereConnexionException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Suppression de l'objet en session.
     * 
     * @param request requ�te HTTP courante.
     */
    private void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        session.invalidate();
    }
    
    /**
     * Obtention  d'un pointeur sur l'objet Cerb�re courant.
     * 
     * @return objet Cerbere associ� � l'utilisateur
     */
    public Cerbere getCerbere() {
        return cerbere;
    }
}
