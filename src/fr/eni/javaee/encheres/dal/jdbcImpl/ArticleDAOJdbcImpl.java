package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.DALException;
import fr.eni.javaee.encheres.dal.DBConnexion;
import fr.eni.javaee.encheres.dal.ArticleDAO;

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
	private static final String SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS";


	@Override
	public void insertArticle(ArticleVendu article) throws DALException, SQLException {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Utilisateur user = null;
		Categorie cat = null;
		Retrait retrait = null;

		cnx = DBConnexion.seConnecter();

		try {
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

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}

			rs.close();

			pstmt.close();
			cnx.commit();

		} catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DALException(e.getMessage());
		} finally {
			try {
				cnx.setAutoCommit(true);
				cnx.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void encherir(Enchere ench) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ArticleVendu> liste() throws DALException, SQLException {

		List<ArticleVendu> listeArticleEnVente = new ArrayList<ArticleVendu>();
		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVendu av = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), 0, rs.getDate("date_debut_encheres").toLocalDate(),
						rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"),
						rs.getFloat("prix_vente"));
				listeArticleEnVente.add(av);
			}
		} catch (Exception e) {
			throw new DALException("liste failed", e);
		}
		return listeArticleEnVente;
	}

	@Override
	public void lieuRetrait(Retrait ret) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void categorieArticle(Categorie cat) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void remporterVente(ArticleVendu article) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherDetailVente() throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ArticleVendu selectByName(String name) throws DALException, SQLException {
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
			throw new DALException("selectByName failed - name = " + name, e);
		}
		return art;
	}

	@Override
	public Categorie selectByCat(int noCategorie) throws DALException, SQLException {
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
			throw new DALException("selectByCat failed - noCategorie = " + cat.getNoCategorie(), e);
		}
		return cat;
	}

}
