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

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bll.ArticleManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;

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
		
     ArticleManager am = new ArticleManager();
	     List<ArticleVendu> avs;
		try {
			avs = am.selectAll();
			request.setAttribute("avs", avs);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     request.getRequestDispatcher("/WEB-INF/pages/accueilNonConnecte.jsp").forward(request, response);
   

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

			String rechercheName = request.getParameter("nomArticle");
	        String rechercheCat = request.getParameter("categorie");
	        
	        try {
	            ArticleManager am = new ArticleManager();
	            List<ArticleVendu> avs = am.selectArticle(rechercheName, Integer.parseInt(rechercheCat));
	            System.out.println("avs : " + avs);
	            
	            // met les attributs
	            request.setAttribute("avs", avs);
	        } catch ( BusinessException e) {
	        	System.out.println("in erreur");
	            e.printStackTrace();
	            request.setAttribute("avs", new ArrayList<ArticleVendu>());
	            request.setAttribute("listeCodesErreur",e.getMessage());
	        }
	        
	        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/accueilNonConnecte.jsp");
	        rd.forward(request, response);

	    }

	}


