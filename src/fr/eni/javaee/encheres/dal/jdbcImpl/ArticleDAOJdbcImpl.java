package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.SQLException;
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
	private static final String VENDRE_ARTICLE = "INSERT INTO article_vendu "
			+ "(nom_article, description, date_debut_encheres, date_fin_encheres, "
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait " + "VALUES (?,?,?,?,?,?,?,?,?) ";
	private static final String SQL_SELECT_BY_NAME = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, "
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait " 
			+ " from ARTICLE_VENDU "
			+ "where nom_article = ?"; 
	private static final String SQL_SELECT_BY_CAT = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, "
			+ "prix_initial, prix_vente, no_utilisateur, no_categorie, no_retrait " 
			+ " from ARTICLE_VENDU "
			+ "where no_categorie = ?"; 

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
			pstmt = cnx.prepareStatement(VENDRE_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			pstmt.setFloat(5, article.getMiseAPrix());
			pstmt.setFloat(6, article.getPrixVente());
			pstmt.setInt(7, user.getNoUtilisateur());
			pstmt.setInt(8, cat.getNoCategorie());
			pstmt.setInt(9, retrait.getIdRetrait());
			
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
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
	public List<ArticleVendu> listeVente(ArticleVendu art) throws DALException, SQLException {
		// TODO Auto-generated method stub
		return null;
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
	        ArticleVendu art= null;

	        try(Connection cnx = DBConnexion.seConnecter();
	            PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_NAME);
	){
	            pstmt.setString(1, name);
	            rs = pstmt.executeQuery();
	            while(rs.next()) {
	            	
	                art = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"),
	                        rs.getDate("date_debut_encheres").toLocalDate(), rs.getDate("date_fin_encheres").toLocalDate(), rs.getFloat("prix_initial"),
	                        rs.getFloat("prix_vente"), rs.getString("etatVente"));
	            }
	        } catch (Exception e) {
	            throw new DALException("selectByName failed - name = " + name, e);
	        }
	        return art;
	    }


	@Override
	public Categorie selectByCat(int noCategorie) throws DALException, SQLException {
	        ResultSet rs = null;
	        Categorie cat= null;

	        try(Connection cnx = DBConnexion.seConnecter();
	            PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_CAT);
	){
	            pstmt.setInt(1, noCategorie);
	            rs = pstmt.executeQuery();
	            if(rs.next()) {
	            	cat = new Categorie(rs.getInt("noCategorie"), rs.getString("libelle"));
	            }
	        } catch (Exception e) {
	            throw new DALException("selectByCat failed - noCategorie = " + cat.getNoCategorie(), e);
	        }
	        return cat;  
	        }

	}


