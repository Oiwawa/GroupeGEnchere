package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.dal.CodeResultatDal;
import fr.eni.javaee.encheres.dal.DBConnexion;
import fr.eni.javaee.encheres.dal.RetraitDAO;

public class RetraitDAOJdbcImpl implements RetraitDAO {

	private static final String INSERT = "INSERT INTO RETRAITS (rue, code_postal, ville) values (?,?,?)";
	private static final String SELECT_BY_ID = "SELECT FROM RETRAITS (rue, code_postal, ville) WHERE no_retrait = ? ";
	private static final String SELECT_ALL = "SELECT FROM RETRAITS (rue, code_postal, ville)";

	//INSERTION D'UN NOUVEAU RETRAIT
	@Override
	public Retrait insert(Retrait retrait) throws BusinessException {

		if(retrait ==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.INSERT_OBJET_NULL);
		}
		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());

			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				retrait.setIdRetrait(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return retrait;
	}

	//SELECT RETRAIT PAR ID
	@Override
	public Retrait selectById(int id) throws BusinessException {

		Retrait retrait = null;

		try (Connection cnx = DBConnexion.seConnecter()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				retrait = new Retrait();
				retrait.setIdRetrait(rs.getInt("no_retrait"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setRue(rs.getString("rue"));
				retrait.setVille(rs.getString("ville"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.INSERT_OBJET_ECHEC);
			throw businessException;
		}
		return retrait;
	}

	//SELECT TOUS LES RETRAITS
	@Override
	public List<Retrait> selectAll() throws BusinessException {

		List<Retrait> retraits = new ArrayList<Retrait>();

		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Retrait retrait = new Retrait();
				retrait.setIdRetrait(rs.getInt("no_retrait"));
				retrait.setCodePostal(rs.getString("code_postal"));
				retrait.setRue(rs.getString("rue"));
				retrait.setVille(rs.getString("ville"));
				retraits.add(retrait);
			}

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_RETRAIT_ECHEC);
			throw businessException;
		}

		return retraits;
	}

	
}
