package fr.eni.javaee.encheres.dal;

import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;

public interface ArticleDAO {


	// -------------------ARTICLES-------------------//

	// Mettre en vente un article / mettre aux encheres
	public void insertArticle(ArticleVendu art) throws BusinessException;
	//Select toutes Les ventes
	public List<ArticleVendu> liste() throws BusinessException;
	//Vente par USER
	public List<ArticleVendu> selectByUser(int id) throws BusinessException;
	//Select vente par Article ID
	public ArticleVendu selectById(int id) throws BusinessException;
	//Selection par categorie
	public List<ArticleVendu> listeSelectByCat(int noCategorie) throws BusinessException;
	//Selection par nom
	public List<ArticleVendu> listeSelectByName(String name) throws BusinessException;
	
	public List<ArticleVendu> listeSelectByNameAndCat(String name, int noCategorie) throws BusinessException;


}
