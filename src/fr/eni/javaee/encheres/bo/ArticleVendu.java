package fr.eni.javaee.encheres.bo;

import java.time.LocalDate;

public class ArticleVendu {

	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private float miseAPrix;
	private float prixVente;
	private String etatVente;
	private Utilisateur vendeur;
	private Categorie noCategorie;
	private Retrait lieuRetrait;
	private EtatVente etatVenteEnum;

	// Constructeur vide

	public ArticleVendu() {

	}
	// Constructeur sans id

	public ArticleVendu(String nomArticle, String description, Categorie noCategorie, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, float miseAPrix, float prixVente, EtatVente etatVenteEnum) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.noCategorie = noCategorie;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVenteEnum = etatVenteEnum;

	}

	// Constructeur sans categorie
	public ArticleVendu(int noArticle, String nomArticle, String description, Categorie noCategorie,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, float miseAPrix, float prixVente,
			Utilisateur vendeur, Retrait lieuRetrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noCategorie = noCategorie;
		this.vendeur = vendeur;
		this.lieuRetrait = lieuRetrait;

	}

	// Constructeur pleins
	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, float miseAPrix, float prixVente, Categorie noCategorie, Utilisateur vendeur,
			Retrait lieuRetrait) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.noCategorie = noCategorie;
		this.vendeur = vendeur;
		this.lieuRetrait = lieuRetrait;
	}

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, float miseAPrix, float prixVente, int int2, int int3, int int4) {
		// TODO Auto-generated constructor stub
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

	public Categorie getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(Categorie noCategorie) {
		this.noCategorie = noCategorie;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;

	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
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

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public Retrait getLieuRetrait() {
		return lieuRetrait;
	}

	public void setLieuRetrait(Retrait lieuRetrait) {
		this.lieuRetrait = lieuRetrait;
	}

	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", vendeur=" + vendeur
				+ ", noRetrait=" + lieuRetrait + ", noCategorie=" + noCategorie + "]";
	}

	public EtatVente getEtatVenteEnum() {
		return etatVenteEnum;
	}

	public void setEtatVenteEnum(EtatVente etatVenteEnum) {
		if (dateDebutEncheres.isAfter(LocalDate.now())) {

			this.etatVenteEnum = EtatVente.ENCHERE_CREE;
		} else {
			if (dateDebutEncheres.isEqual(LocalDate.now())) {
				this.etatVenteEnum = EtatVente.ENCHERE_EN_COURS;
			} else {
				if (dateFinEncheres.isEqual(LocalDate.now())) {
					this.etatVenteEnum = EtatVente.ENCHERE_TERMINEE;
				}
			}

		}

	}

}
