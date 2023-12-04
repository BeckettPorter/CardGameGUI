import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Game {

    Player player;
    Player dealer;
    Deck deck;

    Scanner input = new Scanner(System.in);

    //Game constructor, initializes the players with temporary names before I prompt them for names and makes the deck
    public Game()
    {
        player = new Player("Player", new ArrayList<Card>());
        dealer = new Player("Dealer", new ArrayList<Card>());

        deck = new Deck(new String[]{"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"},
                new String[]{"Hearts", "Clubs", "Diamonds", "Spades"},
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10});

    }

    // Print instructions, just prints the instructions of blackjack in a big print statement
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


    // Main playGame method where most game logic will be
    public void playGame(boolean printInstructions)
    {
        deck.shuffle();
        if (printInstructions == true)
        {
            printInstructions();
            delay(500);
        }

        System.out.println("\n---NEW GAME---");

        player.addCard(deck.deal(player));
        player.addCard(deck.deal(player));
        dealer.addCard(deck.deal(dealer));
        dealer.addCard(deck.deal(dealer));


        System.out.println("\nDealer's hand: [hidden], " + dealer.getSecondCard());
        System.out.println("Dealer has " + dealer.getSecondCard().getPoint() + " visible points");
        System.out.println();

        playTurn();



    }

    public void playTurn()
    {
        System.out.println("Here is your current hand: " + player);

        if (player.getPoints() > 21)
        {
            System.out.println("PLAYER LOST");
            restartGame();
            return;
        }
        if (player.getPoints() == 21)
        {
            System.out.println("Blackjack!");
            restartGame();
            return;
        }

        String hitOrStand = "";
        while (!(hitOrStand.equals("hit") || hitOrStand.equals("stand")))
        {
            System.out.println("\nDo you want to hit or stand?");
            hitOrStand = input.nextLine();
        }

        delay(250);

        if (hitOrStand.equals("hit"))
        {
            player.addCard(deck.deal(player));
            playTurn();
        }
        else if(hitOrStand.equals("stand"))
        {
            while(dealer.getPoints() < 21 && dealer.getPoints() < player.getPoints())
            {
                System.out.println("Dealer gained " + dealer.addCard(deck.deal(dealer)).getPoint() +
                        " points for a total of " + dealer.getPoints() + " points");

            }
            if (dealer.getPoints() > 21)
            {
                System.out.println("Dealer Lost with " + dealer.getPoints() + " points");
                restartGame();
            }
            else if (dealer.getPoints() > player.getPoints())
            {
                System.out.println("Dealer Wins with " + dealer.getPoints() + " points");
                restartGame();
            }
            else if (dealer.getPoints() == player.getPoints())
            {
                System.out.println("Draw at " + dealer.getPoints() + " points");
                restartGame();
            }
        }
    }


    public void restartGame()
    {
        String playAgainInput = "";
        while (!(playAgainInput.equals("yes") || playAgainInput.equals("no")))
        {
            System.out.println("\nWould you like to play again?");
            playAgainInput = input.nextLine();

            if (playAgainInput.equals("yes"))
            {
                Game game = new Game();
                game.playGame(false);
                return;
            }
            else if (playAgainInput.equals("no"))
            {
                return;
            }
        }
    }

    // Delay method to pause the game for a certain number of ms for effect (I found this online btw)
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

    // Main method that makes a new Game.java object and calls the playGame() method on it
    public static void main(String[] args)
    {
        Game game = new Game();
        game.playGame(true);
    }
}
