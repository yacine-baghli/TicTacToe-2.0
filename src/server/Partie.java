package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tictactoe.Plateau;
import java.awt.Desktop;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * La classe Partie représente une partie de Tic Tac Toe entre deux joueurs.
 */
public class Partie extends Thread {

    //TODO ajouter le reste des messages prédéfinis
    
    //TODO ajouter une fonction stop qui permettera de stopper la partie dans le cas où on utilise des commandes
	
	//TODO ajouter une partie permettant de gérer l'input du deuxième utilisateur qui ne peut pas prendre de décision; s'il essaie d'en faire il ne pour pas choisir de ligne au prochain tour puisqu'il aura deja entré un input
	
    // Flux de lecture et d'écriture pour les deux joueurs
    private final BufferedReader in1;
    private final PrintWriter out1;
    private final BufferedReader in2;
    private final PrintWriter out2;
    
    // Plateau de jeu
    private Plateau plateau;

    // État de la partie
    private boolean enCours = true;
    
    private boolean hackerMode = false;//TODO ajouter à la partie ou y a la commande /jegagnesi jeveux pour la bloquer
    
    // Scores des joueurs
    private int scoreJoueur1 = 0;
    private int scoreJoueur2 = 0;
    private int scoreBot = 0;

    
    private Random random;
    
    // Messages prédéfinis
    private static final String YOUR_TURN_MSG = "C'est votre tour :";
    private static final String OPPONENT_TURN_MSG = "%s choisit une ligne et une colonne...";
    private static final String COMMANDS_MSG = "Pour utiliser des commandes faibles, saisissez /commande (par exemple, /matchnul, /passe, /msg)";
    private static final String MESSAGE_INEXISTANTE = "Case innéxistante, veuillez choisir une\nautre case.";
    private static final String MESSAGE_DEJA_SYMBOLE = "Il y a deja un symbole sur cette case,\nveuillez choisir une autre case.";
    
    private static final String ETOILE = "\n***************************************";
    private static final String RAISON_AVEC_OPTIONS = "Fin de la partie."+ETOILE+"\nVoulez vous :\n1 - Rejouer\n2 - Rejouer et remettre le score à zéro\n3 - Quitter"+ETOILE;
//    private static final String FIN_PARTIE = "Fin de la partie."+ETOILE;
    private static final String QUITTER_PARTIE = "Votre adversaire a quitté la partie."+ETOILE;
    
    private static final String OPEN_YOUTUBE_RICKROLL = "rickrollhim";
    private static final String RAISON_AVEC_OPTIONS_SOLO="Fin de la partie."+ETOILE+"\nVoulez vous :\n1 - Rejouer\n2 - Rejouer et remettre le score à zéro\n3 - Quitter"+ETOILE;//TODO à changer

    private static final String CHOIX_PAR_DEF=ETOILE+"\nMode de difficulte : Débutant."+ETOILE;
    private static final String CHOIX_AMATEUR=ETOILE+"\nMode de difficulte : Amateur."+ETOILE;
    private static final String CHOIX_PRO=ETOILE+"\nMode de difficulte : Pro"+ETOILE;
    private static final String CHOIX_HACKER=ETOILE+"\nMode de difficulte : Hacker"+ETOILE;

    private int derniereLigne=-1;
    private int derniereColonne=-1;
    
    // Noms des joueurs
    private String joueur1Name;
    private String joueur2Name;

    
 // Constantes pour les choix de mode de jeu
    private static final int MODE_DUO = 2;
    private static final int MODE_SOLO = 1;
    
    
//    //TODO à ajouter
    private String NomNiveau="";
    private int Difficulte=0;
    
    private static final String DEBUTANT = "Clement";
    private static final String AMATEUR = "Yacine";
    private static final String PRO = choisirUnDesDeuxCreateurs();
    private static final String HACKER = genererNom();    
    
