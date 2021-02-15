package fr.eni.javaee.encheres.dal;



import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateur;

public interface ConnectionDAO {
	
	public Utilisateur rechercheUser(String identifiant, String mdp) throws BusinessException;
	

}
