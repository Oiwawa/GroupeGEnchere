package fr.eni.javaee.encheres.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.bo.Retrait;

public interface ArticleDAO {


	// -------------------ARTICLES-------------------//

	// Mettre en vente un article / mettre aux encheres
	public void insertArticle(ArticleVendu art) throws BusinessException, DALException, SQLException;
	//Select toutes Les ventes
	public List<ArticleVendu> liste() throws DALException, SQLException;
	//Vente par USER
	public List<ArticleVendu> selectByUser(int id) throws BusinessException;
	//Select vente par Article ID
	public ArticleVendu selectById(int id) throws BusinessException;
	//Selection par categorie
	public Categorie selectByCat(int noCategorie) throws DALException, SQLException;
	//Selection par nom
	public ArticleVendu selectByName(String name) throws DALException, SQLException;


}
