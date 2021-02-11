package fr.eni.javaee.encheres.dal;

import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Categorie;

public interface CategorieDAO {

	public Categorie selectById(int id) throws BusinessException;
	public List<Categorie> selectAll() throws BusinessException;

}
