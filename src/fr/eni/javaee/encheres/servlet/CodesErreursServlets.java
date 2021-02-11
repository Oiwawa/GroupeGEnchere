package fr.eni.javaee.encheres.servlet;

/**
 * Les codes disponibles sont entre 30000 et 39999
 */
public abstract class CodesErreursServlets {
	
	
	/**
	 * Date de début antérieur à date du jour
	 */
	public static final int DATE_DEBUT_ERREUR=30003;
	/**
	 * Date de fin antérieur à la date de début
	 */
	public static final int DATE_FIN_ERREUR=30004;
	/**
	 * Informations manquantes dans les champs
	 */
	public static final int CHAMPS_VIDE_ERREUR=30002;
	
	
}