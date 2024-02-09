import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    public static final int TITLE_BAR_HEIGHT = 23;
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;


    public static final int DEALER_POINTS_X_OFFSET = 180;
    public static final int DEALER_POINTS_Y_OFFSET = 114;
    public static final int PLAYER_POINTS_X_OFFSET = 180;
    public static final int PLAYER_POINTS_Y_OFFSET = 398;


    public static final int PLAYER_CARDS_X_OFFSET = 250;
    public static final int PLAYER_CARDS_Y_OFFSET = 398;
    public static final int CARD_WIDTH = 90;
    public static final int CARD_HEIGHT = 125;


    Font bigFont = new Font("Monaco", Font.BOLD, 30);
    Font smallFont = new Font("Monaco", Font.BOLD, 15);

    private String textToDisplay;


    private Image backgroundImage;



    private Game game;


    public GameView (Game game)
    {
        this.game = game;

        this.backgroundImage = new ImageIcon("Resources/Blackjack Background.png").getImage();

        this.textToDisplay = "";


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




    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(backgroundImage,
                0, 0,
                this);

        Color lightBlue = new Color(90, 130, 220);

        g.setColor(lightBlue);



        g.setFont(bigFont);

        // Draw dealer's points
        g.drawString(String.valueOf(game.getDealer().getPoints()),
                DEALER_POINTS_X_OFFSET, DEALER_POINTS_Y_OFFSET);

        // Draw player's points
        g.drawString(String.valueOf(game.getPlayer().getPoints()),
                PLAYER_POINTS_X_OFFSET, PLAYER_POINTS_Y_OFFSET);


        g.setFont(smallFont);


        for (int i = 0; i < game.getPlayer().getHand().size() - 1; i++)
        {
            g.drawImage(game.getPlayer().getHand().get(i).getCardPicture(),
                    PLAYER_CARDS_X_OFFSET + ((i + 1) * CARD_WIDTH), PLAYER_CARDS_Y_OFFSET,
                    CARD_WIDTH, CARD_HEIGHT,
                    this);

        }

//        for (Card c : game.getPlayer().getHand())
//        {
//            g.drawImage(c.getCardPicture(),
//                    PLAYER_CARDS_X_OFFSET, PLAYER_CARDS_Y_OFFSET,
//                    CARD_WIDTH, CARD_HEIGHT,
//                    this);
//        }

        g.drawString(textToDisplay, WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT / 2);
    }
}
