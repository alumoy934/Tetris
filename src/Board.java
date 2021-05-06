import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Board extends JPanel implements ActionListener {

    public static final int NUM_COLS = 10, NUM_ROWS = 22;
    private Shape.Tetrominoes [][] squares;

    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    int numLinesRemoved = 0;
    JLabel statusbar;
    Shape curPiece;
    int currentX = NUM_COLS / 2;
    int currentY= 1;

    private int scoreTotal = 0;

    Shape.Tetrominoes tetrominoes;


    public Board(){
        super();
        setFocusable(true);
        curPiece = new Shape();
        timer = new Timer(400, this);
        timer.start();
        squares = new Shape.Tetrominoes[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                squares[i][j] = Shape.Tetrominoes.NoShape;
            }
        }

        TAdapter keyAdapter = new TAdapter();
        addKeyListener(keyAdapter);


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawSquares(g);
        drawCurrentShape(g);

    }

    private void drawSquares(Graphics g) {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
               drawSquare(g, i, j,squares[i][j]);
            }
        }
    }

    private void drawCurrentShape(Graphics g) {
        for (int i = 0; i < 4; i++) {
            int row = currentY + curPiece.getY(i);
            int col = currentX + curPiece.getX(i);
            drawSquare(g,row, col, curPiece.getShape());

        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (canMove(curPiece, currentY +1, currentX)) {
            currentY++;

        }else{
            moveCurpiceToSquares();
            chekLines();
            if(checkForGameOver()){
                JOptionPane.showMessageDialog(null, "Eres malo. Aprende de Juan Guerra.",
                        "GAME OVER", JOptionPane.WARNING_MESSAGE);
            }

            curPiece = new Shape();
            currentY = 0;
            currentX = NUM_COLS / 2;
        }

        repaint();
    }



    private boolean checkForGameOver() {
        boolean gameOver = false;
        for (int i = 0; i < NUM_COLS; i++) {
            if (squares[0][i] != Shape.Tetrominoes.NoShape) {
                gameOver = true;
            }
        }
        return gameOver;
    }

    private void moveCurpiceToSquares() {
        for (int i = 0; i < 4; i++) {
            int row = currentY + curPiece.getY(i);
            int col = currentX + curPiece.getX(i);
            squares[row][col] = curPiece.getShape();
        }
    }

    public boolean canMove(Shape s, int y, int x){
        if (y + s.maxY() >= NUM_ROWS){
            return false;
        }
        if (x + s.minX() < 0 || x + s.maxX() >= NUM_COLS){
            return false;
        }

        //comprobar colisión con squares
        for (int i = 0; i < 4; i++) {
              if (squares[y + s.getY(i)][x + s.getX(i)] != Shape.Tetrominoes.NoShape){
                  return false;
              }
        }
        return true;
    }

    public void chekLines (){
        boolean completedLine;

        int linescore = 10;

        int i = NUM_ROWS -1;
        while (i >= 0){
            completedLine = true;
            for (int j = 0; j < NUM_COLS; j++) {
                if ( squares[i][j] == Shape.Tetrominoes.NoShape){
                    completedLine = false;
                    break;
                }
            }
            if (completedLine){
                deleteline(i);
                scoreTotal += linescore;
                Tetris.statusbar.setText("Score: " + scoreTotal);
            }else{
                i--;
            }
        }

    }

    private void deleteline(int line) {
        for (int i = line; i >=1 ; i--) {
            for (int j = 0; j < NUM_COLS; j++) {
                squares[i][j] = squares[i-1][j];
            }
        }

        for (int j = 0; j < NUM_COLS; j++) {
            squares[0][j] = Shape.Tetrominoes.NoShape;
        }
    }


    class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    if (canMove(curPiece, currentY,currentX - 1)){
                        currentX --;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (canMove(curPiece, currentY,currentX + 1)){
                        currentX ++;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (canMove(curPiece, currentY+1,currentX)){
                        currentY ++;
                    }
                    break;
                case KeyEvent.VK_UP:
                    Shape rotated = curPiece.rotateRight();
                    if (canMove(rotated, currentY,currentX)){
                        curPiece = rotated;
                    }
                    break;
            }
            repaint();
        }
    }

}
