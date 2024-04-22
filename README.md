# TicTacToe 2.0 - Baghli Yacine

### Project: TicTacToe 2.0

The objective was to create a networked game allowing two players to connect to a server and play matches, exchange messages, and use commands.

### Technical Choices:

In this projet, we prefered using the Cloud gaming technique since it would allow different users to play games that may require a significant amount of computing power without needing machines equipped with that computing power, but only requiring a stable internet connection.


### Regarding the added features:

- Addition of messages explaining the progress of the game

- Addition of a grid to facilitate game reading

- Addition of the real TicTacToe symbols

- Input management (You cannot place a symbol twice in the same box, you cannot place anything other than integers for row and column choices)

- Players can choose a nickname at the beginning of the game

- Addition of "replay", "replay and reset score to zero", and "quit" functionalities displayed via a menu at the end of each game *(Both players must agree on the choice to be made by discussing using the /msg command)*

- Addition in the case of a solo game of a menu with an additional option allowing the player to change the bot's difficulty between games

- Addition of a score that updates at the end of each game and is displayed at the beginning of each game

- The result of the game is displayed directly after one of the two players wins the game

- Customized messages based on players' nicknames (depending on the bot's level in solo mode)

- Addition of a feature to destabilize the opponent by sending them a rickroll **(Simply use the /msg command as explained in the "Commands" section, then press enter and enter "rickrollhim")**

- Each player has a 50% chance of starting each game

- Addition of a menu allowing the first player to choose the game mode. This player can wait for their opponent to connect by watching a documentary on black holes or just sleep, and as soon as their opponent connects, the game can start

- Addition of different commands

### Game Progression:
At the beginning of the game, the first player must choose whether to play Solo(1) or Duo(2) via a menu.

#### Solo Game

The player must then choose the difficulty level of their opponent. Each level has a specific gameplay strategy and a name representing its expertise level in TicTacToe.
The player must then enter their nickname.

The first turn is randomly assigned to either the player or the bot (the higher the bot's level, the more likely it is to play first). On the player's turn, they must choose a row and a column to place their symbol and can use commands during the game. Then it's the bot's turn and so on.
The scores are displayed at the end of the game, and the player can choose to replay, replay and reset the scores to zero, or quit the game.

The player can also quit the game at any time by typing "quit".

#### Duo Game

If the first player chooses to wait for the second player to connect, they can watch a documentary on black holes or just wait and sleep.

As soon as the second player connects, the game begins. The Duo game proceeds in the same way as a solo game except that players can send messages to each other via commands and can troll each other.


### Difficulty Levels:

If you select the "Solo" game mode, you will be prompted to choose the bot's difficulty level.
There are 4 different difficulty levels:

- **Beginner**: By choosing this level, the bot "Clément" will choose a box close to the one played in the last turn. If there are no boxes left near this box, the bot switches to amateur mode for one turn.

- **Amateur**: By choosing this level, the bot "Yacine" will choose a box randomly from the available and valid boxes on the board.

- **Pro**: By choosing this level, expect your opponent to have a real TicTacToe strategy, one of the best. Indeed, by choosing this level, the bot will take the name of one of the two inventors of the game and will follow a carefully chosen game algorithm, but one that is still beatable if you start first and play well...**(To come)**

- **Hacker**: By choosing this level, the probability of you winning the game tends towards zero (tends towards zero negatively even). The hacker has a one in four chance of winning each turn, and if they do not win the turn, they will distract you by sometimes using very questionable methods...
If luck is on your side and you manage to make a move before losing, you will still face an unbeatable opponent, well almost... Of course, against this player, you cannot use the 49.3 commands. And if by some miracle you manage to defeat him, he will find your IP address and that will be the end for you... Good luck... **(To come)**

- **Legend**: By choosing this level, expect to face the **"GOAT of TicTacToe"** who follows **THE** strategy to never lose at TicTacToe. With a little luck, you might achieve a draw... **(To come)**

    *(The developers quickly realized that the algorithm was too powerful, so they decided to implement only the first part of it at the Pro and Hacker levels, but you will face the complete version when playing against the last level...)*

### Commands:
There are two types of commands:

#### - "Weak" commands:

For a player to use these, they just need to try to place a symbol at (99;99). These commands are as follows:

- */matchnul*: proposes to the opponent whether they agree that neither player wins:
    - If the opponent answers "yes"; matchnul
    - If the opponent answers "no"; the players must continue playing

- */passe*: allows the player to skip their turn

- */msg*: allows sending a message to the opponent **(enter the /msg command, then press enter, then enter your message to send it to your opponent)**

- */maisquefaitclement **(easter egg)***: displays a window on the server with a text and a title

- By entering the word "quit" at any time during the game, you will exit the game


#### - "Powerful" commands:

For a player to use these, they just need to try to place a symbol at (49;3)

*(Any resemblance to actual persons, living or dead, or actual events is purely coincidental and could only be the result of pure coincidence).*

These commands are as follows:

- */jegagnesijeveux*: allows the player in question to win the game

- */matchnul*: forces a draw

- */inverser*: allows you to reverse your roles (if you are player 1 and your symbol is X, you will then play with the symbol of the opponent: O) **(If you use this command, you will also skip your turn otherwise it's too cheaty)**

**You cannot use certain commands when playing against advanced bot levels**

### Conclusion:
TicTacToe 2.0 represents an innovative approach to the classic game, offering enhanced features and interactions for players. Through the implementation of various difficulty levels, networked gameplay, and the addition of powerful commands, the game provides an engaging experience for both solo and duo players. This project not only demonstrates technical proficiency but also highlights the creativity and collaborative effort of the development team.
