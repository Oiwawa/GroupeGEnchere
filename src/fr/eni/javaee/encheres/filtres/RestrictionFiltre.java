package fr.eni.javaee.encheres.filtres;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFiltre implements Filter {
	
	public static final String ACCES_CONNECTION = "/ServletConnection";
	
	public void init(FilterConfig config) throws  ServletException{
		
	}

	//Realiser une verification
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		/*Cast des objets request et response*/
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//Non filtrage des ressources statique 
		//Recuperation de l'url d'appel a la requete HTTP via la methode getRequestURI
		//On place sa partie final dans la chaine chemin (partie apres le port 8080)
		String chemin = request.getRequestURI().substring(request.getContextPath().length());
		
		
		/*Si la chaine chemin commence par /inc , la page demande est une des ressources statiques
		 * placees sous le repertoire inc  = il ne faut donc pas lui apporter le filtre
		 * */ 
		if(chemin.startsWith("/inc")) {
		//On laisse la requete poursuivre son cheminement
			chain.doFilter(request, response);
			return;
		}
		
		
		//Recuperation de la session depuis la requete 
		HttpSession masession = request.getSession();
		
		System.out.println("filtre : user : " + masession.getAttribute("user"));
		if(masession.getAttribute("user")==null){
			/*Redirection vers la page public */
			response.sendRedirect(request.getContextPath() + ACCES_CONNECTION);
		}else {
			//Affichage de la page restreinte
			chain.doFilter(request, response);
		}
	}
	
	public void destroy() {
		
	}
	
	

}
