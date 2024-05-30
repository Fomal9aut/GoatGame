package View.Widgets;

import Model.units.Cabbage;
import Model.units.Key;
import Model.units.Wall;
import Model.updatableunit.CabbageEatenEvent;
import Model.updatableunit.CabbageEatenListener;
import Model.updatableunit.MoveEvent;
import Model.updatableunit.StateChangeListener;
import View.ImageLoader;

import javax.swing.*;
import java.io.IOException;
import java.util.EventObject;

public class KeyWidget extends UnitWidget {
    public KeyWidget(FieldPanel owner, Key unit) {
        super(unit);
    }

    private static final ImageIcon icon;

    static {
        try {
            icon = ImageLoader.loadAsImageIcon("key.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageIcon getSourceIcon() {
        return icon;
    }

}
