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
	private ArticleVendu art = new ArticleVendu();
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
	public ArticleVendu insertArticle(ArticleVendu article) throws BusinessException {
		this.validerNom(recupArticle, businessException);
		this.validerDescription(recupDesc, businessException);
		this.validerCategorie(categ, businessException);
		this.validerDateDebut(recupDateDeb, businessException);
		this.validerDateFin(recupDateFin, businessException);
		this.validerPrix(prix, businessException);
		if (!businessException.hasErreurs()) {
			art.setNomArticle(recupArticle);
			art.setDescription(recupDesc);
			art.setDateDebutEncheres(recupDateDeb);
			art.setDateFinEncheres(recupDateFin);
			art.setMiseAPrix(prix);
			art.setPrixVente(prix);
			art.setEtatVente(etatVente);
			
			// TODO recuperer la categorie
			//this.articleDAO.insertArticle(art);
		} else {
			throw businessException;
		}
		return art;
	}

	private void validerNom(String recupArticle, BusinessException businessException) {
		if (recupArticle.trim().length() > 30) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_VENTE);
		}
	}

	private void validerDescription(String recupDesc, BusinessException businessException) {
		if (recupDesc == null || recupDesc.isBlank()) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DESCRITPION_VENTE);
		}
	}

	private void validerCategorie(int categorie, BusinessException businessException) {
		if (categorie <= 0 ) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_CATEGORIE_VENTE);
		}

	}

	public void validerDateDebut(LocalDate recupDateDeb, BusinessException businessException) {
		if (recupDateDeb == null || recupDateDeb.isBefore(LocalDate.now())) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_DEBUT_VENTE);
		}
	}

	private void validerDateFin(LocalDate recupDateFin, BusinessException businessException) {
		if (recupDateFin == null || recupDateFin.isAfter(LocalDate.now().plusDays(1))) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_FIN_VENTE);
		}
	}

	private void validerPrix(float prix, BusinessException businessException) {

		if (prix <= 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PRIX_DEPART_VENTE);

		}
	}

//--------------------------------------------------------------------
	
	public List<ArticleVendu> getAllArticles() throws DALException, SQLException{
        return this.articleDAO.liste();
    }

    public ArticleVendu selectName (String name) throws BusinessException, DALException, SQLException {
        return this.articleDAO.selectByName(name);
    }
}