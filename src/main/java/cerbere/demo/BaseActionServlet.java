package cerbere.demo;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe de base pour l'ensemble des actions de l'application. L'objet de
 * sécurité Cerbère est géré ici.
 */
public abstract class BaseActionServlet extends HttpServlet {
    /** Identifiant de version */
    private static final long serialVersionUID = 1L;
    /** Contexte de la servlet */
    protected ServletContext context;
    /** Gestionnaire de traces */
    private static final Logger LOG = LoggerFactory.getLogger(BaseActionServlet.class);
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }
        
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Vérification de la présence du jeton utilisateur.
        User usr = (User) request.getSession().getAttribute("user");
        // Objet Cerbère absent => création.
        if(usr == null) {
            usr = new User();
            request.getSession().setAttribute("user", usr);
            LOG.info("Objet Cerbère créé : {}", usr.getCerbere().getUtilisateur().getMel());
        }

        // Délégation de la suite des traitements au métier.
        process(request, response);
    }

    /**
     * Méthode spécifique à chaque action.
     * 
     * @param request requête HTTP courante.
     * @param response réponse HTTP courante.
     * @throws ServletException 
     * @throws IOException 
     */
    public abstract void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}

