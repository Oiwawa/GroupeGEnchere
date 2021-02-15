package fr.eni.javaee.encheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.dal.ArticleDAO;
import fr.eni.javaee.encheres.dal.DALException;
import fr.eni.javaee.encheres.dal.DAOFactory;
import fr.eni.javaee.encheres.dal.jdbcImpl.ArticleDAOJdbcImpl;

public class ArticleManager {

	private ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
	private ArticleVendu article = new ArticleVendu();
	BusinessException businessException = new BusinessException();
	
	
	private static ArticleManager instance;

	// Constructeur
	
	public ArticleManager() {
	
	}

	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}

	// Methode
	public ArticleVendu insertArticle(ArticleVendu article) throws BusinessException, DALException, SQLException {
		
		validerDate(article);
		article.setEtatVente(null);
		if(!businessException.hasErreurs()) {
			articleDAO.insertArticle(article);
		} else {
			throw businessException;
		}
		return article;
	}
//*********************** A faire ou pas? la gestion de taille peut être géré par le html (max length par exemple)
//	private void validerNom(String recupArticle, BusinessException businessException) {
//		if (recupArticle.trim().length() > 30) {
//			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_VENTE);
//		}
//	}
//
//	private void validerDescription(String recupDesc, BusinessException businessException) {
//		if (recupDesc == null || recupDesc.isBlank()) {
//			businessException.ajouterErreur(CodesResultatBLL.REGLE_DESCRITPION_VENTE);
//		}
//	}
//
//	private void validerCategorie(int categorie, BusinessException businessException) {
//		if (categorie <= 0 ) {
//			businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_VENTE);
//		}
//
//	}
//	private void validerPrix(float prix, BusinessException businessException) {
//		
//		if (prix <= 0) {
//			businessException.ajouterErreur(CodesResultatBLL.REGLE_PRIX_DEPART_VENTE);
//			
//		}
//	}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

	//Test les dates (Début avant date du jour et fin avant début)
public void validerDate(ArticleVendu article) {
	if(article.getDateDebutEncheres() == null || article.getDateFinEncheres() == null 
			|| article.getDateDebutEncheres().isBefore(LocalDate.now()) 
			|| article.getDateFinEncheres().isBefore(article.getDateDebutEncheres()) ) {

		businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ARTICLE);
	}
}





//--------------------------------------------------------------------
	//selecte vente par USER
	public List<ArticleVendu> selectByUser(int id) throws BusinessException {
		return this.articleDAO.selectByUser(id);
	}
	//Select vente par Article ID
	public ArticleVendu selectById(int id) throws BusinessException {
		return this.articleDAO.selectById(id);
	}
	
	public List<ArticleVendu> selectAll() throws BusinessException{
		return this.articleDAO.liste();
	}
	
//-----------Methode pour le filtrage-------------------------//
	
	public List<ArticleVendu> selectArticle (String name, int noCategorie) throws BusinessException {
		
		if ((name==null) && (noCategorie==0)) {
	          return this.articleDAO.liste();
		}
		else if ((name!=null) && (noCategorie==0)) {
	        return this.articleDAO.listeSelectByName(name);
		}
		else if ((name==null) && (noCategorie!=0)) {
	        return this.articleDAO.listeSelectByCat(noCategorie);
		}
		else if ((name!=null) && (noCategorie!=0)) {
	        return this.articleDAO.listeSelectByNameAndCat(name, noCategorie);
		}
		return null;
	}
   
  
    
  	
}
    
   
    
  