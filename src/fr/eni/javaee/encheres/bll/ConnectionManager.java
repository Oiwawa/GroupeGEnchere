package fr.eni.javaee.encheres.bll;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.ConnectionDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.ConnectionDAOJdbcImpl;

public class ConnectionManager {

	private static BusinessException businessException = new BusinessException();
	private ConnectionDAO connectionDAO = new ConnectionDAOJdbcImpl();
	private static Utilisateur utilisateur = new Utilisateur();

	private static ConnectionManager instance;

	// Constructor
	public ConnectionManager() {
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	// methode

	public Utilisateur connecterUser(String identifiant, String mdp) throws BusinessException {
		return this.connectionDAO.rechercheUser(identifiant, mdp);

	}
}
