import javax.swing.*;

public class ScoreBoard extends JPanel implements Incrementer{

    private int score;
    private JLabel label;

    private JLabel labelThread;

    public ScoreBoard (){
        score = 0;
        label = new JLabel();
        increment(0);

        labelThread = new JLabel();
        add(label);
        add(labelThread);

        Thread t = new Thread(new Runnable() {


            int i = 0;
            @Override
            public void run() {

                while (true){
                    try {
                        Thread.sleep(400);
                        labelThread.setText("labelthread: "+ i++);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t.start();
    }

    public void increment (int inc){
        score += inc;
        label.setText("Score: " + score);
    }
}
