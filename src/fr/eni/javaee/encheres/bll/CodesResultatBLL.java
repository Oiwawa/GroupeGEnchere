package fr.eni.javaee.encheres.bll;

public abstract class CodesResultatBLL {
	
	//LES REGLES POUR INSERER ARTICLE ----------------------------------------------
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
	
}
