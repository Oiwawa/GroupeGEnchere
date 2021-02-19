package fr.eni.javaee.encheres.servlet;

import java.io.IOException;

import java.time.LocalDate;
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
import fr.eni.javaee.encheres.bll.ArticleManager;
import fr.eni.javaee.encheres.bll.CategorieManager;
import fr.eni.javaee.encheres.bll.ConnectionManager;
import fr.eni.javaee.encheres.bll.RetraitManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.messages.LecteurMessage;


@WebServlet("/VenteArticle")
public class NouvelleEnchereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/restreint/nouvelleEnchere.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Récupération des chaines encodages UTF-8
		request.setCharacterEncoding("UTF-8");

		// La session
		HttpSession session = request.getSession();
		Utilisateur user = null;
		// Recuperer l'utilisateur depuis la session ou la request
        if(session.getAttribute("user") != null) {
            user = (Utilisateur) session.getAttribute("user");
        } else if(request.getAttribute("user") != null) {
            user = (Utilisateur) request.getAttribute("user");
        } else {
        	
            // throw error
        }
		//Liste des erreurs
		List<Integer> listeCodesErreurs = new ArrayList<Integer>();
		
		// Liste des categories
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		
		try {
			//Récupération de toutes les catégories
			listeCategorie = CategorieManager.selectAllCat();
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		//Envoie de la liste à la JSP
		request.setAttribute("listeCategorie", listeCategorie);
		
		
		// Création de l'article
		ArticleVendu art = new ArticleVendu();
		try {

					// Recuperation du nom de l'article
			String recupNom = request.getParameter("sarticle");

					// Recuperation de la description
			String recupDesc = request.getParameter("sdescription");

					// Recuperation du prix
			int recupPrix = Integer.parseInt(request.getParameter("sprix"));

					// Recuperation des categorie
			int categorieId = Integer.parseInt(request.getParameter("scategorie"));
			Categorie categorie = CategorieManager.selectCatById(categorieId);

					// Recuperation date début encheres
			LocalDate recupDateDeb = LocalDate.parse(request.getParameter("sdatedeb"));

					// Recuperation date fin d'encheres
			LocalDate recupDateFin = LocalDate.parse(request.getParameter("sdatefin"));

					// Récupération de la session user
			
					// Recuperation de la rue de la ville et code postal
			String rue = request.getParameter("srue");
			String ville = request.getParameter("sville");
			String codePostal = request.getParameter("scodepostal");

					// Création du retrait ou adresse par defaut du vendeur
			if(rue == null || ville == null || codePostal ==null) {
				art.setLieuRetrait(user.getAdresseRetraitDefaut());
			} else {
			Retrait retrait = new Retrait();
			retrait.setVille(ville);
			retrait.setCodePostal(codePostal);
			retrait.setRue(rue);
			
					//Ajout du retrait dans la BDD
			RetraitManager.addRetrait(retrait);
					
			art.setLieuRetrait(retrait);
			}
			// Création de l'article
			art.setNomArticle(recupNom);
			art.setDescription(recupDesc);
			art.setDateDebutEncheres(recupDateDeb);
			art.setDateFinEncheres(recupDateFin);
			art.setMiseAPrix(recupPrix);
			art.setNoCategorie(categorie);
			art.setVendeur(user);

			// Réalisation du traitement

			try {
				ArticleManager.insertArticle(art);
				request.setAttribute("ArticleAffiche", art);
				RequestDispatcher rd = request.getRequestDispatcher("restreint/AccueilConnecte");
				rd.forward(request, response);

			} catch (BusinessException e) {
				listeCodesErreurs = e.getListeCodesErreur();
				 // Recupere la liste de code erreur associe a l'exception qui arrive du manager
					request.setAttribute("listeCodesErreurs", listeCodesErreurs); 
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/restreint/nouvelleEnchere.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Erreur de l'application.");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/restreint/nouvelleEnchere.jsp");
			rd.forward(request, response);

		}
	}
}
