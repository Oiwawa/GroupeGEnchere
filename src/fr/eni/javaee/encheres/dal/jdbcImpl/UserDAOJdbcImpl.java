package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.CodeResultatDal;
import fr.eni.javaee.encheres.dal.DALException;
import fr.eni.javaee.encheres.dal.DBConnexion;
import fr.eni.javaee.encheres.dal.UserDAO;

public class UserDAOJdbcImpl implements UserDAO {
	
	private static final String SELECT_BY_ID ="SELECT [no_utilisateur],[pseudo],[nom],[prenom],[email],[telephone],[rue],[code_postal],[ville],[mot_de_passe],[credit],[administrateur] FROM [BDD_ENCHERES].[dbo].[UTILISATEURS] WHERE [no_utilisateur] = ? ";
	@Override
	public void inscription(Utilisateur user) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void connexion(Utilisateur user) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(Utilisateur user) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Utilisateur user) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void afficherProfil(Utilisateur user) throws DALException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur selectById(int id) throws BusinessException {

		Utilisateur user = null;
		try(Connection cnx = DBConnexion.seConnecter()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new Utilisateur();
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getInt("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getInt("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setMotDePasse(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdministrateur(rs.getBoolean("administrateur"));
			}
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_UTILISATEURS_ECHEC);
			throw businessException;
		}
		
		return null;
	}
}
