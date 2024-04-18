package tictactoe;

/**
 * La classe Plateau représente le plateau de jeu TicTacToe.
 */
public class Plateau {

	public int[][] grille = new int[3][3];
	public boolean inverser=false;
    
	private static final String ETOILE = "\n***************************************";

	private int etatPartie;
	
	 /**
     * Constructeur de la classe Plateau. Initialise le plateau de jeu avec des cases vides.
     */
	public Plateau() {
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				grille[i][j]=0;
			}
		}
	}
	
	
	/**
     * Définit l'état de la partie.
     * @param etatPartie L'état de la partie à définir
     */
	public void setEtatPartie(int etatPartie) {
		this.etatPartie=etatPartie;
	}
	
	
	/**
     * Récupère l'état actuel de la partie.
     * @return L'état actuel de la partie
     */
	public int getEtatPartie() {
		return this.etatPartie;
	}
	


	/**
     * Génère une représentation textuelle du plateau de jeu.
     * @return La représentation textuelle du plateau de jeu
     */
	public String toString() {
        StringBuilder str = new StringBuilder();
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(inverser) {
                    char symbole = grille[i][j] == 1 ? 'O' : (grille[i][j] == -1 ? 'X' : ' ');
		            str.append(symbole);
                }else {
                	char symbole = grille[i][j] == 1 ? 'X' : (grille[i][j] == -1 ? 'O' : ' ');
		            str.append(symbole);
                }
                if (j < 2) {
                    str.append("|");
                }
            }
            if (i < 2) {
                str.append("\n-|-|-\n");
            }
        }
//		str.append("\n");
		str.append(ETOILE);
        return str.toString();
	}
	
	
	 /**
     * Place le symbole d'un joueur sur une case du plateau.
     * @param i L'index de la ligne
     * @param j L'index de la colonne
     * @param joueur Le numéro du joueur (1 ou 2)
     */
	public void set(int i,int j,int joueur) {
		if (joueur==1)grille[i][j]=1;
		if ((joueur==2)||(joueur==5))grille[i][j]=-1;
	}
	
	
	/**
     * Calcule la somme des valeurs d'une ligne du plateau.
     * @param i L'index de la ligne
     * @return La somme des valeurs de la ligne
     */
	public int sommeLigne(int i) {
		int somme=0;
		for (int j=0;j<3;j++) {
			somme+=grille[i][j];
		}
		return somme;
	}
	
	
	/**
     * Calcule la somme des valeurs d'une colonne du plateau.
     * @param j L'index de la colonne
     * @return La somme des valeurs de la colonne
     */
	public int sommeColonne(int j) {
		int somme=0;
		for (int i=0;i<3;i++) {
			somme+=grille[i][j];
		}
		return somme;
	}
	
	/**
     * Calcule la somme des valeurs de la première diagonale du plateau.
     * @return La somme des valeurs de la première diagonale
     */
	public int sommeDiag1() {
		int somme=0;
		for (int i=0;i<3;i++) {
			somme+=grille[i][i];
		}
		return somme;
	}
	
	
	/**
     * Calcule la somme des valeurs de la deuxième diagonale du plateau.
     * @return La somme des valeurs de la deuxième diagonale
     */
	public int sommeDiag2() {
		int somme=0;
		for (int i=0;i<3;i++) {
			somme+=grille[i][2-i];
		}
		return somme;
	}
	
	
	/**
     * Met à jour l'état de la partie en fonction de l'état actuel du plateau.
     */
	public void etatPartie() {
        // Tests sur les lignes
	    for (int i = 0; i < 3; i++) {
	        int sommeLigne = sommeLigne(i);
	        if (sommeLigne == 3) {
	            if(inverser)setEtatPartie(-1); 
	            else setEtatPartie(1);
	            return;
	        } else if (sommeLigne == -3) {
	        	if(inverser)setEtatPartie(1);
	        	else setEtatPartie(-1);
	            return;
	        }
	    }
        // Tests sur les colonnes
	    for (int j = 0; j < 3; j++) {
	        int sommeColonne = sommeColonne(j);
	        if (sommeColonne == 3) {
	            
	        	if(inverser) setEtatPartie(-1);
	        	else setEtatPartie(1);
	            return;
	        } else if (sommeColonne == -3) {
	        	if(inverser) setEtatPartie(1);
	        	else setEtatPartie(-1);
	            return;
	        }
        }
        // Tests sur les diagonales
	    if (sommeDiag1() == 3 || sommeDiag2() == 3) {
	    	if(inverser) setEtatPartie(-1);
	    	else setEtatPartie(1);
	        return;
	    } else if (sommeDiag1() == -3 || sommeDiag2() == -3) {
	    	if(inverser) setEtatPartie(1);
	    	else setEtatPartie(-1);
	    	return;
	    }
    
	    
        // Vérification s'il reste des cases vides
	    boolean grilleRemplie = true;
	    
	    for (int i = 0; i < 3; i++) {
	        for (int m = 0; m < 3; m++) {
	            if (grille[i][m] == 0) {
	                grilleRemplie = false;
	                break;
	            }
	        }
	        if (!grilleRemplie) {
	            break;
	        }
	    }
	    if (grilleRemplie) {
	        setEtatPartie(0); //match nul si y a plus de cases
	        return;
	    }
        // S'il reste des cases vides, la partie continue
	    setEtatPartie(-2);
	}
	
}
