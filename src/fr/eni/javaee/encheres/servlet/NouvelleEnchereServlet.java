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
import fr.eni.javaee.encheres.bll.CategorieManager;
import fr.eni.javaee.encheres.bll.RetraitManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
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
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/nouvelleEnchere.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération des chaines encodages UTF-8
		request.setCharacterEncoding("UTF-8");

		List<Integer> listeCodesErreur = new ArrayList<>();
		// Création de l'article
		ArticleVendu art = new ArticleVendu();
		try {

			// Variables de récupération ----------------
			// Recuperation du nom de l'article
			String recupNom = request.getParameter("sarticle");

			// Recuperation de la description
			String recupDesc = request.getParameter("sdescription");

			// Recuperation du prix
			int recupPrix = Integer.parseInt(request.getParameter("sprix"));

			// Recuperation des categorie
			int categorieId = Integer.parseInt(request.getParameter("scategorie"));
			Categorie categorie = CategorieManager.selectCatById(categorieId);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
			// Recuperation date début encheres

			LocalDate recupDateDeb = LocalDate.parse(request.getParameter("sdatedeb"), dtf);

			// Recuperation date fin d'encheres
			LocalDate recupDateFin = LocalDate.parse(request.getParameter("sdatefin"), dtf);

			// Recuperation de la rue de la ville et code postal
			String rue = request.getParameter("srue");
			String ville = request.getParameter("sville");
			String codePostal = request.getParameter("scodepostal");

			// Création du retrait
			Retrait retrait = new Retrait();
			retrait.setVille(ville);
			retrait.setCodePostal(codePostal);
			retrait.setRue(rue);

			RetraitManager.addRetrait(retrait);

			art.setNomArticle(recupNom);
			art.setDescription(recupDesc);
			art.setDateDebutEncheres(recupDateDeb);
			art.setDateFinEncheres(recupDateFin);
			art.setMiseAPrix(recupPrix);
			art.setNoCategorie(categorie);
			art.setLieuRetrait(retrait);

			// Réalisation du traitement

			if (listeCodesErreur.size() > 0) {
				request.setAttribute("listeCodesErreur", listeCodesErreur);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/nouvelleVente.jsp");
				rd.forward(request, response);
			} else {

				try {
					ArticleManager mger = new ArticleManager();
					mger.insertArticle(art);
					request.setAttribute("ArticleAffiche", art);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/modifierVente.jsp");
					rd.forward(request, response);

				} catch (Exception e) {
					// Si probleme regle pas respecter = renvoie vers la page + affichage erreurs
					e.printStackTrace();
					request.setAttribute("listeCodeErreur", ((BusinessException) e).getListeCodesErreur());
					RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/nouvelleVente.jsp");
					rd.forward(request, response);
				}
				// TODO changer la redirection vers la page avec bouton modification si tout se
				// passe bien

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test erreur date");

		}
	}
}