    private int modeDeJeu; // Mode de jeu sélectionné

    
    /**
     * Constructeur de la classe Partie.
     * @param joueur1 Socket pour le joueur 1
     * @param joueur2 Socket pour le joueur 2
     * @throws IOException 
     */
    public Partie(Socket joueur1, Socket joueur2) {
        modeDeJeu=MODE_DUO;
    	try {
        	
            // Initialisation des flux de lecture et d'écriture pour les deux joueurs
        	in1 = new BufferedReader(new InputStreamReader(joueur1.getInputStream()));
            out1 = new PrintWriter(joueur1.getOutputStream(), true);
            in2 = new BufferedReader(new InputStreamReader(joueur2.getInputStream()));
            out2 = new PrintWriter(joueur2.getOutputStream(), true);
            // Initialisation du plateau de jeu
            plateau = new Plateau();
            // Initialisation de l'objet Random
            random = new Random();

        } catch (Exception e) {
         
            // En cas d'erreur lors de la création de la partie
        	throw new RuntimeException("Erreur lors de la création de la partie", e);
        
        }
    }

    public Partie(Socket joueur1) {
    	modeDeJeu=MODE_SOLO;
    	this.in2 = null;
		this.out2 = null;
		
    	try {
        	
            // Initialisation des flux de lecture et d'écriture pour les deux joueurs
        	in1 = new BufferedReader(new InputStreamReader(joueur1.getInputStream()));
            out1 = new PrintWriter(joueur1.getOutputStream(), true);
            // Initialisation du plateau de jeu
            plateau = new Plateau();
            // Initialisation de l'objet Random
            random = new Random();

        } catch (Exception e) {
         
            // En cas d'erreur lors de la création de la partie
        	throw new RuntimeException("Erreur lors de la création de la partie", e);
        
        }
		
    }
    
    public int getModeDeJeu() {
    	return this.modeDeJeu;
    }

    public void setmodeDeJeu(int modeDeJeu) {
    	this.modeDeJeu=modeDeJeu;
    }
    
    public void derniereCase(int i, int j) {
    	this.derniereLigne=i;
    	this.derniereColonne=j;
    }
    
    /**
     * Méthode exécutée lors du lancement de la partie.
     */
    public void run() {
        if ((modeDeJeu == MODE_DUO)||(modeDeJeu == MODE_SOLO)) {
        	initialiserPartie();
        	premierePartie();
            jouerUnePartie();
        } else {
            System.out.println("Mode de jeu non valide.");
        }
    }
    
    
    /**
     * Initialise la première partie.
     */
    public void premierePartie() {
    	
    	if(modeDeJeu == MODE_SOLO)menuSolo();
    	
    	// Demande aux joueurs d'entrer leurs noms
    	joueur1Name = demanderNom(in1, out1);
    	if(modeDeJeu==MODE_DUO)joueur2Name = demanderNom(in2, out2);

        // Messages de bienvenue pour les deux joueurs
    	envoyerMessage(out1, ETOILE+"\n\nBienvenue dans cette nouvelle partie de\nTicTacToe2.0 " + joueur1Name+", n'hésitez pas à bien\nlire le read me avant de commencer votre\npremière partie...");
    	if(modeDeJeu==MODE_DUO)envoyerMessage(out2, ETOILE+"\n\nBienvenue dans cette nouvelle partie de\nTicTacToe2.0 " + joueur2Name+", n'hésitez pas à bien\nlire le read me avant de commencer votre\npremière partie...");
    
    }
    
    public void menuSolo() {
    	envoyerMessage(out1, ETOILE+"\nVeuillez choisir le niveau de difficulte du bot :\n1 - Debutant\n2 - Amateur\n3 - Pro\n4 - Hacker");
        try {
	    	String message = "";
	        
	        while (!message.equals("1") && !message.equals("2") && !message.equals("3")&& !message.equals("4")) {
					message = in1.readLine();	
				}
	        if(message.equals("2")) {
	        	Difficulte=1;
	        	NomNiveau=AMATEUR;
	        	envoyerMessage(out1, CHOIX_AMATEUR);
	        }else if(message.equals("3")) {
	        	Difficulte=1;
	        	NomNiveau=PRO;
	        	envoyerMessage(out1, CHOIX_PRO);
	        }else if(message.equals("4")) {
	        	Difficulte=1;
	        	NomNiveau=HACKER;
	        	envoyerMessage(out1, CHOIX_HACKER);
	        }else{
//	        	Difficulte=0; //TODO Verifier
	        	NomNiveau=DEBUTANT;
	        	envoyerMessage(out1, CHOIX_PAR_DEF);
	        }
	        
	        
		} catch (IOException e) {
			e.printStackTrace();
        }

        
         
    }
    
