import java.util.ArrayList;

public class Player {

    // Instance variables for the player's hand, points, and name
    private ArrayList<Card> hand;
    private int points = 0;
    private String name;

    // The first constructor for player, just takes in a name and also sets points to 0. I didn't use this, but it said I
    // should have it in the instructions, so I kept it in.
    public Player (String name)
    {
        points = 0;
        this.name = name;
    }

    // Here is the second constructor for player that takes in a name and also a hand arrayList of cards. It also
    // sets points to 0.
    public Player (String name, ArrayList<Card> hand)
    {
        points = 0;
        this.hand = hand;
        this.name = name;
    }

    // Getter and setter methods for player class.
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public int getPoints()
    {
        return points;
    }

    public String getName()
    {
        return name;
    }

    public void addPoints(int pointsToAdd)
    {
        points += pointsToAdd;
    }

    public void setPoints(int newPoints)
    {
        this.points = newPoints;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    // addCard method, I modified it a bit to also return the card it added because it made some of my code in
    // Game.java more clean.
    public Card addCard(Card cardToAdd)
    {
        hand.add(cardToAdd);
        System.out.println(hand);
        return cardToAdd;
    }

    // getSecondCard method, this is a method that just returns the second card in the hand, it's used for
    // initially showing the dealer's second card but not their first at the start of the game.
    public Card getSecondCard()
    {
        if (hand.size() >= 2)
        {
            return hand.get(1);
        }
        else
        {
            // Returns null if the hand doesn't have 2 cards
            return null;
        }
    }

    // toString method that prints the necessary information about the player and their hand.
    @Override
    public String toString() {
        return name + "'s cards: " + hand.toString() + "\n" +
                name + " has " + points + " points";
    }
}
