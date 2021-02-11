package fr.eni.javaee.encheres.bll;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.dal.RetraitDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.RetraitDAOJdbcImpl;

public class RetraitManager {

	private RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
	private BusinessException businessException = new BusinessException();
	
	public static Retrait addRetrait(Retrait retrait) throws BusinessException {
		return retrait;
		
	}
	private static void validerAdresse(Retrait retrait, BusinessException businessException) {
		

	}
}
