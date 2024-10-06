package View.Widgets;

import Model.gamefield.Direction;
import Model.units.Goat;
import View.ImageLoader;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GoatWidget extends UnitWidget {
    private static final ImageIcon icon;
    private JLabel _stepCounter, _goatStrength, _goatBuffDuration;

    static {
        try {
            icon = ImageLoader.loadAsImageIcon("goat.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class GoatKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            int code = e.getKeyCode();

            if(code == KeyEvent.VK_SPACE) {
                getGoat().Interact();
            }

            if (e.isControlDown()) {
                if(code == KeyEvent.VK_UP) {         // перемещаемся вверх
                    getGoat().moveBoxWithStep(Direction.north());
                }
                else if(code == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                    getGoat().moveBoxWithStep(Direction.south());
                }
                else if(code == KeyEvent.VK_LEFT) {  // перемещаемся влево
                    getGoat().moveBoxWithStep(Direction.west());
                }
                else if(code == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                    getGoat().moveBoxWithStep(Direction.east());
                }
            }
            else{
                if(code == KeyEvent.VK_UP) {         // перемещаемся вверх
                    getGoat().move(Direction.north());
                }
                else if(code == KeyEvent.VK_DOWN) {  // перемещаемся вниз
                    getGoat().move(Direction.south());
                }
                else if(code == KeyEvent.VK_LEFT) {  // перемещаемся влево
                    getGoat().move(Direction.west());
                }
                else if(code == KeyEvent.VK_RIGHT) { // перемещаемся вправо
                    getGoat().move(Direction.east());
                }
            }
            FieldPanel field = getCell().getParent();
            field.placeWidgets();
        }
    }

    public GoatWidget(Goat goat) {
        super(goat);

        addKeyListener(new GoatKeyListener());
        setLayout(new MigLayout("nogrid"));

        _stepCounter = new JLabel(Integer.toString(goat.steps()));
        _stepCounter.setFont(new Font("Arial", Font.BOLD, 13));
        _stepCounter.setForeground(Color.BLACK);
        add(_stepCounter, "pos 0% 75%");

        _goatStrength = new JLabel(goat.getEffect() == null ? "0" : Integer.toString(goat.getEffect().getBuffDuration()));
        _goatStrength.setFont(new Font("Arial", Font.BOLD, 13));
        _goatStrength.setForeground(Color.BLACK);
        add(_goatStrength, "pos 75% 0%");

        _goatBuffDuration = new JLabel(Integer.toString(goat.getStrength()));
        _goatBuffDuration.setFont(new Font("Arial", Font.BOLD, 13));
        _goatBuffDuration.setForeground(Color.BLACK);
        add(_goatBuffDuration, "pos 0% 0%");

    }

    Goat getGoat() {
        return (Goat) unit;
    }

    @Override
    public ImageIcon getSourceIcon() {
        return icon;
    }
}
