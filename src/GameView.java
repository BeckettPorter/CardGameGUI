import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    public static final int TITLE_BAR_HEIGHT = 23;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;


    // Final variables for screen spacing of the points counters
    public static final int DEALER_POINTS_X_OFFSET = 180;
    public static final int DEALER_POINTS_Y_OFFSET = 114;
    public static final int PLAYER_POINTS_X_OFFSET = 180;
    public static final int PLAYER_POINTS_Y_OFFSET = 398;



    // Final variables for screen spacing of the cards
    public static final int PLAYER_CARDS_Y_OFFSET = 420;
    public static final int DEALER_CARDS_Y_OFFSET = 135;
    public static final int CARD_WIDTH = 90;
    public static final int CARD_HEIGHT = 125;
    public static final int CARD_GAP = 12;

    // Offsets for the text in the middle of the screen
    public static final int MIDDLE_TEXT_X_OFFSET = -135;
    public static final int MIDDLE_TEXT_Y_OFFSET = 25;


    Font bigFont = new Font("Monaco", Font.BOLD, 30);
    Font smallFont = new Font("Monaco", Font.BOLD, 20);

    private String textToDisplay;

    private boolean showDealerFirstCard;


    private final Image backgroundImage;

    private final Image cardBackImage;


    private Game game;



    private boolean gameOver;



    // Constructor for GameView that sets instance vars and also sets up the window and shows it
    public GameView (Game game)
    {
        this.game = game;

        this.backgroundImage = new ImageIcon("Resources/Blackjack Background.png").getImage();

        this.textToDisplay = "";

        cardBackImage = new ImageIcon("resources/back.png").getImage();

        showDealerFirstCard = false;

        gameOver = false;


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tic Tac Toe");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }


    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
        repaint();
    }

    public void setGame(Game g)
    {
        this.game = g;
    }

    public void setShowDealerFirstCard(boolean showDealerFirstCard) {
        this.showDealerFirstCard = showDealerFirstCard;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }


    // Paint method that draws everything to the screen
    @Override
    public void paint(Graphics g) {

        g.drawImage(backgroundImage,
                0, 0,
                this);

        // Color I made for the text
        Color lightBlue = new Color(90, 130, 220);

        g.setColor(lightBlue);


        g.setFont(bigFont);


        // I have to do this check so it doesn't throw error messages at me :/
        if (!game.getDealer().getHand().isEmpty())
        {
            if (game.getDealer().getHand().size() <= 2 && !gameOver)
            {
                // Draw dealer's points if they have a hidden card
                g.drawString(String.valueOf(game.getDealer().getSecondCard().getPoint()),
                        DEALER_POINTS_X_OFFSET, DEALER_POINTS_Y_OFFSET);
            }
            else
            {
                // Draw dealer's points if they don't have a hidden card
                g.drawString(String.valueOf(game.getDealer().getPoints()),
                        DEALER_POINTS_X_OFFSET, DEALER_POINTS_Y_OFFSET);
            }
        }


        // Draw player's points
        g.drawString(String.valueOf(game.getPlayer().getPoints()),
                PLAYER_POINTS_X_OFFSET, PLAYER_POINTS_Y_OFFSET);


        g.setFont(smallFont);


        // Draws the player's cards and offsets them so they are always centered
        int totalCardsWidth = game.getPlayer().getHand().size() * CARD_WIDTH;
        int totalGapsWidth = (game.getPlayer().getHand().size() - 1) * CARD_GAP;
        int totalFilledUpWidth = totalCardsWidth + totalGapsWidth;

        int startingXPosition = (WINDOW_WIDTH - totalFilledUpWidth) / 2;

        for (int i = 0; i < game.getPlayer().getHand().size(); i++)
        {
            int xPosition = startingXPosition + (i * (CARD_WIDTH + CARD_GAP));
            g.drawImage(game.getPlayer().getHand().get(i).getCardPicture(),
                    xPosition, PLAYER_CARDS_Y_OFFSET,
                    CARD_WIDTH, CARD_HEIGHT,
                    this);
        }

        // Draws the dealer's cards and offsets them, so they are always centered
        totalCardsWidth = game.getDealer().getHand().size() * CARD_WIDTH;
        totalGapsWidth = (game.getDealer().getHand().size() - 1) * CARD_GAP;
        totalFilledUpWidth = totalCardsWidth + totalGapsWidth;

        startingXPosition = (WINDOW_WIDTH - totalFilledUpWidth) / 2;

        for (int i = 0; i < game.getDealer().getHand().size(); i++)
        {
            int xPosition = startingXPosition + (i * (CARD_WIDTH + CARD_GAP));
            if (i == 0 && !showDealerFirstCard)
            {
                g.drawImage(cardBackImage,
                        xPosition, DEALER_CARDS_Y_OFFSET,
                        CARD_WIDTH, CARD_HEIGHT,
                        this);
            }
            else
            {
                g.drawImage(game.getDealer().getHand().get(i).getCardPicture(),
                        xPosition, DEALER_CARDS_Y_OFFSET,
                        CARD_WIDTH, CARD_HEIGHT,
                        this);
            }
        }


        // Draw the string text in the middle of the screen that displays important info
        g.drawString(textToDisplay, WINDOW_WIDTH / 2 + MIDDLE_TEXT_X_OFFSET, WINDOW_HEIGHT / 2 + MIDDLE_TEXT_Y_OFFSET);
    }
}