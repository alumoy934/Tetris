import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame{

    public static JLabel statusbar;

    public static final int WIDTH = 300, HEIGHT = 600;

    public static int totalScore = 0;

    public Tetris(){
        // barra de estado
        statusbar = new JLabel("Score: 0");
        add(statusbar, BorderLayout.SOUTH);


        // tablero de juego
        Board board = new Board();
        add(board);
       // board.start(); // este metodo arranca el juego
        setSize(300, 600);
        setResizable(false);
        setTitle("Tetris_Cutre_Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JLabel getStatusBar() {
        return statusbar;
    }

    public static void main(String[] args) {

        Tetris game = new Tetris();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    }

}
