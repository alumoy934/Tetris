import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{

    public static final int WIDTH = 300, HEIGHT = 600;
    public Window(){
        JFrame window = new JFrame("Tetris");
        setSize(WIDTH, HEIGHT);
        setTitle("Tetris_Cutre_Game");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Board board = new Board();
        add(board);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }

}
