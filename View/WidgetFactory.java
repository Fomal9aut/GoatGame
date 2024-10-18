package View;

import Model.ownership.Unit;
import Model.units.*;
import View.Widgets.*;

public class WidgetFactory {
    public static UnitWidget placeUnitWidget(Unit modelUnit, FieldPanel field) {
        UnitWidget entityWidget;


        if(modelUnit == null)
        {
            return null;
        }

        if (modelUnit instanceof BoxWithUnit) {
            entityWidget = new BoxWidget((Box) modelUnit);
        } else if (modelUnit instanceof Box) {
            entityWidget = new BoxWidget((Box) modelUnit);
        } else if (modelUnit instanceof Wall) {
            entityWidget = new WallWidget((Wall) modelUnit);
        } else if (modelUnit instanceof Cabbage) {
            entityWidget = new CabbageWidget(field, (Cabbage) modelUnit);
        } else if (modelUnit instanceof Goat) {
            entityWidget = new GoatWidget((Goat) modelUnit);
        } else if (modelUnit instanceof Key) {
            entityWidget = new KeyWidget(field, (Key) modelUnit);
        } else if (modelUnit instanceof Teleporter) {
            entityWidget = new TeleporterWidget((Teleporter) modelUnit);
        }
        else {
            System.out.println(modelUnit.getClass());
            throw new IllegalArgumentException("Неизвестный тип сущности");
        }
        field.cellAt(modelUnit.position()).removeAll();
        field.cellAt(modelUnit.position()).addItem(entityWidget);
        field.cellAt(modelUnit.position()).repaint();
        field.cellAt(modelUnit.position()).revalidate();
        return entityWidget;
    }
}
