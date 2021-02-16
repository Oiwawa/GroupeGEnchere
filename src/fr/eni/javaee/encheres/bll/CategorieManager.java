package fr.eni.javaee.encheres.bll;



import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.dal.CategorieDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.CategorieDAOJdbcImpl;

public class CategorieManager {

	private static CategorieDAO categorieDAO = new CategorieDAOJdbcImpl();

	

	public static Categorie selectCatById(int id) throws BusinessException {
		return categorieDAO.selectById(id);
	}
	
	public static List<Categorie> selectAllCat() throws BusinessException {
		return categorieDAO.selectAll();
	}
}
