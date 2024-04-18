package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * La classe ListeningThread est un thread qui écoute en permanence les messages envoyés par le serveur au client.
 * Elle lit les messages provenant du flux d'entrée du socket et les affiche sur la console.
 */
public class ListeningThread extends Thread{
	BufferedReader in;
	
	/**
     * Constructeur de la classe ListeningThread.
     * Initialise le flux d'entrée pour lire les messages provenant du socket.
     * @param s Le socket à partir duquel lire les messages
     * @throws IOException En cas d'erreur lors de la création du flux d'entrée
     */
	public ListeningThread(Socket s) throws IOException {
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	/**
     * Méthode principale du thread d'écoute.
     * Écoute en permanence les messages du serveur et les affiche sur la console.
     */
	public void run(){
		try {
		while (true) {
            // Lecture et affichage des messages du serveur
			System.out.println(in.readLine());
		}
		}catch (IOException e) {
            // En cas d'erreur de lecture ou de fermeture du flux, le thread se termine
		};
	}
}
