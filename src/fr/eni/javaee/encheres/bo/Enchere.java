package fr.eni.javaee.encheres.bo;

import java.time.LocalDate;

public class Enchere {

	private int id;
	private LocalDate dateEnchere;
	private float montantEnchere;
	private ArticleVendu article;
	private Utilisateur encherisseur;
	private boolean remporteEnchere = false;

	//Constructeur vide
	public Enchere() {
	}
	// Constructeur sans ID
	public Enchere(LocalDate dateEnchere, float montantEnchere, ArticleVendu article, Utilisateur encherisseur) {
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
		this.encherisseur = encherisseur;
		this.setRemporteEnchere(false);

	}

	// Constructeur plein (avec ID)
	public Enchere(int id, LocalDate dateEnchere, float montantEnchere, ArticleVendu article,
			Utilisateur encherisseur) {
		this.id = id;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
		this.article = article;
		this.encherisseur = encherisseur;
		this.setRemporteEnchere(false);
	}

	
	//GETTERS SETTERS
	public int getId() {
		return id;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public float getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(float montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public Utilisateur getEncherisseur() {
		return encherisseur;
	}

	public void setEncherisseur(Utilisateur encherisseur) {
		this.encherisseur = encherisseur;
	}

	public boolean isRemporteEnchere() {
		return remporteEnchere;
	}

	public void setRemporteEnchere(boolean remporteEnchere) {
		this.remporteEnchere = remporteEnchere;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [dateEnchere=");
		builder.append(dateEnchere);
		builder.append(", montantEnchere=");
		builder.append(montantEnchere);
		builder.append("]");
		return builder.toString();
	}

}
