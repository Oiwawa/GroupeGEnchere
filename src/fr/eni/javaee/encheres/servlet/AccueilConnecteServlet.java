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
import fr.eni.javaee.encheres.bll.CategorieManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;

/**
 * Servlet implementation class AccueilConnecteServlet
 */
@WebServlet("/AccueilConnecte")
public class AccueilConnecteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilConnecteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ArticleManager am = new ArticleManager();
		     List<ArticleVendu> avs = new ArrayList<ArticleVendu>();
			try {
				avs = am.selectAll();
				request.setAttribute("avs", avs);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("/WEB-INF/pages/accueilConnecte.jsp").forward(request, response);
		     
	   

		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
			protected void doPost(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		        List<Categorie> listeCategorie = new ArrayList<Categorie>();
		        List<ArticleVendu> avs = new ArrayList<ArticleVendu>();
	            ArticleManager am = new ArticleManager();

				try {
					listeCategorie = CategorieManager.selectAllCat();
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				request.setAttribute("listeCategorie", listeCategorie);
				
				
				
				String rechercheName = request.getParameter("nomArticle");
				int categorieId = Integer.parseInt(request.getParameter("scategorie"));
		        
		        System.out.println("categorie : " + categorieId);
		        System.out.println("nomArticle : " + rechercheName);

		        try {
		             avs = am.selectArticle(rechercheName, categorieId);
		            
		            // met les attributs
		            request.setAttribute("avs", avs);
		        } catch ( BusinessException e) {
		            e.printStackTrace();
		            request.setAttribute("avs", new ArrayList<ArticleVendu>());
		            request.setAttribute("listeCodesErreur",e.getMessage());
		        }
		        
		        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/accueilConnecte.jsp");
		        rd.forward(request, response);

		    }

		}
	

