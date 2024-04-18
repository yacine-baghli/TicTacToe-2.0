## TicTacToe 2.0 - Baghli Yacine

### Project: TicTacToe 2.0

The objective was to create a networked game allowing two players to connect to a server and play matches, exchange messages, and use commands.

### Technical Choices:

In this project, we preferred to use Cloud gaming technology as it would allow different users to play games that may require significant computational power without needing machines equipped with such power but only a stable internet connection.

### Features of TicTacToe 2.0:

- Addition of a grid along with certain messages explaining the game's progress.

- Instead of using 1 and -1, we use X and O. Added explanatory messages for the game's progress and added lines on the board.

- It's not possible to place a symbol twice in the same box, and only integers can be placed for the choice of rows and columns (input management).

- Players can choose a nickname at the start of the game.

- Added features for "play again," "play again and reset the score to zero," and "quit," which will be displayed on a menu at the end of each match *(Both players must agree on the choice by discussing using the /msg command)*.

- Added a score that updates at the end of each match and displays at the beginning of each match.

- Results are displayed directly after one of the players wins the match.

- Personalized messages based on the players' nicknames.

- Added a feature allowing players to unsettle their opponent by sending them a rickroll **(Simply use the /msg command as explained in the "Commands" section, then press enter, and enter "rickrollhim")**.

- Added a feature where each player has a 50% chance of playing first at the beginning of each match.

- Added a menu allowing the first player to choose to play solo or not. This player can wait for their opponent to connect by watching a documentary on black holes.

- Added weak and powerful commands.

### Match Progression:
At the start of the match, the first player must choose whether to play Solo(1) or Duo(2) via a menu.

#### Solo Match

The player must then choose the difficulty level of their opponent. Each level has a specific gameplay strategy and a name representing its TicTacToe expertise level.
The player must then enter their nickname.

The first turn is randomly assigned to either the player or the bot (the higher the bot's level, the more likely it is to play first). When it's the player's turn, they must choose a row and a column to place their symbol and can use commands during the match. Then it's the bot's turn and so on.
The scores are displayed at the end of the match, and the player can choose to play again, play again and reset the scores to zero, or quit the game.

The player can also quit the match at any time by typing "quit".

#### Duo Match

If the first player chooses to wait for the second player to connect, they can watch a documentary on black holes or just wait and sleep.

As soon as the second player connects, the match begins. The Duo match proceeds in the same way as a solo match, except that players can send messages to each other via commands and can troll each other.

### Difficulty Levels:

If you select the "Solo" game mode, you will be prompted to choose the bot's difficulty level.
There are 4 different difficulty levels:

- Beginner: By choosing this level, the bot "Clément" will choose a box near the one played in the last turn. If there are no more boxes near this box, the bot switches to amateur mode for one turn.

- Amateur: By choosing this level, the bot "Yacine" will randomly choose a valid and available box on the board.

- Pro: By choosing this level, expect your opponent to have a real TicTacToe strategy, one of the best. Indeed, by choosing this level, the bot takes the name of one of the two inventors of the game.

- Hacker: By choosing this level, the probability of winning the match tends to zero. The hacker has a one in four chance of winning each turn. If luck is on your side, you will still face an unbeatable opponent, well almost... Of course, against this player, you cannot use the commands from 49.3. Good luck...

### Commands:
There are two types of commands:

#### - Weak Commands:

To use these commands, a player just needs to try to place a symbol at (99;99). These commands are as follows:

- */matchnul*: Proposes to the opponent if they agree that neither of the two players wins:
    - If the opponent answers "yes"; matchnul
    - If the opponent answers "no"; the players must continue playing

- */passe*: Allows the player to pass their turn

- */msg*: Allows sending a message to the opponent **(Enter the /msg command, then press enter, and enter your message to send it to your opponent)**

- */maisquefaitclement **(easter egg)***: Displays a window on the server with a text and a title

- By entering the word "quit" at any time during the match, you will leave the match.

#### - Powerful Commands:

To use these commands, a player just needs to try to place a symbol at (49;3)

*(Any resemblance to actual events or characters, living or dead, would be purely coincidental).*

- /jegagnesijeveux: Allows the player to win the match

- /matchnul: Forces a draw

- /inverser: Allows you to switch roles (if you are player 1 and your symbol is X, you will then play with the opponent's symbol: O) (If you use this command, you will also skip your turn otherwise it's too overpowered)

(Any use of these commands is subject to fair play and mutual agreement between the players).

### Conclusion:
TicTacToe 2.0 represents an innovative approach to the classic game, offering enhanced features and interactions for players. Through the implementation of various difficulty levels, networked gameplay, and the addition of powerful commands, the game provides an engaging experience for both solo and duo players. This project not only demonstrates technical proficiency but also highlights the creativity and collaborative effort of the development team.


*(J'ai eu l'occasion d'avancer entre les deux séances de TP (du 16/03 au 21/03) sur le tictactoe. J'ai pendant ce temps avancé sur le projet à rendre avant de passer à la question 2.2 j'ai continué par la suite à ajouter des fonctionnalités régulièrement)*

Voila, merci de m'avoir lu - Baghli Yacine
