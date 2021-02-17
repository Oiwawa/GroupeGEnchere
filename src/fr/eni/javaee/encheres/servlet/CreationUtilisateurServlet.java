package fr.eni.javaee.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bll.UserManager;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.CodeResultatDal;
import fr.eni.javaee.encheres.messages.LecteurMessage;

/**
 * Servlet implementation class CreationUtilisateur
 */
@WebServlet("/CreationUtilisateur")
public class CreationUtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/creationUtilisateur.jsp").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Creation de l'utilisateur
		Utilisateur user = new Utilisateur();
		// Liste des erreurs
		List<Integer> listeCodesErreur = new ArrayList<>();
		// session
		
		BusinessException businessException = new BusinessException();
		try {
			// Recuperation des champs duformulaire
			String pseudo = request.getParameter("pseudo");
			String prenom = request.getParameter("prenom");
			String telephone = request.getParameter("telephone");
			int codepostal = Integer.parseInt(request.getParameter("codepostal"));
			String mdp = request.getParameter("mdp");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String rue = request.getParameter("rue");
			String ville = request.getParameter("ville");
			String confirmation = request.getParameter("confirmation");

			// Creation de l'utilisateur
			user.setPrenom(prenom);
			user.setPseudo(pseudo);
			user.setTelephone(telephone);
			user.setCodePostal(codepostal);
			user.setMotDePasse(mdp);
			user.setNom(nom);
			user.setEmail(email);
			user.setRue(rue);
			user.setVille(ville);

			try {
				UserManager.inscription(user, confirmation);
				request.setAttribute("Utilisateur", user);
				HttpSession session = request.getSession();
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/creationUtilisateur.jsp");
				rd.forward(request, response);

			} catch (BusinessException e) {
				e.printStackTrace();
				List<Integer> listeCodesErreursManager = e.getListeCodesErreur(); // Recupere la liste de code erreur
																					// associe a l'exception qui arrive
																					// du manager
				int codeErreur = listeCodesErreursManager.get(0); // recupere le 1er code de la liste d'erreur
				String msg = LecteurMessage.getMessageErreur(codeErreur); // tranforme le code d'erreur en son message
				request.setAttribute("message", msg); // ajoute msg comme attribut de la request pour la JSP
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/creationUtilisateur.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Erreur de l'application.");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/creationUtilisateur.jsp");
		}

	}
}
