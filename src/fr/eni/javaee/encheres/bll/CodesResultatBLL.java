package fr.eni.javaee.encheres.bll;

public abstract class CodesResultatBLL {
	
	/**
	 * Regle de gestion des erreurs pour la date d'enchere (ne peut pas être null / avant la date du jour / date de fin pas avant date de début
	 */
	public static final int REGLE_DATE_ENCHERE_ARTICLE=30000;
	//LES REGLES POUR INSERER ARTICLE ----------------------------------------------
	
	
	//PEUT ETRE A SUPPRIMER VOIR ARTICLE MANAGER
	/**
	 * Regle quand tous les champs ne sont pas remplis
	 */
	public static final int REGLE_REMPLISSAGE_VENTE = 20000;

	/**
	 * Regle nom de l'article
	 */
	public static final int REGLE_NOM_VENTE = 30000;
	/**
	 * Regle description doit être remplie
	 */
	public static final int REGLE_DESCRITPION_VENTE = 30001;
	/**
	 * Regle la categorie doit être indiqué
	 */
	public static final int REGLE_CATEGORIE_VENTE =30002;
	/**
	 * Regle début d'enchere ne peut pas être avant date du jour
	 */
	public static final int REGLE_DATE_DEBUT_VENTE = 30003;

	/**
	 * Regle fin d'enchere doit être minimum 24h apres date de debut
	 */
	public static final int REGLE_DATE_FIN_VENTE = 30004;
	
	/**
	 * Regle le prix ne peut pas être <= a 0
	 */
	public static final int REGLE_PRIX_DEPART_VENTE =30005;
	/**
	 * Regle quand l'adresse de retrait n'est pas complete
	 */
	public static final int REGLE_RETRAITS_ADRESSE_ERREUR=30006;
	
	/**
	 * Regle quand le pseudo n'est pas correct
	 */
	public static final int REGLE_VALIDER_PSEUDO=30007;
	
	/**
	 * Regle quand l'etat de la vente n'est pas correct
	 */

	public static final int REGLE_ETAT_VENTE_ARTICLE=30008;
	
//REGLE CREATION D'UN COMPTE UTILISATEUR
	/**
	 * Regle nom user 
	 */
	public static final int REGLE_NOM_USER = 40000;
	/**
	 * Regle PSEUDO user 
	 */
	public static final int REGLE_PSEUDO_USER = 40001;
	/**
	 * Regle PRENOM user 
	 */
	public static final int REGLE_PRENOM_USER = 40002;
	/**
	 * Regle EMAIL user 
	 */
	public static final int REGLE_EMAIL_USER = 40003;
	/**
	 * Regle TELEPHONE user 
	 */
	public static final int REGLE_TELEPHONE_USER = 40004;
	/**
	 * Regle RUE user 
	 */
	public static final int REGLE_RUE_USER = 40005;
	/**
	 * Regle CP user 
	 */
	public static final int REGLE_CP_USER = 40006;
	/**
	 * Regle VILLE user 
	 */
	public static final int REGLE_VILLE_USER = 40007;
	/**
	 * Regle MDP user 
	 */
	public static final int REGLE_MDP_USER = 40008;
	/**
	 * Regle connexion user 
	 */
	public static final int ERREUR_CONNECTION = 40008;

	
}
