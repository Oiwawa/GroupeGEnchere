package fr.eni.javaee.encheres.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bll.ArticleManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/NouvelleEnchere.html")
public class NouvelleEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NouvelleEnchereServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/nouvelleEnchere.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération des chaines encodées UTF-08
		request.setCharacterEncoding("UTF-08");
		StringBuilder sb = new StringBuilder();

		// Création de l'article
		ArticleVendu art = null;

		// Variables de récupération
		String recupArticle = null;
		String recupDesc = null;
		String recupPrix = null;
		String recupCateg = null;
		LocalDate recupDateDeb = null;
		LocalDate recupDateFin = null;
		String recupEtat = null;
		List<Integer> listeCodesErreur = new ArrayList<>();

		// Recuperation du nom de l'article
		recupArticle = request.getParameter("sarticle");
		if (recupArticle == null || recupArticle.trim().isEmpty()) {
			listeCodesErreur.add(CodesErreursServlets.CHAMPS_VIDE_ERREUR);

		}

		// Recuperation de la description
		recupDesc = request.getParameter("sdescription");
		if (recupDesc.length() > 300) {
			sb.append("La description de l'article ne doit pas dépasser 300 charactères.<br>");
		}
		// Recuperation des categories

		recupCateg = request.getParameter("scategorie");
		int categ = Integer.parseInt(recupCateg);
		if (categ <= 0) {
			listeCodesErreur.add(CodesErreursServlets.CHAMPS_VIDE_ERREUR);

		}

		// Recuperation du prix
		recupPrix = request.getParameter("sprix");
		float prix = Integer.parseInt(recupPrix);
		
		if (prix <= 0) {
			listeCodesErreur.add(CodesErreursServlets.CHAMPS_VIDE_ERREUR);
			
		}
		//Prix de la vente initialiser au prix de départ de l'article, sera changé à la fin de la vente
		float prixVente = prix;;

		// TEST
		System.out.println(recupPrix);

		// Recuperation date début encheres
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy");
			recupDateDeb = LocalDate.parse(request.getParameter("sdatedeb"), dtf);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesErreursServlets.DATE_DEBUT_ERREUR);
		}

		// Recuperation date fin d'encheres
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy");
			recupDateDeb = LocalDate.parse(request.getParameter("sdatefin"), dtf);
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesErreursServlets.DATE_FIN_ERREUR);
		}
		
		// Réalisation du traitement

		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/nouvelleVente.jsp");
			rd.forward(request, response);
		} else  {

			try {
				ArticleManager articleManager = new ArticleManager();
				articleManager.insertArticle(recupArticle, recupDesc, categ, recupDateDeb,recupDateFin, prix, prixVente, recupEtat);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/modifierVente.jsp");
				rd.forward(request, response);
				
			} catch (BusinessException  e) {
				//Si probleme regle pas respecter = renvoie vers la page + affichage erreurs
				e.printStackTrace();
			request.setAttribute("listeCodeErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/nouvelleVente.jsp");
			rd.forward(request, response);
			}
			// TODO changer la redirection vers la page avec bouton modification si tout se passe bien

		}
	}
}
