package View.Widgets;

import Model.units.Key;
import Model.units.Wheat;
import View.ImageLoader;

import javax.swing.*;
import java.io.IOException;

public class WheatWidget extends UnitWidget {
    public WheatWidget( Wheat unit) {
        super(unit);
    }

    private static final ImageIcon icon;

    static {
        try {
            icon = ImageLoader.loadAsImageIcon("wheat.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageIcon getSourceIcon() {
        return icon;
    }

}