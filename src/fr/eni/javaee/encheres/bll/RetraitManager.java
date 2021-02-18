package fr.eni.javaee.encheres.bll;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.RetraitDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.RetraitDAOJdbcImpl;


public class RetraitManager {

	//Instanciation 
	private static RetraitDAO retraitDAO = new RetraitDAOJdbcImpl();
	private static BusinessException businessException = new BusinessException();
	private static Utilisateur user = new Utilisateur();

	public RetraitManager() {
	}
	/**
	 * Methode pour créer un nouveau retrait. Verifie l'adresse, si adresse correct envoie, sinon erreur
	 * @param retrait
	 * @return retrait
	 * @throws BusinessException
	 */
	public static Retrait addRetrait(Retrait retrait) throws BusinessException {
		
		validerAdresse(retrait, businessException);
		if(!businessException.hasErreurs()) {
			retraitDAO.insert(retrait);
		} else {
			throw businessException;
		}
		return retrait;
		
	}
	private static void validerAdresse(Retrait retrait, BusinessException businessException) {
		String testCP = "/^(([1-95]{2}|2A|2B)[0-9]{3})$|^[971-974]$/";
		//Si la rue, le code postal et la ville ne sont pas indiqués, renvoie erreur 
		if(retrait.getRue() == null || retrait.getRue().trim().equals("")  
				|| retrait.getCodePostal() == null 
				|| retrait.getCodePostal().trim().equals("")
				|| retrait.getVille() == null || retrait.getVille().trim().equals("")) {
				retrait = user.getAdresseRetraitDefaut();
				
				} // else if(!retrait.getCodePostal().matches(testCP)) {
		//businessException.ajouterErreur(CodesResultatBLL.REGLE_RETRAITS_ADRESSE_ERREUR);				
//				}
	}
	
	public static Retrait selectRetraitById(int id) throws BusinessException {
		return retraitDAO.selectById(id);
	}
}
