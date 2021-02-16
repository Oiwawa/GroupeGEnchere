package fr.eni.javaee.encheres.bo;

public enum EtatVente {
		ENCHERE_CREE("Enchère créé"),
		ENCHERE_EN_COURS("Enchère en cours"),
		ENCHERE_TERMINEE("Enchère terminée");

private final String message;
EtatVente(String message) {
	this.message = message;
}

public String getMessage() {
	return message;
}
}



