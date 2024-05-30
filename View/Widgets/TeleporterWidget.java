package View.Widgets;

import Model.ownership.Unit;
import View.ImageLoader;

import javax.swing.*;
import java.io.IOException;

public class TeleporterWidget extends UnitWidget{
    public TeleporterWidget(Unit unit) {
        super(unit);
    }

    private static final ImageIcon icon;

    static {
        try {
            icon = ImageLoader.loadAsImageIcon("teleport.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageIcon getSourceIcon() {
        return icon;
    }
}
