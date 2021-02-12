package fr.eni.javaee.encheres.dal;

import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Enchere;

public interface EnchereDAO {

	//Créer une nouvelle enchere
	public Enchere insert(Enchere enchere) throws BusinessException;
	//Avoir la liste de toutes les encheres
	public List<Enchere> selectAll() throws BusinessException;
	//Trouver une enchere par son ID
	public Enchere selectById() throws BusinessException;
	//Trouvé une enchere gagné par un  USER
	public List<Enchere> selectGagneeParEncherisseur(int id) throws BusinessException;
	//Trouvé les encheres ou USER a encherit
	public List<Enchere> selectByEncherisseur(int id) throws BusinessException;
}
