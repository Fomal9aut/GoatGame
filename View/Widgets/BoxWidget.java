package View.Widgets;

import Model.updatableunit.MoveEvent;
import Model.updatableunit.StateChangeListener;
import View.ImageLoader;

import Model.units.Box;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.EventObject;

public class BoxWidget extends UnitWidget {
    private static final ImageIcon icon;

    static {
        try{
            icon = ImageLoader.loadAsImageIcon("box.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BoxWidget(Box entity) {
        super(entity);
        setLayout(new MigLayout("nogrid"));
         = new JLabel(Integer.toString(goat.steps()));
        _stepCounter.setFont(new Font("Arial", Font.BOLD, 13));
        _stepCounter.setForeground(Color.BLACK);
        add(_stepCounter, "pos 0% 75%");

    }

    @Override
    public ImageIcon getSourceIcon() {
        return icon;
    }
}
