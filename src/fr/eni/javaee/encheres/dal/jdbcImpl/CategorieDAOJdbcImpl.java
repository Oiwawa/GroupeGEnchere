package fr.eni.javaee.encheres.dal.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.dal.CategorieDAO;
import fr.eni.javaee.encheres.dal.CodeResultatDal;
import fr.eni.javaee.encheres.dal.DBConnexion;

public class CategorieDAOJdbcImpl implements CategorieDAO {

	private static final String SELECT_BY_ID = "SELECT [no_categorie] , [libelle] FROM [BDD_ENCHERES].[dbo].[CATEGORIES] WHERE no_categorie = ?";
	private static final String SELECT_ALL = "SELECT [no_categorie] , [libelle] FROM [BDD_ENCHERES].[dbo].[CATEGORIES]";
	
	//SELECT PAR ID 
	@Override
	public Categorie selectById(int id) throws BusinessException {
	
		Categorie categorie = null;
		
		try(Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodeResultatDal.LECTURE_CATEGORIES_ECHEC);
			throw businessException;
		}
		
		return categorie;
	}

	//SELECT TOUTES LES CATEGORIES 
	@Override
	public List<Categorie> selectAll() throws BusinessException {
		
		List<Categorie> listeCategories = new ArrayList<Categorie>();
		try(Connection cnx = DBConnexion.seConnecter()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				listeCategories.add(categorie);
			}
			} catch(Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodeResultatDal.LECTURE_CATEGORIES_ECHEC);
				throw businessException;
			}
		
		return listeCategories;
	}

}

