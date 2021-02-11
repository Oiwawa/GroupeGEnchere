package fr.eni.javaee.encheres.bo;

import java.time.LocalDate;

public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
	private int noCategorie;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private float miseAPrix;
	private float prixVente;
	private String etatVente;
	
	// Constructeur vide

	public ArticleVendu() {
		
	}
	// Constructeur sans id

	public ArticleVendu(String nomArticle, String description, int noCategorie, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, float miseAPrix, float prixVente) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.noCategorie = noCategorie;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;

		this.calculEtatVente();
	}

	// Constructeur pleins
	public ArticleVendu(int noArticle, String nomArticle, String description, int noCategorie,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, float miseAPrix, float prixVente) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.noCategorie = noCategorie;

		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		
		this.calculEtatVente();
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
		this.calculEtatVente();
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
		this.calculEtatVente();
	}

	public float getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(float miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public float getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(float prixVente) {
		this.prixVente = prixVente;
	}

	public String getEtatVente() {
		return etatVente;
	}
	
	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}
	
	private void calculEtatVente() {
		LocalDate today = LocalDate.now();
        if(today.isBefore(dateDebutEncheres)) {
        	this.setEtatVente("Vente non debute");
        } else if (today.isAfter(dateDebutEncheres) && today.isBefore(dateFinEncheres)) {
        	this.setEtatVente("Vente en cours");
        }else {
        	this.setEtatVente("Vente terminee");
        }
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVendu [noArticle=");
		builder.append(noArticle);
		builder.append(", nomArticle=");
		builder.append(nomArticle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateDebutEncheres=");
		builder.append(dateDebutEncheres);
		builder.append(", dateFinEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", miseAPrix=");
		builder.append(miseAPrix);
		builder.append(", prixVente=");
		builder.append(prixVente);
		builder.append(", etatVente=");
		builder.append(etatVente);
		builder.append("]");
		return builder.toString();
	}

}
