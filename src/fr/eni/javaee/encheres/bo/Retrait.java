package fr.eni.javaee.encheres.bo;

import java.io.Serializable;

public class Retrait implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int noArticle;
	private String codePostal;
	private String ville;
	private int idRetrait;
	private String rue;
	
	//Constructeur vide
	public Retrait() {
		super();
	}
	
	public Retrait(int idRetrait) {
		this.idRetrait = idRetrait;
	}
	
	//Constructeur sans ID retrait
	public Retrait(int noArticle, String codePostal, String ville, String rue) {
		super();
		this.noArticle = noArticle;
		this.codePostal = codePostal;
		this.ville = ville;
		this.rue = rue;
	}
	
	//Constructeur avec ID retrait
	public Retrait(int noArticle, String codePostal, String ville, String rue, int idRetrait) {
		super();
		this.noArticle = noArticle;
		this.codePostal = codePostal;
		this.ville = ville;
		this.rue = rue;
		this.idRetrait = idRetrait;
	}
	
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getRue() {
		return rue;
	}
	
	
	public void setRue(String rue) {
		this.rue = rue;
	}
	public int getIdRetrait() {
		return idRetrait;
	}
	public void setIdRetrait(int idRetrait) {
		this.idRetrait = idRetrait;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [noArticle=");
		builder.append(noArticle);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append(", idRetrait=");
		builder.append(idRetrait);
		builder.append("]");
		return builder.toString();
	}





	
	

}
