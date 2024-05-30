package View.Widgets;

import Model.units.Wall;
import View.ImageLoader;

import javax.swing.*;
import java.io.IOException;

public class WallWidget extends UnitWidget {
    public WallWidget(Wall entity) {
        super(entity);
    }

    private static final ImageIcon icon;

    static {
        try {
            icon = ImageLoader.loadAsImageIcon("fence.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ImageIcon getSourceIcon() {
        return icon;
    }

}
