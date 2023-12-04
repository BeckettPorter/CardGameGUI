import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    ArrayList<Card> hand;
    int points = 0;
    String name;


    public Player (String name)
    {
        points = 0;
        this.name = name;
    }

    public Player (String name, ArrayList<Card> hand)
    {
        points = 0;
        this.hand = hand;
        this.name = name;
    }

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

    public Card addCard(Card cardToAdd)
    {
        hand.add(cardToAdd);
        return cardToAdd;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Card getSecondCard()
    {
        if (hand.size() >= 2)
        {
            return hand.get(1);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String toString() {
        return name + "'s cards: " + hand.toString() + "\n" +
                name + " has " + points + " points";
    }
}
