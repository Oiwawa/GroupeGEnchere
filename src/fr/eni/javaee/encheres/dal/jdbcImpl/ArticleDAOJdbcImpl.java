package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.DBConnexion;
import fr.eni.javaee.encheres.dal.ArticleDAO;
import fr.eni.javaee.encheres.dal.CodeResultatDal;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	// Constantes
	private static final String INSERT = "INSERT INTO articles_vendus "
			+ "(nom_article, description, date_debut_encheres, date_fin_encheres, "
			+ "prix_initial, no_utilisateur, no_categorie, no_retrait ) VALUES (?,?,?,?,?,?,?,?) ";

	private static final String SQL_SELECT_BY_NAME = "SELECT * from ARTICLES_VENDUS a " 
			+ "INNER JOIN UTILISATEURS u on a.no_utilisateur = u.no_utilisateur"
			+ "where nom_article =?";

	private static final String SQL_SELECT_BY_CAT = "SELECT * from ARTICLES_VENDUS a " 
			+ "INNER JOIN UTILISATEURS u on a.no_utilisateur = u.no_utilisateur"
			+ "where no_categorie = ?"
	;

	private static final String SQL_SELECT_BY_NAMEANDCAT = "SELECT * from [ARTICLES_VENDUS] a "
			+ "INNER JOIN UTILISATEURS u on a.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c on a.no_categorie = c.no_categorie " 
			+ "where nom_article = ? and c.no_categorie = ? ";


	private static final String SELECT_ALL = "SELECT * from ARTICLES_VENDUS a "
			+"INNER JOIN UTILISATEURS u on a.no_utilisateur = u.no_utilisateur";

	private static final String SELECT_BY_ID = "SELECT * from ARTICLE_VENDUS WHERE no_article= ?";
	
	private static final String UPDATE = "update ARTICLES_VENDUS set nom_article = ?, description = ?,"
			+ "	date_debut_encheres=?, date_fin_encheres= ?, prix_initial= ?, prix_vente= ?, "
			+ "	no_utilisateur= ?, no_categorie=?, no_retrait=? where no_article= ? ";

	// Insert un nouvel article
	@Override
	public void insertArticle(ArticleVendu article) throws BusinessException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// Si l'article n'existe pas, création d'une erreur
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.INSERT_OBJET_NULL);
		}

		// Test connexion, si bon = traitement et
		try (Connection cnx = DBConnexion.seConnecter()) {
			cnx.setAutoCommit(false);
			pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			pstmt.setFloat(5, article.getMiseAPrix());
			// pstmt.setInt(6, article.getVendeur().getNoUtilisateur());
			pstmt.setInt(6, 2);
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

			// Si la connexion est un echec, renvoie une erreur echec
		} catch (Exception e) {
			// Exception est plus général que DAL ou BLLExecp
			e.printStackTrace();
			// Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.INSERT_OBJET_ECHEC);
			throw businessException;

		}
	}

	// Recuperation de tt les articles lors d'une recherche sans filtre
	@Override
	public List<ArticleVendu> liste() throws BusinessException {
		List<ArticleVendu> listeArticleEnVente = new ArrayList<ArticleVendu>();
		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Categorie cat = new Categorie(rs.getInt("no_categorie"), "un libelle");
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				Retrait retrait = new Retrait(rs.getInt("no_retrait"));

				ArticleVendu av = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), cat, rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"),
						rs.getFloat("prix_vente"), utilisateur, retrait);

				listeArticleEnVente.add(av);

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// Exception est plus général que DAL ou BLLExecp
			e.printStackTrace();
			// Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		return listeArticleEnVente;
	}

	// Selection d'articles par le nom (pour le filtrage)
	@Override
	public List<ArticleVendu> listeSelectByName(String name) throws BusinessException {
		List<ArticleVendu> listeArticleSelectByName = new ArrayList<ArticleVendu>();

		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_NAME);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Categorie cat = new Categorie(rs.getInt("no_categorie"), "un libelle");
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				Retrait retrait = new Retrait(rs.getInt("no_retrait"));

				ArticleVendu av = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), cat, rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"),
						rs.getFloat("prix_vente"), utilisateur, retrait);

				listeArticleSelectByName.add(av);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// Exception est plus général que DAL ou BLLExecp
			e.printStackTrace();
			// Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}

		return listeArticleSelectByName;
	}

	// Selection d'articles via la categorie (pour le filtrage)
	@Override
	public List<ArticleVendu> listeSelectByCat(int noCategorie) throws BusinessException {

		List<ArticleVendu> listeArticleSelectByCat = new ArrayList<ArticleVendu>();

		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_CAT);
			pstmt.setInt(1, noCategorie);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Categorie cat = new Categorie(rs.getInt("no_categorie"), "un libelle");
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				Retrait retrait = new Retrait(rs.getInt("no_retrait"));

				ArticleVendu av = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), cat, rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"),
						rs.getFloat("prix_vente"), utilisateur, retrait);

				listeArticleSelectByCat.add(av);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			// Exception est plus général que DAL ou BLLExecp
			e.printStackTrace();
			// Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		return listeArticleSelectByCat;
	}

	// Selection d'articles via une Categorie et le nom de l'article (pour le
	// filtrage)
	@Override
	public List<ArticleVendu> listeSelectByNameAndCat(String name, int noCategorie) throws BusinessException {

		List<ArticleVendu> listeArticleSelectByNameAndCat = new ArrayList<ArticleVendu>();

		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_NAMEANDCAT);
			pstmt.setString(1, name);
			pstmt.setInt(2, noCategorie);
			

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Categorie cat = new Categorie(rs.getInt("no_categorie"), "un libelle");
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				Retrait retrait = new Retrait(rs.getInt("no_retrait"));

				ArticleVendu av = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), cat, rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"),
						rs.getFloat("prix_vente"), utilisateur, retrait);

				listeArticleSelectByNameAndCat.add(av);
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// Exception est plus général que DAL ou BLLExecp
			e.printStackTrace();
			// Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		return listeArticleSelectByNameAndCat;
	}

	// *****************************************************************
	// Select un article par User (pour affichage article en vente sur profil)
	@Override
	public List<ArticleVendu> selectByUser(int id) throws BusinessException {

		return null;
	}

	// *****************************************************************
	// Select un article par son ID
	@Override
	public ArticleVendu selectById(int id) throws BusinessException {
		ArticleVendu article = null;

		try (Connection cnx = DBConnexion.seConnecter()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("no_categorie"), "un libelle");
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"));
				Retrait retrait = new Retrait(rs.getInt("no_retrait"));

				 article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), categorie, rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"),
						rs.getFloat("prix_vente"), utilisateur, retrait);

			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}

		return article;
	}

	@Override
	public void update(ArticleVendu article) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
