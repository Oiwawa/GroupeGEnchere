package fr.eni.javaee.encheres.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bll.ArticleManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.dal.DALException;

/**
 * Servlet implementation class accueilNonConnecteServlet
 */
@WebServlet("/Accueil.html")
public class accueilNonConnecteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/accueilNonConnecte.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        System.out.println("hello post accueilNonConnecteServlet");
	        String rechercheArt = request.getParameter("nomArticle");
	        String rechercheCat = request.getParameter("categorie");
	        System.out.println("categorie : " + rechercheCat);
	        System.out.println("nomArticle : " + rechercheArt);

	        try {
	            ArticleManager am = new ArticleManager();
	            ArticleVendu av = (ArticleVendu) am.getAllArticles();
	            request.setAttribute(rechercheArt, av);
	            request.setAttribute(rechercheCat, av);

	        } catch ( DALException | SQLException e) {
	            e.printStackTrace();
	            //   request.setAttribute("listeCodesErreur",e.getListeCodesErreur());

	        }

	        javax.servlet.RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/accueilNonConnecte.jsp");
	        rd.forward(request, response);

	    }

	}


