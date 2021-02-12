package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.dal.DBConnexion;
import fr.eni.javaee.encheres.dal.ArticleDAO;
import fr.eni.javaee.encheres.dal.CodeResultatDal;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	// Constantes
	private static final String INSERT = "INSERT INTO article_vendu "
			+ "(nom_article, description, date_debut_encheres, date_fin_encheres, "
			+ "prix_initial, no_utilisateur, no_categorie, no_retrait " + "VALUES (?,?,?,?,?,?,?,?) ";
	private static final String SQL_SELECT_BY_NAME = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait " + " from ARTICLES_VENDUS "
			+ "where nom_article = ?";
	private static final String SQL_SELECT_BY_CAT = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, "
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait " + " from ARTICLE_VENDUS "
			+ "where no_categorie = ?";
	private static final String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres,"
			+" prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait"
			+ "from ARTICLE_VENDUS"
			+ "where nom_article LIKE '%?%'"
			+ "where no_categorie = ?";


	//Insert un nouvel article
	@Override
	public void insertArticle(ArticleVendu article) throws BusinessException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		//Si l'article n'existe pas, création d'une erreur
		if(article ==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.INSERT_OBJET_NULL);
		}

		//Test connexion, si bon = traitement et 
		try (Connection cnx = DBConnexion.seConnecter()){
			cnx.setAutoCommit(false);
			pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(article.getDateFinEncheres()));
			pstmt.setFloat(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getVendeur().getNoUtilisateur());
			pstmt.setInt(7, article.getNoCategorie().getNoCategorie());
			pstmt.setInt(8, article.getLieuRetrait().getIdRetrait());

			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}

			rs.close();

			pstmt.close();
			cnx.commit();

			//Si la connexion est un echec, renvoie une erreur echec
		} catch (Exception e) {
			//Exception est plus général que DAL ou BLLExecp 
			e.printStackTrace();
			//Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.INSERT_OBJET_ECHEC);
			throw businessException;

	}
	}



	//Liste de tous les articles en vente
	@Override
	public List<ArticleVendu> liste() throws BusinessException {

		List<ArticleVendu> listeArticleEnVente = new ArrayList<ArticleVendu>();
		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				ArticleVendu av = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"), rs.getFloat("prix_vente"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait"));

				listeArticleEnVente.add(av);
			}
		} catch (Exception e) {
			//Exception est plus général que DAL ou BLLExecp 
			e.printStackTrace();
			//Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		return listeArticleEnVente;
	}

	

	//Select un article par son nom (pour le filtrage)
	@Override
	public ArticleVendu selectByName(String name) throws BusinessException {
		ResultSet rs = null;
		ArticleVendu art = null;

		try (Connection cnx = DBConnexion.seConnecter();
				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_NAME);) {
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				art = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), 0, rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"),
						rs.getFloat("prix_vente"));
			}
		} catch (Exception e) {
			//Exception est plus général que DAL ou BLLExecp 
			e.printStackTrace();
			//Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		return art;
	}

	//Select un article par sa categorie (pour le filtrage)
	@Override
	public Categorie selectByCat(int noCategorie) throws BusinessException {
		ResultSet rs = null;
		Categorie cat = null;

		try (Connection cnx = DBConnexion.seConnecter();
				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_CAT);) {
			pstmt.setInt(1, noCategorie);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cat = new Categorie(rs.getInt("noCategorie"), rs.getString("libelle"));
			}
		} catch (Exception e) {
			//Exception est plus général que DAL ou BLLExecp 
			e.printStackTrace();
			//Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		return cat;
	}

	//*****************************************************************
	//Select un article par User (pour affichage article en vente sur profil)
	@Override
	public List<ArticleVendu> selectByUser(int id) throws BusinessException {

		
		
		return null;
	}
	//*****************************************************************
	//Select un article par son ID 
	@Override
	public ArticleVendu selectById(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
