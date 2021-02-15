package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.dal.jdbcImpl.ArticleDAOJdbcImpl;
import fr.eni.javaee.encheres.dal.jdbcImpl.ConnectionDAOJdbcImpl;

public class DAOFactory {

	public static ArticleDAO getArticleDAO() {
		
		return  new ArticleDAOJdbcImpl();
	}
	
	public static ConnectionDAO getConnectionDAO() {
		return new ConnectionDAOJdbcImpl();
	}
	
}
