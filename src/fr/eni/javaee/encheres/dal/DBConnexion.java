package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnexion {

	public static Connection seConnecter() throws DALException {
		Connection cnx = null;
		InitialContext jndi = null;
		DataSource ds = null;
		
		//-----------------------------------------
		try {
			jndi = new InitialContext();
		} catch(NamingException e) {
			throw new DALException("Erreur d'accès au contexte initial JNDI", e);
		}
		//-----------------------------------------
		try {
			ds =(DataSource) jndi.lookup("java:comp/env/dsEncheres");
		} catch(NamingException e) {
			throw new DALException("Objet introuvable dans l'arbre JNDI", e);	
		}

		//-----------------------------------------
		try {
			cnx =(Connection) ds.getConnection();
		}catch(SQLException e) {
			throw new DALException("Impossible d'obtenir une connexion", e);
		}
		return cnx;
	}
	
	//-------------------------------------------------------------------------------------

	
	public static void seDeconnecter(Connection cnx) throws DALException {
		if(cnx !=null) {
			try {
				cnx.close();
			}catch(SQLException e) {
				throw new DALException("Impossible de fermer la connexion",e);
			}
		}
	}
	
	//-------------------------------------------------------------------------------------

	public static void seDeconnecter(Statement stmt, Connection cnx) throws DALException{
		try {
			if(stmt!=null) {
				stmt.close();
			}
		}catch (SQLException e) {
			throw new DALException("Impossible de fermer le statement",e );
		}
		seDeconnecter(cnx);
	}
}

