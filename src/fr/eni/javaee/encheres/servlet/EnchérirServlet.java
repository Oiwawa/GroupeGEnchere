package fr.eni.javaee.encheres.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bll.ArticleManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;

/**
 * Servlet implementation class EnchérirServlet
 */
@WebServlet("/Encherir")
public class EnchérirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public EnchérirServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/encherir.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ArticleVendu articleDetail = null;
		
		int propPrix =Integer.parseInt(request.getParameter("sprix"));
		int idArticle = Integer.parseInt(request.getParameter("sIdArticle"));
		
		try {
			articleDetail = ArticleManager.selectById(idArticle);
			request.setAttribute("ArticleAffiche", articleDetail);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
