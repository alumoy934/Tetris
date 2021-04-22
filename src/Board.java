import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    public static final int NUM_COLS = 10, NUM_ROWS = 22;
    public Board(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawSquare(g,5,9, Shape.Tetrominoes.LShape);
    }

    public int getSquareWidth(){
        return getWidth() / NUM_COLS;
    }

    public int getSquareHeight(){
        return getHeight() / NUM_ROWS;
    }

    private void drawSquare(Graphics g, int row, int col, Shape.Tetrominoes shape) {
        Color colors[] = { new Color(0, 0, 0),
                new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };
        int x = col * getSquareWidth();
        int y = row * getSquareHeight();
        Color color = colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x + 1, y + 1, getSquareWidth() - 2,
                getSquareHeight() - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + getSquareHeight() - 1, x, y);
        g.drawLine(x, y, x + getSquareWidth() - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + getSquareHeight() - 1,
                x + getSquareWidth() - 1, y + getSquareHeight() - 1);
        g.drawLine(x + getSquareWidth() - 1,
                y + getSquareHeight() - 1,
                x + getSquareWidth() - 1, y + 1);
    }
}
