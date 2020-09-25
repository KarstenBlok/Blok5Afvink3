import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;


// meldingen over wie er gewonnen heeft wordt niet in de gui laten zien, maar wordt geprint.
// de X en O zijn erg lelijk maar ik hou daarom van ze :)
// player 1 = X, player 2 = O

public class TicTacToe extends JFrame {
    JPanel panel = new JPanel();
    PlayerButton buttons[] = new PlayerButton[9];
    boolean turnX = true;
    int gelijk = 0;
    PlayerButton[][] buttonBeter;

    public static void main(String[] args) throws IOException {
        TicTacToe GUI = new TicTacToe();
    }

    public TicTacToe() throws IOException {
        super("TicTacToe");
        PlayerButton Weggooibutton;
        setSize(1000, 1000);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        buttonBeter = new PlayerButton[3][3];
        panel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < buttonBeter.length; i++){
            for (int y = 0; y < buttonBeter[i].length; y++){
                Weggooibutton = new PlayerButton();
                buttonBeter[i][y] = Weggooibutton;
                panel.add(Weggooibutton);
            }
        }
        add(panel);
        setVisible(true);
        System.out.println(Arrays.deepToString(buttonBeter));
    }


    public void disable_buttons() {
        for (PlayerButton[] playerButtons : buttonBeter) {
            for (int y = 0; y < playerButtons.length; y++) {
                    playerButtons[y].setEnabled(false);

            }
        }
    }

    public void gewonnen() {
        int vergelijkingsint;
        if (turnX){
            vergelijkingsint = 1;
        } else {
            vergelijkingsint = 2;
        }
        for (int i = 0; i < 3; i++){
            if (buttonBeter[i][0].player == vergelijkingsint
                && buttonBeter[i][1].player == vergelijkingsint
                && buttonBeter[i][2].player == vergelijkingsint) {
                System.out.println("player "+vergelijkingsint+" heeft gewonnen");
                disable_buttons();
            } else if (buttonBeter[0][i].player == vergelijkingsint
                    && buttonBeter[1][i].player == vergelijkingsint
                    && buttonBeter[2][i].player == vergelijkingsint) {
                System.out.println("player "+vergelijkingsint+" heeft gewonnen");
                disable_buttons();
            }
        }
        if (buttonBeter[0][0].player == vergelijkingsint
                && buttonBeter[1][1].player == vergelijkingsint
                && buttonBeter[2][2].player == vergelijkingsint) {
                System.out.println("player "+vergelijkingsint+" heeft gewonnen");
                disable_buttons();
        } else if (buttonBeter[2][0].player == vergelijkingsint
                && buttonBeter[1][1].player == vergelijkingsint
                && buttonBeter[0][2].player == vergelijkingsint){
            System.out.println("player "+vergelijkingsint+" heeft gewonnen");
            disable_buttons();
        }
    }


    public class PlayerButton extends JButton implements ActionListener {
        Image X;
        Image O;
        int player = 0;
        public PlayerButton() throws IOException {
            X = ImageIO.read(getClass().getResource("X2.png"));
            O = ImageIO.read(getClass().getResource("O2.png"));

            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (turnX) {
                setIcon(new ImageIcon(X));
                player = 1;
            } else {
                setIcon(new ImageIcon(O));
                player = 2;

            }
            gelijk=gelijk+1;
            if (gelijk == 9) {
                System.out.println(("gelijkspel"));
            }
            gewonnen();
            turnX = !turnX;
            setEnabled(false);
        }
    }
}