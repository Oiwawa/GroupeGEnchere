package fr.eni.javaee.encheres.bo;


public class Retrait{

	
	
	private int idRetrait;
	private String codePostal;
	private String ville;
	private String rue;
	
	//Constructeur vide
	public Retrait() {
	}
	//Constructeur avec ID seul
	public Retrait(int idRetrait) {
		this.idRetrait = idRetrait;
	}
	//Constructeur avec Ville, Rue, CodePostal / sans ID
	public Retrait(String codePostal, String ville, String rue) {
		this.codePostal = codePostal;
		this.ville = ville;
		this.rue = rue;
	}
	
	//Constructeur pleins
	public Retrait(int idRetrait, String codePostal, String ville, String rue) {
		this.idRetrait = idRetrait;
		this.codePostal = codePostal;
		this.ville = ville;
		this.rue = rue;
	}
	
	//Constructeur avec ID retrait
	public Retrait(String codePostal, String ville, String rue, int idRetrait) {
		super();
		this.codePostal = codePostal;
		this.ville = ville;
		this.rue = rue;
		this.idRetrait = idRetrait;
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
		return "Retrait [codePostal=" + codePostal + ", ville=" + ville + ", idRetrait=" + idRetrait + ", rue=" + rue
				+ "]";
	}








	
	

}
