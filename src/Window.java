import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{


    public static final int WIDTH = 300, HEIGHT = 600;

    public Window(){
        JLabel statusbar = new JLabel("0");
        add(statusbar, BorderLayout.SOUTH);

        Board board = new Board();
        add(board);

        setSize(WIDTH, HEIGHT);
        setTitle("Tetris_Cutre_Game");
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        Window game = new Window();
    }

}
