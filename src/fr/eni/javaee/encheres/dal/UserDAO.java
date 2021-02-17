package fr.eni.javaee.encheres.dal;

import java.sql.SQLException;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateur;

public interface UserDAO {

	// -------------------USER-------------------//
		// Inscription au site
		public void inscription(Utilisateur user) throws BusinessException;

		// Modification du profil
		public void updateUser(Utilisateur user) throws DALException, SQLException;

		// Suppression du compte par l'utilisateur
		public void deleteUser(Utilisateur user) throws DALException, SQLException;
		
		//Afficher un profil
		public void afficherProfil(Utilisateur user) throws DALException, SQLException;

		public Utilisateur selectById(int id) throws BusinessException;
}
