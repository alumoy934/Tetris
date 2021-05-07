import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame{


    public static final int WIDTH = 300, HEIGHT = 600;

    private ScoreBoard scoreBoard;

    public Tetris(){
        // barra de estado
        scoreBoard = new ScoreBoard();
        add(scoreBoard, BorderLayout.SOUTH);


        // tablero de juego
        Board board = new Board(scoreBoard);
        add(board);
       // board.start(); // este metodo arranca el juego
        setSize(300, 600);
        setResizable(false);
        setTitle("Tetris_Cutre_Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    public static void main(String[] args) {

        Tetris game = new Tetris();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    }

}
