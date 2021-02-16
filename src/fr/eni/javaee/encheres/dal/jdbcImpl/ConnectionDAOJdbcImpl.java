package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.CodeResultatDal;
import fr.eni.javaee.encheres.dal.ConnectionDAO;
import fr.eni.javaee.encheres.dal.DBConnexion;

public class ConnectionDAOJdbcImpl implements ConnectionDAO {

	private static final String SELECT_USER = "SELECT * from UTILISATEURS " + "where pseudo =? and mot_de_passe =?";

	@Override
	public Utilisateur rechercheUser(String identifiant, String mdp) throws BusinessException {
		Utilisateur user = new Utilisateur();

		try (Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER);

			pstmt.setString(1, identifiant);
			pstmt.setString(2, mdp);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				user = new Utilisateur(rs.getString("pseudo"), rs.getString("mot_de_passe"), rs.getInt("no_utilisateur"));
				
				System.out.println("L'utilisateur trouve est :" + user);
			}

			rs.close();
			pstmt.close();
		} catch (Exception e) {
			// Exception est plus général que DAL ou BLLExecp
			e.printStackTrace();
			// Création d'une exception qui renvoie un code erreur
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.CONNEXION_RECHERCHEUSER_ERREUR);
			throw businessException;

		}
		if (user.getPseudo() == null || user.getMotDePasse() == null) {
			System.out.println("Le couple Identifiant, mdp n'existe pas");
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.IDENTIFIANT_MDP_INCORECT);
			throw businessException;
		} else {
			return user;
		}

	}
}
