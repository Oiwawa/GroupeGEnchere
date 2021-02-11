package fr.eni.javaee.encheres.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.dal.ArticleDAO;
import fr.eni.javaee.encheres.dal.DALException;
import fr.eni.javaee.encheres.dal.DAOFactory;

public class ArticleManager {

	private ArticleDAO articleDAO;
	private static ArticleManager instance;

	// Constructeur
	public ArticleManager() {
		this.articleDAO = new DAOFactory().getArticleDAO();
	}

	public static ArticleManager getInstance() {
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	public void insertArticle(String recupArticle, String recupDesc, LocalDate recupDateDeb, LocalDate recupDateFin,
			float prix, float prixVente, String recupEtat) {
		// TODO Auto-generated method stub
		
	}

	// Methode
	public ArticleVendu insertArticle(String recupArticle, String recupDesc, int categ, LocalDate recupDateDeb,
			LocalDate recupDateFin, float prix, float prixVente, String etatVente) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.validerNom(recupArticle, businessException);
		this.validerDescription(recupDesc, businessException);
		this.validerCategorie(categ, businessException);
		this.validerDateDebut(recupDateDeb, businessException);
		this.validerDateFin(recupDateFin, businessException);
		this.validerPrix(prix, businessException);
		ArticleVendu art = null;
		if (!businessException.hasErreurs()) {
			art = new ArticleVendu();
			art.setNomArticle(recupArticle);
			art.setDescription(recupDesc);
			art.setNoCategorie(categ);
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