    /**
     * Initialise une partie.
     */
    private void initialiserPartie() {
    	
    	// Initialisation du plateau de jeu et des scores
    	plateau = new Plateau();
    	plateau.setEtatPartie(-2);
    	enCours = true;
    	
        // Envoi du plateau de jeu aux joueurs ainsi que les scores
    	if(modeDeJeu==MODE_SOLO) {
    		
    		envoyerMessage(out1, ETOILE+"\nScore : " + joueur1Name + " - " + scoreJoueur1 + ", " + NomNiveau + " - " + scoreBot+ETOILE);
        	envoyerMessage(out1, plateau.toString());
    		
    	}else {
    		
    		envoyerMessage(out1, ETOILE+"\nScore : " + joueur1Name + " - " + scoreJoueur1 + ", " + joueur2Name + " - " + scoreJoueur2+ETOILE);
        	envoyerMessage(out1, plateau.toString());
            
    		envoyerMessage(out2, ETOILE+"\nScore : " + joueur1Name + " - " + scoreJoueur1 + ", " + joueur2Name + " - " + scoreJoueur2+ETOILE);
    		envoyerMessage(out2, plateau.toString());
    	}
        
    	
    	
    }
  

    /**
     * Joue une partie.
     */
    private void jouerUnePartie() {
        int etatPartie = plateau.getEtatPartie();
        int nbTours = 0;
        int joueur = random.nextInt(2) + 1; // Random entre 1 et 2 inclus
        
        // Afficher le message du premier joueur
        envoyerMessage(out1, ETOILE + "\nC'est au joueur " + joueur + " de commencer la partie.\nBonne chance !" + ETOILE);
        if (modeDeJeu == MODE_DUO) {
            envoyerMessage(out2, ETOILE + "\nC'est au joueur " + joueur + " de commencer la partie.\nBonne chance !" + ETOILE);
        }

        while (enCours && etatPartie == -2 && nbTours < 9) {
            
            tourDeJeu(joueur);

            if (etatPartie != -2) {
                break;
            }

            joueur = (joueur == 1) ? 2 : 1;

            // Afficher le plateau après le tour du joueur
            envoyerPlateau();
            
            nbTours++;
            plateau.etatPartie();
            etatPartie = plateau.getEtatPartie();
        }
        
        informerResultat(etatPartie, modeDeJeu);
    }
    

    /**
     * Effectue un tour de jeu pour un joueur donné.
     * @param joueur Le numéro du joueur
     */
    private void tourDeJeu(int joueur) {
        if (joueur == 1) {
            jouerTour(in1, out1, out2, joueur);
        } else {
        	if(modeDeJeu==MODE_DUO)jouerTour(in2, out2, out1, joueur);
        	else jouerTourBot();
        }
        plateau.etatPartie();
    }

    
    public void jouerTourBot(){
    	if (Difficulte==0) {
    		tourDebutant();
    	}else if(Difficulte==1){
    		tourAmateur();
    	}else if(Difficulte==2){
    		tourPro();
    	}else if(Difficulte==3){
    		tourHacker();
    	}
    }

    public void tourDebutant() {
        int iBot = derniereLigne;
        int jBot = derniereColonne;

        // List to store adjacent empty cells
        List<int[]> emptyAdjacentCells = new ArrayList<>();

        for (int di = -1; di <= 1; di++) {
            for (int dj = -1; dj <= 1; dj++) {
                int ni = iBot + di;
                int nj = jBot + dj;
                if (estCaseValide(ni, nj) && plateau.grille[ni][nj] == 0 && (ni != derniereLigne || nj != derniereColonne)) {
                    emptyAdjacentCells.add(new int[]{ni, nj});
                }
            }
        }

        if (emptyAdjacentCells.isEmpty()) {
            tourAmateur();
            return;
        }

        // Randomly choose one of the adjacent empty cells
        Random random = new Random();
        int[] selectedCell = emptyAdjacentCells.get(random.nextInt(emptyAdjacentCells.size()));

        // Update the bot's move
        plateau.set(selectedCell[0], selectedCell[1], 5);

        // Update the last move of the bot
        derniereLigne = selectedCell[0];
        derniereColonne = selectedCell[1];
    }

