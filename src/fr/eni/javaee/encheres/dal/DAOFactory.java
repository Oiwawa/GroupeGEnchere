package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.dal.jdbcImpl.ArticleDAOJdbcImpl;

public class DAOFactory {

	public static ArticleDAO getArticleDAO() {
		ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
		return articleDAO;
	}
	
}
