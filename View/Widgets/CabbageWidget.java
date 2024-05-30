package View.Widgets;

import Model.ownership.Unit;
import Model.units.Cabbage;
import Model.updatableunit.CabbageEatenEvent;
import Model.updatableunit.CabbageEatenListener;
import View.ImageLoader;

import javax.swing.*;
import java.io.IOException;

public class CabbageWidget extends UnitWidget {
    private static final ImageIcon icon;

    static {
        try {
            icon = ImageLoader.loadAsImageIcon("cabbage.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CabbageWidget(FieldPanel owner, Cabbage unit) {
        super(unit);
        unit.addCabbageEatenListener(new CabbageEatenListener() {
            @Override
            public void cabbageEaten(CabbageEatenEvent event) {
                owner.cellAt(event.getCabbagePosition()).removeItem(CabbageWidget.this);
                owner.cellAt(event.getCabbagePosition()).repaint();
            }
        });
    }

    @Override
    public ImageIcon getSourceIcon() {
        return icon;
    }
}
