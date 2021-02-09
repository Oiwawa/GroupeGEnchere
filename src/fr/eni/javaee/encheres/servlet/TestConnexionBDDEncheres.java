package fr.eni.javaee.encheres.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.dal.DALException;
import fr.eni.javaee.encheres.dal.DBConnexion;

/**
 * Servlet implementation class TestConnexionBDDEncheres
 */
@WebServlet("/TestConnexionBDDEncheres")
public class TestConnexionBDDEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestConnexionBDDEncheres() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection cnx=null;
		try {
			cnx = DBConnexion.seConnecter();
			response.getWriter().append("Connexion ok");
		} catch (DALException e) {
			response.getWriter().append("Connexion KO").append("\n").append(e.getMessage());
		}finally {
			try {
				DBConnexion.seDeconnecter(cnx);
			} catch (DALException e) {
				response.getWriter().append("Probleme libération connexion").append("\n").append(e.getMessage());

			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
