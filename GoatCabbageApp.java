import View.GameFrame;

import javax.swing.*;



public class GoatCabbageApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame frame = new GameFrame();
                frame.setVisible(true);
            }
        });
    }
}