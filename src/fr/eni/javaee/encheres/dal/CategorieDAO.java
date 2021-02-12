package fr.eni.javaee.encheres.dal;

import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Categorie;

public interface CategorieDAO {

	//Select une categorie par son ID
	public Categorie selectById(int id) throws BusinessException;
	//Liste de toutes les categories
	public List<Categorie> selectAll() throws BusinessException;

}
