package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.dal.ArticleDAO;
import fr.eni.javaee.encheres.dal.CodeResultatDal;
import fr.eni.javaee.encheres.dal.DBConnexion;
import fr.eni.javaee.encheres.dal.EnchereDAO;
import fr.eni.javaee.encheres.dal.UserDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String INSERT="INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur, remporte) VALUES (?,?,?,?,?)";
	private static final String SELECT_ALL="SELECT INTO ENCHERES (no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur, remporte)";
	private static final String SELECT_BY_ID="SELECT INTO ENCHERES (no_enchere, date_enchere, montant_enchere, no_article, no_utilisateur, remporte) WHERE no_enchere = ?";
	
	private static ArticleDAO articleDao = new ArticleDAOJdbcImpl();
	private static UserDAO userDao = new UserDAOJdbcImpl();
	
	@Override
	public void insert(Enchere enchere) throws BusinessException {
		if(enchere ==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.INSERT_OBJET_NULL);
		}
		
		try (Connection cnx = DBConnexion.seConnecter()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setDate(1, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setFloat(2, enchere.getMontantEnchere());
			pstmt.setInt(3, enchere.getArticle().getNoArticle());
			pstmt.setInt(4, enchere.getEncherisseur().getNoUtilisateur());
			pstmt.setBoolean(5, enchere.isRemporteEnchere());

			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				enchere.setId(rs.getInt(1));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Liste de toutes les encheres a afficher dans la page principale
	@Override
	public List<Enchere> selectAll() throws BusinessException {
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try(Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Enchere enchere = new Enchere();
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setId(rs.getInt("no_enchere"));
				enchere.setMontantEnchere(rs.getFloat("montant_enchere"));
				enchere.setArticle(articleDao.selectById(rs.getInt("no_article")));
				enchere.setEncherisseur(userDao.selectById(rs.getInt("no_utilisateur")));
				enchere.setRemporteEnchere(rs.getBoolean("remporte"));
				listeEncheres.add(enchere);
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ENCHERES_ECHEC);
			throw businessException;
		}
		
		return listeEncheres;
	}

	//Select enchere par id
	@Override
	public Enchere selectById(int id) throws BusinessException {

		Enchere enchere = null;
		
		try(Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				enchere = new Enchere();
				enchere.setId(rs.getInt("no_enchere"));
				enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
				enchere.setId(rs.getInt("no_enchere"));
				enchere.setMontantEnchere(rs.getFloat("montant_enchere"));
				enchere.setArticle(articleDao.selectById(rs.getInt("no_article")));
				enchere.setEncherisseur(userDao.selectById(rs.getInt("no_utilisateur")));
				enchere.setRemporteEnchere(rs.getBoolean("remporte"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_ENCHERES_ECHEC);
			throw businessException;
		}
		
		return enchere;
	}

	//Select les encheres gagnées par un User
	@Override
	public List<Enchere> selectGagneeParEncherisseur(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	//Select les encheres en cours pour un User
	@Override
	public List<Enchere> selectByEncherisseur(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
