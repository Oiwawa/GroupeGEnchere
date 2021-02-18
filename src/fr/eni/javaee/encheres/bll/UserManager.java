package fr.eni.javaee.encheres.bll;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.CodeResultatDal;
import fr.eni.javaee.encheres.dal.UserDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.UserDAOJdbcImpl;

public class UserManager {

	private static UserDAO userDAO = new UserDAOJdbcImpl();
	private static Utilisateur utilisateur = new Utilisateur();
	private static BusinessException businessException = new BusinessException();

	public static Utilisateur selectById(int id) throws BusinessException {
		return UserManager.userDAO.selectById(id);
	}

	public static Utilisateur inscription(Utilisateur user, String confirmation)
			throws BusinessException {
		
		businessException.viderListeErreur(); // A chaque nouvelle inscription on vide la liste d'erreurs 

		validerMotDePasse(user, confirmation, businessException);
		validerPseudo(user, businessException);
		validerEmail(user, businessException);
		validerNom(user, businessException);
		validerPrenom(user, businessException);
		validerTelephone(user, businessException);

		if (!businessException.hasErreurs()) {
			userDAO.inscription(user);
		} else {
			throw businessException;
		}
		return user;
	}

	// Verification de la validite du mot de passe
	public static void validerMotDePasse(Utilisateur user, String confirmation, BusinessException businessException)
			throws BusinessException {
		String mdp = user.getMotDePasse();
		if (mdp != null && mdp.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
			if (!mdp.equals(confirmation)) {
				businessException.ajouterErreur(CodeResultatDal.IDENTIFIANT_MDP_INCORECT);
			} else if (mdp.trim().length() < 3) {
				businessException.ajouterErreur(CodeResultatDal.INSERT_MDP_INCORRECT);
			}
		}
	}

	// Verification de la validite du pseudo
	public static void validerPseudo(Utilisateur user, BusinessException businessException) throws BusinessException {
		if (user.getPseudo() != null && user.getPseudo().trim().length() < 3) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_USER);
		}
	}

	// Verification de la validite du Nom
	public static void validerNom(Utilisateur user, BusinessException businessException) throws BusinessException {
		if (user.getNom() != null && user.getNom().trim().length() < 3) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_NOM_USER);
		}

	}

	// Verification de la validite du prenom
	public static void validerPrenom(Utilisateur user, BusinessException businessException) throws BusinessException {
		if (user.getPrenom() != null && user.getPrenom().trim().length() < 3) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_PRENOM_USER);
		}
	}

	// Verification de la validite de l'email
	public static void validerEmail(Utilisateur user, BusinessException businessException) throws BusinessException {
		if (user.getEmail() != null && user.getEmail().trim().length() != 0) {
			if (!user.getEmail().matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_USER);
			}
		} else {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_USER);
		}
	}

	// Verification de la validite du telephone
	public static void validerTelephone(Utilisateur user, BusinessException businessException)
			throws BusinessException {
		if (user.getTelephone() != null && user.getTelephone().trim().length() < 10
				|| user.getTelephone() != null && user.getTelephone().trim().length() > 10) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_TELEPHONE_USER);
		}
	}

}
