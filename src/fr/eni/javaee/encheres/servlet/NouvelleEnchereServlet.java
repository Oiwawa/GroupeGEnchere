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
import fr.eni.javaee.encheres.bll.RetraitManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Retrait;

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
		// Récupération des chaines encodages UTF-8
		request.setCharacterEncoding("UTF-8");
		StringBuilder sb = new StringBuilder();

		List<Integer> listeCodesErreur = new ArrayList<>();
		// Création de l'article
		ArticleVendu art = new ArticleVendu();

		// Variables de récupération ----------------
			// Recuperation du nom de l'article
		String recupArticle = request.getParameter("sarticle");
		
			// Recuperation de la description
		String recupDesc = request.getParameter("sdescription");
		
			// Recuperation du prix
		int recupPrix =  Integer.parseInt(request.getParameter("sprix"));
		
			// Recuperation des categorie
		int categorieId =  Integer.parseInt(request.getParameter("scateg"));
		
		// Recuperation date début encheres
		LocalDate recupDateDeb = LocalDate.parse("sdatedeb");
		
		// Recuperation date fin d'encheres
		LocalDate recupDateFin = LocalDate.parse("sdatefin");
		
		//Recuperation de la rue de la ville et code postal
		String rue=request.getParameter("srue");
		String ville = request.getParameter("sville");
		String codePostal = request.getParameter("scodepostal");
		
		String recupCateg = request.getParameter("scategorie");
		Retrait retrait = new Retrait();
		
		retrait.setVille(ville);
		retrait.setCodePostal(codePostal);
		retrait.setRue(rue);
		
		//RetraitManager.addRetrait(retrait);
	
			
				
			
		// Réalisation du traitement

		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/nouvelleVente.jsp");
			rd.forward(request, response);
		} else  {

			try {
				ArticleManager articleManager = new ArticleManager();
			//	articleManager.insertArticle(recupArticle, recupDesc, categ, recupDateDeb,recupDateFin, prix, prixVente, recupEtat);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/modifierVente.jsp");
				rd.forward(request, response);
				
			} catch (Exception  e) {
				//Si probleme regle pas respecter = renvoie vers la page + affichage erreurs
				e.printStackTrace();
			//request.setAttribute("listeCodeErreur", e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/nouvelleVente.jsp");
			rd.forward(request, response);
			}
			// TODO changer la redirection vers la page avec bouton modification si tout se passe bien

		}
	}
}
