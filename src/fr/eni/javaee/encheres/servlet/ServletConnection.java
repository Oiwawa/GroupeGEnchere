package fr.eni.javaee.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.bll.ConnectionManager;
import fr.eni.javaee.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnection
 */
@WebServlet("/ServletConnection")
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		// recuperation de la session -- identifiant stocke en memoire dans la methode doGet
//		HttpSession maSession = request.getSession();
//		maSession.getAttribute("identifiant");
//		// Recuperation des infos stocke dans les cookies - renvoie tout les cookies de l'utilisateur stocke pour ce site
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				// verification que le cookie est bien le cookie identifiant
//				if (cookie.getName().equals("identifiant")) {
//					// recuperation de la valeur du cookie
//					request.setAttribute("identifiant", cookie.getValue());
//				}
//			}
//		}
		System.out.println("Passage dans la methode doGet");
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageConnection.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Passage dans la methode doPost");

		// Recuperation des parametres de la requete.
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");

		try {

			new ConnectionManager();
			ConnectionManager cm = ConnectionManager.getInstance();
			Utilisateur user = cm.connecterUser(identifiant, mdp);
			request.setAttribute("user", user);

//			// Soumettre les parametres de la requete a la couche service et recuperation du resultat
//			HttpSession maSession = request.getSession();
//			/* Mise en session d'un utilisateur */
//			maSession.setAttribute("user", user);
//		
//			
//			
//			// Stockage dans des cookies cote visiteur
//			Cookie cookie = new Cookie("identifiant", identifiant);
//			// defini l'age d'expiration en seconde. Garde les infos enregistre pdt ce temps. Ici dure 1jour
//			cookie.setMaxAge(60 * 60 * 24);
//			response.addCookie(cookie);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/resultatLogin.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			System.out.println("in erreur :" + e.getMessage());
			request.setAttribute("message", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/pageConnection.jsp");
			rd.forward(request, response);
		}

		// Reponse a l'utilisateur
		

	}

}
