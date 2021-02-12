package fr.eni.javaee.encheres.dal;

import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Retrait;

public interface RetraitDAO {

	//Créer un nouveau lieu de retrait
	public Retrait insert(Retrait retrait) throws BusinessException;
	//Select un retrait par son ID
	public Retrait selectById(int id) throws BusinessException;
	//afficher tous les retraits
	public List<Retrait> selectAll() throws BusinessException;
	
}
