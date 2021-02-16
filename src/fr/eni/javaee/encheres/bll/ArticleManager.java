package fr.eni.javaee.encheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.EtatVente;
import fr.eni.javaee.encheres.dal.ArticleDAO;
import fr.eni.javaee.encheres.dal.DALException;
import fr.eni.javaee.encheres.dal.jdbcImpl.ArticleDAOJdbcImpl;

public class ArticleManager {

	private static ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
	private static ArticleVendu article = new ArticleVendu();
	private static BusinessException businessException = new BusinessException();

	// Methode
	public static ArticleVendu insertArticle(ArticleVendu article)
			throws BusinessException, DALException, SQLException {

		validerDate(article);
		validerNom(article);
		validerDescription(article);
		validerCategorie(article);
		validerPrix(article);
		if (!businessException.hasErreurs()) {
			articleDAO.insertArticle(article);
		} else {
			throw businessException;
		}
		return article;
	}

	/**
	 * Vérifie que le nom de l'article est valide
	 * @param article
	 */
	public static void validerNom(ArticleVendu article) {
		if (article.getNomArticle() == null  ||article.getNomArticle().trim().length() > 30 ) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_VENTE);
		}
	}
/**
 * vérifie que la description de l'article est valide
 * @param article
 */
	public static void validerDescription(ArticleVendu article) {
		if (article.getDescription() == null || article.getDescription().isBlank() || article.getDescription().trim().length()>300) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DESCRITPION_VENTE);
		}
	}
/**
 * Vérifie que la categorie selectionner est valide
 * @param article
 */
	public static void validerCategorie(ArticleVendu article) {
		if (article.getNoCategorie() == null) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_VENTE);
		}

	}

	/**
	 * Vérifie que le prix de mise en vente n'est pas = a 0
	 * @param article
	 */
	public static void validerPrix(ArticleVendu article) {
		if (article.getMiseAPrix() <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PRIX_DEPART_VENTE);

		}
	}

	// Test les dates (Début avant date du jour et fin avant début)
	public static void validerDate(ArticleVendu article) {
		if (article.getDateDebutEncheres() == null || article.getDateFinEncheres() == null
				|| article.getDateDebutEncheres().isBefore(LocalDate.now())
				|| article.getDateFinEncheres().isBefore(article.getDateDebutEncheres())) {

			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_ENCHERE_ARTICLE);
		}
	}

//public static void validerEtatVente(EtatVente etatVente ) throws BusinessException{
//	if(article.getEtatVenteEnum() == EtatVente.ENCHERE_TERMINEE) {
//		businessException.ajouterErreur(CodesResultatBLL.REGLE_ETAT_VENTE_ARTICLE);
//	}
//}

//--------------------------------------------------------------------
	// selecte vente par USER
	public List<ArticleVendu> selectByUser(int id) throws BusinessException {
		return ArticleManager.articleDAO.selectByUser(id);
	}

	// Select vente par Article ID
	public ArticleVendu selectById(int id) throws BusinessException {
		return ArticleManager.articleDAO.selectById(id);
	}

	public List<ArticleVendu> selectAll() throws BusinessException {
		return ArticleManager.articleDAO.liste();
	}

//-----------Methode pour le filtrage-------------------------//

	public List<ArticleVendu> selectArticle(String name, int noCategorie) throws BusinessException {

		if ((name == null) && (noCategorie == 0)) {
			return ArticleManager.articleDAO.liste();
		} else if ((name != null) && (noCategorie == 0)) {
			return ArticleManager.articleDAO.listeSelectByName(name);
		} else if ((name == null) && (noCategorie != 0)) {
			return ArticleManager.articleDAO.listeSelectByCat(noCategorie);
		} else if ((name != null) && (noCategorie != 0)) {
			return ArticleManager.articleDAO.listeSelectByNameAndCat(name, noCategorie);
		}
		return null;
	}

}
