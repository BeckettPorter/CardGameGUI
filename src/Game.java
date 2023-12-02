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
        player = new Player("player", new ArrayList<Card>());
        dealer = new Player("dealer", new ArrayList<Card>());

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
                "8. If you and the dealer have the same total, it's a 'Push' and you get your bet back.\n");
    }


    // Main playGame method where most game logic will be
    public void playGame()
    {
        deck.shuffle();
        printInstructions();

        player.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());

        System.out.println("Your hand: " + player.toString());
        System.out.println("Dealer's hand: [hidden], " + dealer.getSecondCard());

        // Player's turn
        while (player.getPoints() <= 21)
        {
            playerTurn();
        }




    }

    public void playerTurn()
    {
        System.out.println("Here is your current hand: " + player);



        String hitOrStand = "";
        while (!(hitOrStand.equals("hit") || hitOrStand.equals("stand")))
        {
            System.out.println("\nDo you want to hit or stand?");
            hitOrStand = input.nextLine();
        }

        if (hitOrStand.equals("hit"))
        {

        }
        else if(hitOrStand.equals("stand"))
        {

        }

        dealerTurn();
    }

    public void dealerTurn()
    {

    }






    // Main method that makes a new Game.java object and calls the playGame() method on it
    public static void main(String[] args)
    {
        Game game = new Game();
        game.playGame();
    }
}
