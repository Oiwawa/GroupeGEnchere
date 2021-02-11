package fr.eni.javaee.encheres.bo;

public class Retrait {
	private int noArticle;
	private int codePostal;
	private String ville;
	private int idRetrait;
	
	
	
	
	public Retrait(int noArticle, int codePostal, String ville, int idRetrait) {
		super();
		this.noArticle = noArticle;
		this.codePostal = codePostal;
		this.ville = ville;
		this.idRetrait = idRetrait;
	}
	
	
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
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
