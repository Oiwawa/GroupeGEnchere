package fr.eni.javaee.encheres.bll;


import fr.eni.javaee.encheres.BusinessException;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.ConnectionDAO;
import fr.eni.javaee.encheres.dal.jdbcImpl.ConnectionDAOJdbcImpl;


public class ConnectionManager {

	private ConnectionDAO connectionDAO = new ConnectionDAOJdbcImpl();	


	private static ConnectionManager instance;

	// Constructor
	public ConnectionManager() {
	}

	public static ConnectionManager getInstance() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
	
	//methode

	public Utilisateur connecterUser(String identifiant, String mdp) throws BusinessException{
	     /* Validation du champ email. */
		//BusinessException businessException = new BusinessException();
		
		// 1. Recuperer
		
        
		
//		try {
//        	validationPseudo( identifiant );
//        } catch ( Exception e ) {
//        	System.out.println("in erreur");
//        	BusinessException businessException = new BusinessException();
//        	businessException.ajouterErreur(CodesResultatBLL.REGLE_VALIDER_PSEUDO);
//        }
//        return identifiant;
//
//        /* Validation du champ mot de passe. */
//        try {
//            validationMotDePasse( motDePasse );
//        } catch ( Exception e ) {
//            setErreur( CHAMP_PASS, e.getMessage() );
//        }
//        utilisateur.setMotDePasse( motDePasse );
//
//        /* Initialisation du résultat global de la validation. */
//        if ( erreurs.isEmpty() ) {
//            resultat = "Succès de la connexion.";
//        } else {
//            resultat = "Échec de la connexion.";
//        }

        return this.connectionDAO.rechercheUser(identifiant, mdp);

    
	}
	
	
	/**
     * Valide l'adresse email saisie.
     */
    private void validationPseudo( String identifiant, BusinessException businessException) {
        if (identifiant == null || !identifiant.equals(businessException)) {
            throw new Exception( "Merci de saisir un pseudo valide." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String mdp ) throws Exception {
        if ( mdp != null ) {
            if ( mdp.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }
	
}
