import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Game {

    //Instance variables for the player, dealer, deck, and scanner for input.
    private Player player;

    private Player dealer;
    private Deck deck;
    private Scanner input = new Scanner(System.in);

    private GameView window;

    // Game constructor, initializes the players with their names and hands, also creates the main deck.
    public Game()
    {
        player = new Player("Player", new ArrayList<Card>());
        dealer = new Player("Dealer", new ArrayList<Card>());

        deck = new Deck(new String[]{"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"},
                new String[]{"Hearts", "Clubs", "Diamonds", "Spades"},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10});

        window = new GameView(this);
    }

    // Print instructions method, just prints the instructions of blackjack in a big print statement.
    public void printInstructions()
    {
        System.out.println("Welcome to Blackjack!\n" +
                "\n" +
                "Rules:\n" +
                "1. The goal is to beat the dealer's hand without going over 21.\n" +
                "2. Cards 2 through 10 are worth their face value. J, Q, and K are worth 10. Aces are worth 1 or 11.\n" +
                "3. Each player starts with two cards, one of the dealer's cards is hidden until the end.\n" +
                "4. To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\n" +
                "5. If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.\n" +
                "6. The dealer will hit until their cards total 17 or higher.\n" +
                "7. Dealer will not stop until they have at least 17, even if all players bust.\n" +
                "8. If you and the dealer have the same total, it's a 'Push' and you get your bet back.\n\n");
    }


    // Main playGame method, does the main game logic but also calls other methods I've made.
    public void playGame(boolean printInstructions)
    {
        // Shuffles the deck, and then prints the instructions if PrintInstructions is true (meaning it's the first game).
        deck.shuffle();
        if (printInstructions)
        {
            printInstructions();
            delay(500);
        }

        System.out.println("\n---NEW GAME---");

        // Deals the initial 2 cards to both the player and dealer.
        player.addCard(deck.deal(player));
        player.addCard(deck.deal(player));
        dealer.addCard(deck.deal(dealer));
        dealer.addCard(deck.deal(dealer));

        // Shows the dealer's initial hand (second card hidden) and their visible point value
        System.out.println("\nDealer's hand: [hidden], " + dealer.getSecondCard());
        System.out.println("Dealer has " + dealer.getSecondCard().getPoint() + " visible points");
        System.out.println();

        // Calls the playTurn Method.
        playTurn();
    }

    // PlayTurn method, this lets the player choose to hit or stand.
    public void playTurn()
    {
        // Call to checkWinner to make sure that the player's points aren't over 21.
        checkWinner(false);
        window.repaint();

        // Show the player's hand so they can see points and cards.
        System.out.println("Here is your current hand: " + player);

        // Resets the hitOrStand string.
        String hitOrStand = "";

        // While loop that runs until the player inputs either hit or stand.
        while (!(hitOrStand.equals("hit") || hitOrStand.equals("stand")))
        {
            System.out.println("\nDo you want to hit or stand?");
            hitOrStand = input.nextLine();
        }

        // Short delay for *aesthetics*
        delay(250);

        // If the player chooses to hit, this gives them a card and then calls playTurn to repeat. Keep in mind that
        // at the start of playTurn, it checks if the player has over 21 points. If so, then it won't continue
        // the playTurn method and will instead show that the player lost.
        if (hitOrStand.equals("hit"))
        {
            player.addCard(deck.deal(player));
            playTurn();
        }
        // Else if the player chooses to stand, it will do a while loop that runs until the dealer either busted
        // or is less or equal to 21 with a higher point count than the player.
        else if(hitOrStand.equals("stand"))
        {
            while(dealer.getPoints() < 21 && dealer.getPoints() < player.getPoints())
            {
                System.out.println("Dealer gained " + dealer.addCard(deck.deal(dealer)).getPoint() +
                        " points for a total of " + dealer.getPoints() + " points");

            }
            // Once the while loop is done, it determines the winner and comparePointValues is true because I want to
            // check if the player or dealer has more points.
            checkWinner(true);
        }
    }

    // Method I can call to reset the game and allow the player to restart it by typing "yes" or "no".
    // It then will make a new game object and call playGame on it.
    public void restartGame()
    {
        // Initializes playAgainInput String variable which I use to see if the player wants to restart the game.
        String playAgainInput = "";

        // While the playAgainInput isn't yes or no, re-prompt the user to answer either yes or no.
        while (!(playAgainInput.equals("yes") || playAgainInput.equals("no")))
        {
            System.out.println("\nWould you like to play again?");
            playAgainInput = input.nextLine();

            // If playAgainInput is yes, make a new game object, call playGame on it, and then return from this method.
            if (playAgainInput.equals("yes"))
            {
                Game game = new Game();
                game.playGame(false);
                return;
            }
            // Else if playAgainInput is no, just return and exit the program.
            else if (playAgainInput.equals("no"))
            {
                return;
            }
        }
    }

    // Delay method to pause the game for a certain number of ms for effect (I found this online btw).
    public static void delay(int delayTime)
    {
        try
        {
            Thread.sleep(delayTime);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    // Method to check the winner, takes in a boolean comparePointValues to see if it should compare the values.
    // It only should compare the player and dealer's point values if the game is over,
    // so I added the bool to tell it to not compare when just checking if the player is over 21.
    public void checkWinner(boolean comparePointValues)
    {
        // Here is the check for if the player lost, it checks if comparePointValues is true, and if so,
        // checks if the dealer has more points than the player. Or, if the player has more
        // than 21 points, they also lose. Then it calls restartGame.

        if (comparePointValues && dealer.getPoints() > player.getPoints() && dealer.getPoints() <= 21 ||
                player.getPoints() > 21)
        {
            System.out.println("You lost with " + player.getPoints() + " points! The dealer had " +
                    dealer.getPoints() + " points.");
            restartGame();
        }
        // If the player didn't lose, checks if they have 21 and then gives them a blackjack and calls restartGame.
        else if (player.getPoints() == 21)
        {
            System.out.println("BlackJack with " + player.getPoints() + " points!");
            restartGame();
        }
        // Here is the check for if the player won, it checks if the comparePointValues is true, and if so, it will
        // check if the player has more points than the dealer. Or, if the dealer has more than 21 points, the
        // player wins. It then calls restartGame.
        else if (comparePointValues && player.getPoints() > dealer.getPoints() || dealer.getPoints() > 21)
        {
            System.out.println("You won with " + player.getPoints() + " points! The dealer had " +
                    dealer.getPoints() + " points.");
            restartGame();
        }
        // If comparePointValues is true and the player and dealer have the same point values, it shows that
        // there was a tie with x amount of points. It then calls restartGame.
        else if (comparePointValues && player.getPoints() == dealer.getPoints())
        {
            System.out.println("Tie with " + player.getPoints() + " points!");
            restartGame();
        }
    }


    public Player getDealer() {
        return dealer;
    }


    public Player getPlayer() {
        return player;
    }


    // Main method that makes a new Game.java object and calls the playGame() method on it.
    public static void main(String[] args)
    {
        Game game = new Game();
        game.playGame(true);
    }
}
