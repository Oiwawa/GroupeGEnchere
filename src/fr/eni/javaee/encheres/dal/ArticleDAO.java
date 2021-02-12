package fr.eni.javaee.encheres.dal;

import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;

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
	public Categorie selectByCat(int noCategorie) throws BusinessException;
	//Selection par nom
	public ArticleVendu selectByName(String name) throws BusinessException;


}
