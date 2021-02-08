package fr.eni.javaee.encheres.bo;

import java.time.LocalDate;

public class Enchere {

	private LocalDate dateEnchere;
	private float montantEnchere;
	
	
	
	public Enchere(LocalDate dateEnchere, float montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
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
