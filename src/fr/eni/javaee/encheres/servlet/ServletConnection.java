package fr.eni.javaee.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bll.ConnectionManager;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.messages.LecteurMessage;

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
		// recuperation de la session -- identifiant stocke en memoire dans la methode
		// doGet
		HttpSession maSession = request.getSession();
		maSession.getAttribute("user");

		// Recuperation des infos stocke dans les cookies - renvoie tout les cookies de
		// l'utilisateur stocke pour ce site

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				// verification que le cookie est bien le cookie identifiant
				if (cookie.getName().equals("identifiant")) {
					// recuperation de la valeur du cookie
					request.setAttribute("identifiant", cookie.getValue());
				}
			}
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/pageConnection.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Integer> listeCodesErreur = new ArrayList<>();
		BusinessException businessException = new BusinessException();

		// Recuperation des parametres de la requete.
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");

		try {
			new ConnectionManager();
			ConnectionManager cm = ConnectionManager.getInstance();
			Utilisateur user = cm.connecterUser(identifiant, mdp);

			HttpSession maSession = request.getSession();
			request.setAttribute("user", user);
			maSession.setAttribute("user", user);

			// Reponse a l'utilisateur
			RequestDispatcher rd = request.getRequestDispatcher("restreint/AccueilConnecte");
			rd.forward(request, response);

		} catch (BusinessException e) {
			e.printStackTrace();

			HttpSession maSession = request.getSession();
			maSession.setAttribute("user", null);

			List<Integer> listeCodesErreursManager = e.getListeCodesErreur(); // Recupere la liste de code erreur
			// associe a l'exception qui arrive
			// du manager
			String msg = "";
			for (Integer codeErreur : listeCodesErreursManager) {
				msg += LecteurMessage.getMessageErreur(codeErreur) + "</br>"; // tranforme le code d'erreur en son
																				// message
			}
			request.setAttribute("message", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/pageConnection.jsp");
			rd.forward(request, response);
		}

		/*
		 * // Stockage dans des cookies cote visiteur Cookie cookie = new
		 * Cookie("identifiant", identifiant); // defini l'age d'expiration en seconde.
		 * // Garde les infos enregistre pdt ce temps. Ici dure 1jour
		 * cookie.setMaxAge(60 * 60 * 24); response.addCookie(cookie);
		 */

	}

}
