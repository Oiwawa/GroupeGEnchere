package fr.eni.javaee.encheres.bll;

import java.time.LocalDate;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.dal.EnchereDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.EnchereDAOJdbcImpl;

public class EnchereManager {

	private static EnchereDAO enchereDAO = new EnchereDAOJdbcImpl();
	static BusinessException businessException = new BusinessException();
	
	public static Enchere insert(Enchere enchere) throws BusinessException {
		
		validerDate(enchere.getDateEnchere(), businessException);
		
		if(!businessException.hasErreurs()) {
			enchereDAO.insert(enchere);
		} else {
			throw businessException;
		}
		return enchere;
		
	}
	
	public static void validerDate(LocalDate date, BusinessException businessException) {
	
		if(date==null || date.isAfter(LocalDate.now()) ) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_DATE_DEBUT_VENTE);
		}
	}

}
