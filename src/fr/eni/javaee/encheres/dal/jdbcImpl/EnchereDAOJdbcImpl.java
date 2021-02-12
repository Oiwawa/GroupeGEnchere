package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.dal.CodeResultatDal;
import fr.eni.javaee.encheres.dal.DBConnexion;
import fr.eni.javaee.encheres.dal.EnchereDAO;

public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String INSERT="INSERT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur, remporte) VALUES (?,?,?,?,?)";
	private static final String SELECT_ALL="SELECT INTO ENCHERES (date_enchere, montant_enchere, no_article, no_utilisateur, remporte)";
	@Override
	public Enchere insert(Enchere enchere) throws BusinessException {
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
		return null;
	}

	//Liste de toutes les encheres a afficher dans la page principale
	@Override
	public List<Enchere> selectAll() throws BusinessException {
		
		//TODO 
		return null;
	}

	@Override
	public Enchere selectById() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectGagneeParEncherisseur(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enchere> selectByEncherisseur(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
