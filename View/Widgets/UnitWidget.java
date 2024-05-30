package View.Widgets;

import Model.ownership.Unit;

import javax.swing.*;

public class UnitWidget extends CellItemWidget{

    protected Unit unit;

    public UnitWidget(Unit unit) {
        super();
        this.unit = unit;
    }

    public Unit getModelUnit() {
        return unit;
    }

    @Override
    public ImageIcon getSourceIcon() {
        return null;
    }
}
