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

    public Card deal(Player playerToAddPoints)
    {
        if (isEmpty())
        {
            return null;
        }

        cardsLeft--;
        if (cards.get(cardsLeft + 1).getRank().equals("Ace"))
        {
            if (playerToAddPoints.getPoints() + 11 > 21)
            {
                playerToAddPoints.addPoints(1);
            }
            else
            {
                playerToAddPoints.addPoints(11);
            }
        }
        else
        {
            playerToAddPoints.addPoints(cards.get(cardsLeft + 1).getPoint());
        }

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

}