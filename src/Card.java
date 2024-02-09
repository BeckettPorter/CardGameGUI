import java.awt.*;

public class Card {

    // Instance variables rank, suit, and point for the Card class
    private String rank;
    private String suit;
    private int point;


    private Image cardPicture;

    // Card constructor that takes in the rank, suit, and point values.
    public Card(String rank, String suit, int point, Image cardPicture)
    {
        this.suit = suit;
        this.rank = rank;
        this.point = point;
        this.cardPicture = cardPicture;
    }

    // Getter and setter methods for instance variables.
    public String getRank()
    {
        return rank;
    }

    public String getSuit()
    {
        return suit;
    }

    public int getPoint()
    {
        return point;
    }

    public Image getCardPicture() {
        return cardPicture;
    }

    public void setRank(String rank)
    {
        this.rank = rank;
    }

    public void setSuit(String suit)
    {
        this.suit = suit;
    }

    public void setPoint(int point)
    {
        this.point = point;
    }

    // toString method that returns the rank and suit of the card object.
    @Override
    public String toString()
    {
        return this.rank + " of " + this.suit;
    }
}
