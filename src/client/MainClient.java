package client;
import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 * La classe MainClient représente le client du jeu TicTacToe.
 * Elle se connecte au serveur TicTacToe et permet à l'utilisateur d'envoyer des messages au serveur.
 */
public class MainClient {
	
	/**
     * Méthode principale pour démarrer le client.
     * @param args Les arguments de la ligne de commande (non utilisés dans cette application)
     */
	public static void main(String[] args) {
		try {
			
            // Connexion au serveur TicTacToe sur le port 1500
			Socket s = new Socket("127.0.0.1", 1500);
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			
            // Démarrage du thread d'écoute pour les messages du serveur
			new ListeningThread(s).start();
			System.out.println("Connexion réussie!");
			
            // Lecture des messages depuis l'entrée utilisateur et envoi au serveur
			Scanner sc=new Scanner(System.in);
			String message="";
			while (!message.equals("quit")) {
			message=sc.nextLine();
			out.println(message);
			}
			sc.close();
			s.close();
			} catch(Exception e) {
			// Traitement d'erreur
			}

	}
}
