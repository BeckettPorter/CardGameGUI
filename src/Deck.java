import java.util.ArrayList;
public class Deck {

    // Instance variables cardsLeft and the arrayList of the cards in the deck.
    private int cardsLeft;
    private ArrayList<Card> cards;

    // Constructor for the Deck.java class, it takes in the necessary values for the deck (ranks, suits, and values)
    // and then creates the deck using a nested for loop.
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

        // Sets cardsLeft to the size of the deck - 1 to account for it starting at 0.
        cardsLeft = cards.size() - 1;
    }

    // isEmpty method to check if the deck is empty.
    public boolean isEmpty()
    {
        return cardsLeft == 0;
    }

    // getCardsLeft method that I didn't use but was in the instructions, so I kept it in.
    public int getCardsLeft()
    {
        return cardsLeft;
    }

    // This deals a card and adds the point value to the player who it is getting dealt to. I also modified it
    // to take into account aces being either 1 or 11 in blackjack depending on if it will make the score over 21.
    public Card deal(Player playerToAddPoints)
    {
        if (isEmpty())
        {
            return null;
        }

        cardsLeft--;

        // Check for if the card is an ace, and then either adds 1 or 11 points depending on if it'll go over 21.
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

    //Shuffles the deck using the algorithm from the canvas assignment.
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