    public void tourAmateur() {
        // Initialisation des générateurs aléatoires
        Random random = new Random();

        // Tant que la case choisie n'est pas valide ou occupée, en choisir une autre
        int iBot, jBot;
        do {
            iBot = random.nextInt(3);
            jBot = random.nextInt(3);
        } while (!estCaseValide(iBot, jBot) || plateau.grille[iBot][jBot] != 0);

        // Poser le symbole dans la case choisie
        plateau.set(iBot, jBot, 5);
    }

    public void tourPro() {
    	//TODO ajouter la partie permettant de suivre les methodes de jeu 
    }
    
    public void tourHacker(){
    	//TODO de meme que tourPro mais avec une chance sur 4 de /jegagnesijeveux à chaque tour
    }
    	   
    
    /**
     * Joue un tour pour un joueur donné.
     * @param in BufferedReader pour l'entrée du joueur
     * @param out PrintWriter pour la sortie du joueur
     * @param opponentOut PrintWriter pour la sortie de l'adversaire
     * @param joueur Le numéro du joueur
     */
    private void jouerTour(BufferedReader in, PrintWriter out, PrintWriter opponentOut, int joueur) {
       
    	// Message indiquant que c'est au tour du joueur
    	String joueurNom = (joueur == 1) ? joueur1Name : joueur2Name;
        envoyerMessage(out, YOUR_TURN_MSG);
        if(modeDeJeu==MODE_DUO)envoyerMessage(opponentOut, String.format(OPPONENT_TURN_MSG, joueurNom, "Tour du joueur "));
        
        try {
            // Demande au joueur de choisir une ligne et une colonne
            int i = demanderEntree(in, out, "Numéro de ligne? (0, 1 ou 2)");
            int j = demanderEntree(in, out, "Numéro de colonne? (0, 1 ou 2)");
            
            // Vérification si le joueur veut exécuter une commande puissante
            if (i == 49 && j == 3) {
                
            	envoyerMessage(out, "Oulaaa tu veux quoi??");
                String message = "";
                
                while (!message.equals("/jegagnesijeveux") && !message.equals("/inverser") && !message.equals("/matchnul")) {
                    message = in.readLine();
                }

                if (message.equals("/jegagnesijeveux")) {
                    if(modeDeJeu==MODE_SOLO) {
                    	//TODO ajouter la partie permettant de bloquer la commande en hacker mode
                    }
                	envoyerMessage(out, "Vous avez gagné !");
                    envoyerMessage(opponentOut, "Votre adversaire a décidé de gagner directement...");
                    
                    if(joueur==1)scoreJoueur1++;
                    else if (joueur==2)scoreJoueur2++;
                    
                    if (modeDeJeu==MODE_DUO)stopPartieDuo(RAISON_AVEC_OPTIONS);
                    else stopPartieSolo(RAISON_AVEC_OPTIONS_SOLO);
                    
                } else if (message.equals("/matchnul")) {
                
                	envoyerMessage(out, "Match nul !");
                    envoyerMessage(opponentOut, "Votre adversaire a décidé de terminer le match sur un match nul...");
                    
                    if (modeDeJeu==MODE_DUO)stopPartieDuo(RAISON_AVEC_OPTIONS);
                    else stopPartieSolo(RAISON_AVEC_OPTIONS_SOLO);

                } else if (message.equals("/inverser")) {
                	
                    envoyerMessage(out, "Vous avez inversé les symboles !");
                    envoyerMessage(opponentOut, "Euh.. Votre adversaire a inversé les symboles !");
                    plateau.inverser = true;
                
                }
            }
            
            
            // Vérification si le joueur veut exécuter une commande faible
            if (i == 99 && j == 99) {
                
            	envoyerMessage(out, COMMANDS_MSG);
                String message = "";
                
                while (!message.equals("/matchnul") && !message.equals("/passe") && !message.equals("/msg") && !message.equals("/maisquefaitclement")) {
                    message = in.readLine();
                }

                if (message.equals("/matchnul")) {
                    
                	envoyerMessage(out, "Vous venez de proposer la nulle à votre adversaire");
                    envoyerMessage(opponentOut, "Le joueur adverse vous propose la nulle. (répondez par \"oui\" ou \"non\")");

                    String reponse;
                    
                    if (in == in1) {
                        reponse = in2.readLine();
                    } else {
                        reponse = in1.readLine();
                    }

                    if (reponse.equals("oui")) {
                    
                    	envoyerMessage(out, "Match nul !\nVotre adversaire a accepté la nulle.");
                        envoyerMessage(opponentOut, "Match nul !\nVotre avez accepté la nulle.");
                        if (modeDeJeu==MODE_DUO)stopPartieDuo(RAISON_AVEC_OPTIONS);
                        else stopPartieSolo(RAISON_AVEC_OPTIONS_SOLO);
                        
                    } else if (reponse.equals("non")) {
                        tourDeJeu(joueur);
                    }
                    
                }else if (message.equals("/passe")) {
                    
                	envoyerMessage(opponentOut, "Votre adversaire a passé son tour...");
                
                } else if ((message.equals("/msg")&&(modeDeJeu==MODE_DUO))) {
                	
                    envoyerMessage(out, "Entrez votre message :");

            	    String msg = in.readLine();
            	    
            	    if(msg.equals(OPEN_YOUTUBE_RICKROLL)){
            	    	if(joueur == 1) {
            	    		ouvrirYoutube(out2,"https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            	            out2.println("Vous vous etes fait rickroll par le joueur adverse,\nil semblerai qu'il essaie de vous déstabiliser...");
            	    	}
            	    	else {
            	    		ouvrirYoutube(out1,"https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            	            out1.println("Vous vous etes fait rickroll par le joueur adverse,\nil semblerai qu'il essaie de vous déstabiliser...");
            	    	}
            	    }else {
            	    
            	    	String senderName = (joueur == 1) ? joueur1Name : joueur2Name;
            	    	envoyerMessage(opponentOut, ETOILE+"\n"+senderName + " : " + msg+ETOILE);
            	    	envoyerMessage(out, ETOILE+"\nVous : " + msg+ETOILE);
            	    	tourDeJeu(joueur);
            	    
            	    }
        	    }else if (message.equals("/maisquefaitclement")) {
        	    	// Création d'une fenêtre
        	        JFrame fenetre = new JFrame("Suis je en binome ou pas ?");

        	        // Création d'un composant JLabel pour afficher le texte
        	        JLabel label = new JLabel("Mais que fait Clément?");
        	        label.setHorizontalAlignment(JLabel.CENTER); // Alignement du texte au centre

        	        // Ajout du composant JLabel à la fenêtre
        	        fenetre.getContentPane().add(label);

        	        // Configuration de la fenêtre
        	        fenetre.setSize(400, 200); // Taille de la fenêtre
        	        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Action à effectuer à la fermeture de la fenêtre
        	        fenetre.setVisible(true); // Rendre la fenêtre visible
                    
        	        envoyerMessage(out, "L'easter egg est apparu sur la machine serveur !");

        	        envoyerMessage(opponentOut, "Votre adversaire se demande ce que fait clement... et il a raison, QUE FAIT CLEMENT???");
        	    }
            }
            
            // Vérification de la validité de la case choisie
            if (!estCaseValide(i, j)) {
                envoyerMessage(out, MESSAGE_INEXISTANTE);
                tourDeJeu(joueur);
            }
            
            
            // Vérification si la case est déjà occupée
            if (plateau.grille[i][j] != 0) {
                envoyerMessage(out, MESSAGE_DEJA_SYMBOLE);
                if(modeDeJeu==MODE_DUO)envoyerMessage(opponentOut, "Votre adversaire ne fait pas la différence entre une case occupée et une case vide...");
                tourDeJeu(joueur);
            }
            
            // Mise à jour du plateau avec le symbole du joueur
            plateau.set(i, j, joueur);
            
            //TODO verifier
            if(joueur==1)derniereCase(i, j);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Demande à un joueur de saisir une valeur numérique.
     * @param in BufferedReader pour l'entrée du joueur
     * @param out PrintWriter pour la sortie du joueur
     * @param message Message à afficher pour demander la valeur
     * @return La valeur saisie par le joueur
     * @throws Exception En cas d'erreur de saisie
     */
    private int demanderEntree(BufferedReader in, PrintWriter out, String message) throws Exception {
    
    	int input = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            try {
                
            	envoyerMessage(out, message);
                input = Integer.parseInt(in.readLine());
                isValidInput = true;
            
            } catch (NumberFormatException e) {
                envoyerMessage(out, "Veuillez entrer un chiffre valide.");
            }
        }
        return input;
    }

    /**
     * Envoie le plateau de jeu aux deux joueurs.
     */
    private void envoyerPlateau() {
        
    	envoyerMessage(out1, plateau.toString());
    	if(modeDeJeu==MODE_DUO)envoyerMessage(out2, plateau.toString());
    
    }
    
   


    
    /**
     * Informe les joueurs du résultat de la partie.
     * @param etatPartie L'état final de la partie
     */
    private void informerResultat(int etatPartie, int modeDeJeu) {
        
    	String joueur1Message = "";
        String joueur2Message = "";

        if (etatPartie == 0) {
            
        	envoyerMessage(out1, "Match nul");
            if(modeDeJeu==2)envoyerMessage(out2, "Match nul");
//            if (modeDeJeu==MODE_DUO)stopPartieDuo(RAISON_AVEC_OPTIONS);
//            else stopPartieSolo(RAISON_AVEC_OPTIONS_SOLO);
            
        } else if (etatPartie == 1) {
            
        	joueur1Message = "Tu as gagné!";
            joueur2Message = "Tu as perdu...";
            
            if (!plateau.inverser) {
                scoreJoueur1++;
            } else {
            	if(modeDeJeu==MODE_DUO)scoreJoueur2++;
            	else scoreBot++;
            }
            
        } else {
        
        	joueur1Message = "Tu as perdu...";
            joueur2Message = "Tu as gagné!";
            
            if (!plateau.inverser) {
            	if(modeDeJeu==MODE_DUO)scoreJoueur2++;
            	else scoreBot++;
            } else {
                scoreJoueur1++;
            }
        }

        if (plateau.inverser) {
        	
            String temp = joueur1Message;
            joueur1Message = joueur2Message;
            joueur2Message = temp;
        
        }

        out1.println(joueur1Message);
        if(modeDeJeu==MODE_DUO)out2.println(joueur2Message);
        if (modeDeJeu==MODE_DUO)stopPartieDuo(RAISON_AVEC_OPTIONS);
        else stopPartieSolo(RAISON_AVEC_OPTIONS_SOLO);
    }

    
    /**
     * Envoie un message à un joueur.
     * @param out PrintWriter pour la sortie du joueur
     * @param message Le message à envoyer
     */
    private void envoyerMessage(PrintWriter out, String message) {
        out.println(message);
    }
    
    
    /**
     * Demande à un joueur d'entrer son pseudo.
     * @param in BufferedReader pour l'entrée du joueur
     * @param out PrintWriter pour la sortie du joueur
     * @return Le pseudo saisi par le joueur
     */
    private String demanderNom(BufferedReader in, PrintWriter out) {
        
    	String nom = "";
        
        try {
        
        	envoyerMessage(out, "Entrez votre pseudo :");
            nom = in.readLine();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nom;
    }
    
    
    /**
     * Vérifie si une case est valide.
     * @param i Numéro de ligne
     * @param j Numéro de colonne
     * @return true si la case est valide, false sinon
     */
    private boolean estCaseValide(int i, int j) {
        return (i >= 0 && i < 3 && j >= 0 && j < 3)||(i == 49 && j == 3)||(i == 99 && j == 99);
    }
    
    
    /**
     * Arrête la partie et affiche les options de fin de partie.
     * @param raison La raison de la fin de la partie
     */
    private void stopPartieDuo(String raison) {
        
    	enCours = false;
        envoyerMessage(out1, raison);
        envoyerMessage(out2, raison);
        
        try {
            
        	String choix1 = "jesuisunchoix";
        	String choix2 = "";
            
        	while(!choix1.equals(choix2)) {
        		

        		envoyerMessage(out2, "Veuillez attendre que votre adversaire fasse un choix...");
        		envoyerMessage(out1, "Veuillez vous mettre d'accord avec votre adversaire (Vous pouvez utiliser la commande /msg pour envoyer un message à votre adversaire)");
        		choix1 = in1.readLine();
        		
        		envoyerMessage(out1, "Veuillez attendre que votre adversaire fasse un choix...");
        		envoyerMessage(out2, "Veuillez vous mettre d'accord avec votre adversaire (Vous pouvez utiliser la commande /msg pour envoyer un message à votre adversaire)");
        		choix2 = in2.readLine();
        	}
        	
    		String choix=choix1;
    		
        	
            if (choix.equals("1")) {
        		envoyerMessage(out1, "Vous avez décidé de rejouer une partie");
        		envoyerMessage(out2, "Vous avez décidé de rejouer une partie");

                rejouerPartie();
            } else if (choix.equals("2")) {
        		envoyerMessage(out1, "Vous avez décidé de rejouer une partie et de remettre les scores à zéro");
        		envoyerMessage(out2, "Vous avez décidé de rejouer une partie et de remettre les scores à zéro");
            
            	scoreJoueur1 = 0;
                scoreJoueur2 = 0;
                rejouerPartie();
            
            } else {
            	if (modeDeJeu==MODE_DUO)stopPartieDuo(QUITTER_PARTIE);
                else stopPartieSolo(RAISON_AVEC_OPTIONS_SOLO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void stopPartieSolo(String raison) {
        
    	enCours = false;
        envoyerMessage(out1, raison);
        
        try {
            
        	String choix1 = "jesuisunchoix";
            
        	while((!choix1.equals("1"))&&(!choix1.equals("2"))&&(!choix1.equals("3"))&&(!choix1.equals("4"))) {
        		
        		envoyerMessage(out1, raison);
                	
        		choix1 = in1.readLine();
        		
        	}
        	
    		
            if (choix1.equals("1")) {
        		envoyerMessage(out1, "Vous avez décidé de rejouer une partie");

                rejouerPartie();
            } else if (choix1.equals("2")) {
        		envoyerMessage(out1, "Vous avez décidé de rejouer une partie et de remettre les scores à zéro");
        	
            	scoreJoueur1 = 0;
                scoreBot = 0;
                rejouerPartie();
                
            } else if (choix1.equals("3")) {
        		envoyerMessage(out1, "Veuillez choisir le niveau de difficulté de votre adversaire :\n1- Débutant\n2- Amateur\n3- Pro\n4-");
        	
            	scoreJoueur1 = 0;
                scoreBot = 0;
                rejouerPartie();
            
            } else {
                if (modeDeJeu==MODE_DUO)stopPartieDuo(RAISON_AVEC_OPTIONS);
                else stopPartieSolo(RAISON_AVEC_OPTIONS_SOLO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Relance une partie.
     */
    private void rejouerPartie() {
    	
    	initialiserPartie();
        jouerUnePartie();
    
    }
    
    
    static void ouvrirYoutube(PrintWriter out, String lien) {
        
        try {
            URI uri = new URI(lien);
            Desktop.getDesktop().browse(uri);
//            out.println("Vous vous etes fait rickroll par le joueur adverse, il semblerai qu'il essaie de vous déstabiliser...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    //TODO à finir
    public static String genererNom() {
    	String nom="爪哇_404";
    	
    	return nom;
    }
    
    public static String choisirUnDesDeuxCreateurs() {
    	String nom="";
    	Random random = new Random();
        int r = random.nextInt(2);

    	if(r==1)nom="Allan Goff";
    	else nom="Novatia Labs";
    	return nom;
    }
}