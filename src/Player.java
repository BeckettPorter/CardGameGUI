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

    public void addPoints(int pointsToAdd)
    {
        points += pointsToAdd;
    }

    public void addCard(Card cardToAdd)
    {
        hand.add(cardToAdd);
    }

    @Override
    public String toString() {
        return name + " has " + points + " points\n"
        + name + "'s cards: " + hand.toString();
    }
}
