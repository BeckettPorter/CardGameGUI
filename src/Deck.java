import java.util.ArrayList;
public class Deck {
    int size;
    Arraylist<Card> cardDeck;

    public Deck (int size)
    {
        this.size = size;
        cardDeck = new Arraylist<Card>();
    }

    public void drawDeck()
    {
        for (int i = 0; i < size; i++)
        {
            cardDeck.add(new Card());
        }
    }

}
