package fr.eni.javaee.encheres.bll;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.UserDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.UserDAOJdbcImpl;

public class UserManager {

	private static UserDAO userDAO = new UserDAOJdbcImpl();
	private static Utilisateur utilisateur = new Utilisateur();
	
	public static Utilisateur selectById(int id) throws BusinessException {
		return UserManager.userDAO.selectById(id);
	}


}
