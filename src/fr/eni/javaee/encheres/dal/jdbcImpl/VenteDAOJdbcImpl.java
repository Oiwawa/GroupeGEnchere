package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.SQLException;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.dal.DALException;
import fr.eni.javaee.encheres.dal.VenteDAO;

public class VenteDAOJdbcImpl implements VenteDAO {

	@Override
	public void vendre(ArticleVendu article) throws DALException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encherir(Enchere ench) throws DALException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void concerne(ArticleVendu art) throws DALException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArticleVendu> listeVente(ArticleVendu art) throws DALException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lieuRetrait(Retrait ret) throws DALException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void categorieArticle(Categorie cat) throws DALException, SQLException {
		// TODO Auto-generated method stub
		
	}


	
}
