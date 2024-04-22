package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * La classe MainServer est responsable du démarrage du serveur TicTacToe.
 * Elle écoute les connexions des joueurs et démarre une nouvelle partie dès que deux joueurs se connectent.
 */
public class MainServer {
    
    private static final String ETOILE = "\n***************************************";
	
    
    /**
     * Méthode principale pour démarrer le serveur.
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application)
     */
    public static void main(String[] args) {
    
        try {
            ServerSocket ecoute = new ServerSocket(1500);
            System.out.println("Serveur lancé!");
            
            boolean nouvellePartie = true;
            boolean enAttenteDeDeuxiemeJoueur = false;
            Socket joueur1 = null;
            Socket joueur2 = null;
            

            // Boucle d'écoute des connexions des joueurs
            while (true) {
                
                // Attente de la connexion du joueur
                Socket joueur = ecoute.accept();
                PrintWriter out1 = new PrintWriter(joueur.getOutputStream(), true);
                
                if (enAttenteDeDeuxiemeJoueur) {
                    // Si un joueur est déjà connecté et que nous attendons le deuxième
                    joueur2 = joueur;
                    enAttenteDeDeuxiemeJoueur = false;
                    
                    // Envoyer un message au premier joueur pour indiquer que l'adversaire s'est connecté
                    envoyerMessage(joueur1, ETOILE+"\nVotre adversaire s'est connecté.\nLa partie peut commencer!\n"+ETOILE);
                    envoyerMessage(joueur2, "La partie peut commencer!");
                    
                    // Démarrage d'une nouvelle partie
                    Partie partie = new Partie(joueur1, joueur2);
                    partie.start();
                    
                    // Réinitialisation pour une nouvelle partie
                    joueur1 = null;
                    joueur2 = null;
                    nouvellePartie = true;
                } else {
                    // Si aucun joueur n'est connecté ou si un seul joueur est connecté
                    joueur1 = joueur;
                    
                    if (nouvellePartie) {
                        // Si c'est la première partie ou si une partie est terminée
                        nouvellePartie = false;
                        
                        // Affichage du menu de sélection du mode de jeu chez le joueur 1
                        envoyerMessage(joueur1, ETOILE+"\n\t- Nouvelle partie -"+ETOILE+"\nSélection du mode de jeu :\n1 - Solo\n2 - Duo");
                        
                        // Lecture du choix du mode de jeu
                        int choixModeDeJeu = lireChoixModeDeJeu(joueur1);
                        
                        if (choixModeDeJeu == 2) {
                            enAttenteDeDeuxiemeJoueur = true;
                            envoyerMessage(joueur1, ETOILE+"\nVeuillez attendre que votre adversaire\nse connecte...\nVous pouvez en attendant regarder un\ndocumentaire sur les trous noirs...\n1 - Regarder\n2 - Zzz");
                            
                            
                            while (joueur2 == null) {
                                // Attendre que le joueur 1 choisisse une option
                                int option = lireChoixModeDeJeu(joueur1);
                                if (option==1) {
                                    envoyerMessage(joueur1, "Voici un documentaire sur les trous noirs\nen attendant votre adversaire...");
                                    Partie.ouvrirYoutube(out1,"https://www.youtube.com/watch?v=_AR-yX_GIuc");
                                } else if (option==2) {
                                    envoyerMessage(joueur1, "Zzz...");
                                    break;
                                } else {
                                    envoyerMessage(joueur1, "Veuillez entrer une option valide (1 ou 2).");
                                }
                                

                            }
                        } else {
                            // Démarrage d'une partie solo avec un seul joueur connecté
                            Partie partieSolo = new Partie(joueur1);
                            partieSolo.start();
                           
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Lit le choix du mode de jeu saisi par le joueur.
     * @param joueur Le socket du joueur
     * @return Le choix du mode de jeu
     */
    private static int lireChoixModeDeJeu(Socket joueur) throws IOException {
    	
        BufferedReader reader = new BufferedReader(new InputStreamReader(joueur.getInputStream()));
    	String input = reader.readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            envoyerMessage(joueur, "Veuillez entrer un numéro valide.\n");
            return lireChoixModeDeJeu(joueur); // Demander à nouveau le choix en cas d'entrée non valide
        }
    }
    
    /**
     * Envoie un message au joueur.
     * @param joueur Le socket du joueur
     * @param message Le message à envoyer
     */
    private static void envoyerMessage(Socket joueur, String message) throws IOException {
        PrintWriter writer = new PrintWriter(joueur.getOutputStream(), true);
        writer.println(message);
    }
}
