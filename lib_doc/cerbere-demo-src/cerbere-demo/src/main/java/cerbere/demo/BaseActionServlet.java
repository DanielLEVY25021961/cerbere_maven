package cerbere.demo;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Classe de base pour l'ensemble des actions de l'application. L'objet de
 * s�curit� Cerb�re est g�r� ici.
 */
public abstract class BaseActionServlet extends HttpServlet {
    /** Identifiant de version */
    private static final long serialVersionUID = 1L;
    /** Contexte de la servlet */
    protected ServletContext context;
    /** Gestionnaire de traces */
    private static Logger logger = Logger.getLogger(BaseActionServlet.class);
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        context = config.getServletContext();
    }
        
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // V�rification de la pr�sence du jeton utilisateur.
        User usr = (User) request.getSession().getAttribute("user");
        // Objet Cerb�re absent => cr�ation.
        if(usr == null) {
            usr = new User(request);
            request.getSession().setAttribute("user", usr);
            logger.info("Objet Cerb�re cr�� : " + usr.getCerbere().getUtilisateur().getMel());
        }

        // D�l�gation de la suite des traitements au m�tier.
        process(request, response);
    }

    /**
     * M�thode sp�cifique � chaque action.
     * 
     * @param request requ�te HTTP courante.
     * @param response r�ponse HTTP courante.
     */
    public abstract void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}

