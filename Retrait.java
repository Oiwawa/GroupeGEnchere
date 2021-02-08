package fr.eni.javaee.encheres.bo;

public class Retrait {
	private int noArticle;
	private int codePostal;
	private String ville;
	
	
	
	
	public Retrait(int noArticle, int codePostal, String ville) {
		super();
		this.noArticle = noArticle;
		this.codePostal = codePostal;
		this.ville = ville;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [noArticle=");
		builder.append(noArticle);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}
	
	

}
