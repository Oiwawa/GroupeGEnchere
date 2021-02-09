package fr.eni.javaee.encheres.dal;

import fr.eni.javaee.encheres.dal.jdbcImpl.VenteDAOJdbcImpl;

public class DAOFactory {

	public static VenteDAO venteDAO() {
		VenteDAO venteDAO = new VenteDAOJdbcImpl();
		return venteDAO;
	}
	
}
