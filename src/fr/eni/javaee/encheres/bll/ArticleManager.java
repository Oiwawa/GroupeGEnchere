package fr.eni.javaee.encheres.bll;

import java.sql.SQLException;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.dal.ArticleDAO;
import fr.eni.javaee.encheres.dal.DALException;
import fr.eni.javaee.encheres.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDAO = new DAOFactory().getArticleDAO();
	private static ArticleManager instance;
	
	//Constructeur
	public ArticleManager() throws BLLException {
	}

	public static ArticleManager getInstance() throws BLLException {
		if(instance == null) {
			instance = new ArticleManager();
		} 
		return instance;
	}
	
	
	//Methode
	public ArticleVendu insertArticle(ArticleVendu art) throws BLLException {
		try {
			this.articleDAO.insertArticle(art);
		} catch(DALException | SQLException e) {
			throw new BLLException("Echec d'insertArticle", e);
		}
		return art;
	} 
}