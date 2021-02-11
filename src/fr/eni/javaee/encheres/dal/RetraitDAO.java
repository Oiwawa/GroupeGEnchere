package fr.eni.javaee.encheres.dal;

import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Retrait;

public interface RetraitDAO {

	public Retrait insert(Retrait retrait) throws BusinessException;
	public Retrait selectById(int id) throws BusinessException;
	public List<Retrait> selectAll() throws BusinessException;
}
