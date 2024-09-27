package View.Widgets;

import Model.units.Grass;
import View.ImageLoader;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GrassWidget extends UnitWidget {
    public GrassWidget(FieldPanel owner, Grass unit)
    {
        super(unit);
        setLayout(new MigLayout("nogrid"));

        JLabel strengthBuff = new JLabel(Integer.toString(unit.getGrassEffect().getBuffStrength()));
        strengthBuff.setFont(new Font("Arial", Font.BOLD, 13));
        strengthBuff.setForeground(Color.BLACK);
        add(strengthBuff, "pos 0% 75%");

        JLabel strengthBuffDuration = new JLabel(Integer.toString(unit.getGrassEffect().getBuffDuration()));
        strengthBuffDuration.setFont(new Font("Arial", Font.BOLD, 13));
        strengthBuffDuration.setForeground(Color.BLACK);
        add(strengthBuffDuration, "pos 75% 0%");
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
