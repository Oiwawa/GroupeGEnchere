package fr.eni.javaee.encheres.dal;

import java.sql.SQLException;
import java.time.LocalDate;

import fr.eni.javaee.encheres.bo.ArticleVendu;

public class TestDal {

	public static void main(String[] args) throws DALException, SQLException {
		ArticleVendu a1 = new ArticleVendu(1, "nomTest", "Voici une description test", LocalDate.now(),LocalDate.now().plusDays(5), 10, 25, "Bon etat");
		System.out.println(a1.toString());
		
		System.out.println();
		
		DAOFactory.getArticleDAO().insertArticle(a1);
		System.out.println(a1);
	}
}
