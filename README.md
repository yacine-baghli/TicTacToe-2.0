# TicTacToe 2.0 - Baghli Yacine - Rayer Clément

## Choix technique :

Nous préférons la technique du Cloud gaming puisqu'elle permetterait aux différents utilisateurs de jouer à des jeux qui peuvent nécessiter une puissance de calcul assez conséquente sans avoir à utiliser des machines dotées de cette puissance de calcul mais uniquement d'une connexion internet stable.


## Concernant les fonctionnalité ajoutées :

- Ajout d'une grille ainsi que certains messages expliquant le déroulement de la partie

- A la place des 1 et -1 on joue avec des X et des O, ajout de quelques messages expliquant le déroulement de la partie, ajout de lignes sur le plateau

- On ne peut pas replacer un symbole deux fois dans la meme case, on ne peut pas placer autre chose que des entiers pour le choix de lignes et de colonnes (gestion des inputs)

- Les joueurs peuvent au début de la partie choisir un pseudo

- Ajout des fonctionnalités "rejouer", "rejouer et remettre le score à zéro" et "quitter" qui s'afficheront sur un menu à la fin de chaque partie *(Les deux joueurs doivent se mettre d'accord sur le choix à faire en discutant grace à la commande /msg)*

- Ajout d'un score qui se met à jour à la fin de chaque partie et qui s'affiche à chaque début de partie

- Les résultats s'afficheront directement apres que l'un des deux joueurs ait gagné la partie

- Messages personnalisés en fonction du pseudo des joueurs

- Ajout d'une fonctionnalité permettant de déstabiliser l'adversaire en lui envoyant un rickroll **(Il suffit d'utiliser la commande /msg comme expliqué dans la section "Commandes" puis d'appyer sur entrée puis de saisir "rickrollhim")**

- Ajout d'une fonctionnalité qui fait en sorte que chaque joueur ait 50% de chance de jouer en premier à chaque début de partie

- Ajout de différentes commandes

## Commandes :
Il existe deux types de commandes :

### - Les commandes "faibles" :

Pour qu'un joueur puisse utiliser ces dernières il lui suffit d'essayer de placer un symbole en (99;99). Ces commandes sont les suivantes :

- */matchnul* : propose à l'adversaire si il accepte qu'aucun des deux joueurs ne gagne : 
    -  Si l'adversaire répond "oui"; matchnul
    -  Si l'adversaire répond "non"; les joueurs doivent continuer à jouer 

- */passe* : permet au joueur de passer son tour

- */msg* : permet d'envoyer un message à l'adversaire **(entrez la commande /msg puis appyez sur entrée puis entrez votre message pour l'envoyer à votre adversaire)**
 
- */maisquefaitclement **(easter egg)*** : affiche une fenetre sur le serveur avec un texte et un titre

 - En entrant le mot "quit" à n'importe quel moment de la partie vous quitterez la partie


### - Les commandes "puissantes" :

Pour qu'un joueur puisse utiliser ces dernières il lui suffit d'essayer de placer un symbole en (49;3) 

*(Toute ressemblance avec des faits et des personnages existants ou ayant existé serait purement fortuite et ne pourrait être que le fruit d'une pure coïncidence).*

Ces commandes sont les suivantes :

- */jegagnesijeveux* : permet au joueur en question de gagner la partie
	
- */matchnul* : permet de forcer la nulle

- */inverser* : permet d'inverser vos roles (si vous etes le joueur 1 et que votre symbole est X vous jouerez alors avec le symbole du joueur adverse : O) **(Si vous utilisez cette commande vous passerez également votre tour sinon c'est trop cheaté)**

## Prochaine mise à jour :
- Ajout d'une fonctionnalité qui permetterait au joueur de jouer en solo ; l'idéal serait qu'il puisse choisir un niveau de difficulté
- Ajout d'un menu permettant au premier joueur de choisir de joueur en solo ou pas


*(J'ai eu l'occasion d'avancer entre les deux séances de TP (du 16/03 au 21/03) sur le tictactoe. J'ai pendant ce temps avancé sur le projet à rendre avant de passer à la question 2.2 j'ai continué par la suite à ajouter des fonctionnalités régulièrement)*

Voila, merci de m'avoir lu - Baghli Yacine
