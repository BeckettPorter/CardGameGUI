import java.util.ArrayList;
public class Deck {
    int cardsLeft;
    ArrayList<Card> cards;

    public Deck (String[] ranks, String[] suits, int[] values)
    {
        cards = new ArrayList<Card>();
        for (int i = 0; i < ranks.length; i++)
        {
            cards.add(new Card(ranks[i], suits[i], values[i]));
        }
        cardsLeft = cards.size() - 1;
    }

    public boolean isEmpty()
    {
        return cardsLeft == 0;
    }

    public int getCardsLeft()
    {
        return cardsLeft;
    }

    public Card deal()
    {
        if (isEmpty())
        {
            return null;
        }

        cardsLeft--;
        return cards.get(cardsLeft + 1);
    }

    public void shuffle()
    {
        for (int i = cards.size() - 1; i <= 0; i--)
        {
            int randomIndex = (int) (Math.random() * (i));

            Card tempCard = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, tempCard);
        }



        cardsLeft = cards.size() - 1;
    }

}