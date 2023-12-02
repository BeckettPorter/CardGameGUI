import java.util.ArrayList;
public class Deck {
    int cardsLeft;
    ArrayList<Card> cards;

    public Deck (String[] ranks, String[] suits, int[] values)
    {
        cards = new ArrayList<Card>();
        for (int i = 0; i < suits.length - 1; i++)
        {
            for (int j = 0; j < ranks.length; j++)
            {
                cards.add(new Card(ranks[j], suits[i], values[j]));
            }
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
        for (int i = cards.size() - 1; i > 0; i--)
        {
            int randomIndex = (int) (Math.random() * (i));

            Card tempCard = cards.get(i);
            cards.set(i, cards.get(randomIndex));
            cards.set(randomIndex, tempCard);
        }

        cardsLeft = cards.size() - 1;
    }

    //for testing
//    public void printDeck()
//    {
//        for (Card card: cards)
//        {
//            System.out.println(card);
//        }
//    }

}