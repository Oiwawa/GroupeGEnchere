package fr.eni.javaee.encheres.dal;
/**
 * Code gestion erreurs
 * @author hugor
 *
 */
public abstract class CodeResultatDal {

	/**
	 * Echec ajout d'objet
	 */
	public static final int INSERT_OBJET_NULL=10000;
	
	/**
	 * Echec quand erreur lié à l'insertion objet
	 */
	public static final int INSERT_OBJET_ECHEC=10001;
	
	/**
	 * Echec de la lecture des Articles
	 */
	public static final int LECTURE_ARTICLES_ECHEC=10002;
	
	/**
	 * Echec de la lecture des Utilisateurs
	 */
	public static final int LECTURE_UTILISATEURS_ECHEC=10003;
	
	/**
	 * Echec de la lecture des Categorie
	 */
	public static final int LECTURE_CATEGORIES_ECHEC=10004;
	/**
	 * Echec de la lecture des Encheres
	 */
	public static final int LECTURE_ENCHERES_ECHEC=10005;
	/**
	 * Echec de la lecture des Retrait
	 */
	public static final int LECTURE_RETRAIT_ECHEC=10006;
	
	/**
	 * Echec CONNEXION
	 */
	public static final int IDENTIFIANT_MDP_INCORECT=10007;
	public static final int CONNEXION_RECHERCHEUSER_ERREUR=10008;

	public static final int INSERT_USER_NULL=10009;
	public static final int INSERT_USER_ECHEC=10010;
	public static final int INSERT_MDP_ECHEC=10011;
	public static final int INSERT_MDP_INCORRECT=10012;
	public static final int MDP_NON_SAISIE=10013;





}

