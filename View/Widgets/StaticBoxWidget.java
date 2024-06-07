package View.Widgets;

import Model.ownership.Unit;
import View.ImageLoader;

import javax.swing.*;
import java.io.IOException;

public class StaticBoxWidget extends UnitWidget{
    public StaticBoxWidget(Unit unit) {
        super(unit);
    }

    private static final ImageIcon icon;

    static {
        try {
            icon = ImageLoader.loadAsImageIcon("box2.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageIcon getSourceIcon() {
        return icon;
    }

}
