package fr.eni.javaee.encheres.bll;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.dal.EnchereDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.EnchereDAOJdbcImpl;

public class EnchereManager {

	private static EnchereDAO enchereDAO = new EnchereDAOJdbcImpl();
	private Enchere enchere = new Enchere();
	BusinessException businessException = new BusinessException();
	
}
