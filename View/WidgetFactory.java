package View;

import Model.ownership.Unit;
import Model.units.*;
import View.Widgets.*;

public class WidgetFactory {
    public static UnitWidget placeUnitWidget(Unit modelUnit, FieldPanel field) {
        UnitWidget unitWidget;


        if(modelUnit == null)
        {
            return null;
        }

        if (modelUnit instanceof Box) {
            unitWidget = new BoxWidget((Box) modelUnit);
        } else if (modelUnit instanceof Wall) {
            unitWidget = new WallWidget((Wall) modelUnit);
        } else if (modelUnit instanceof Cabbage) {
            unitWidget = new CabbageWidget(field, (Cabbage) modelUnit);
        } else if (modelUnit instanceof Goat) {
            unitWidget = new GoatWidget((Goat) modelUnit);
        } else if (modelUnit instanceof Key) {
            unitWidget = new KeyWidget(field, (Key) modelUnit);
        } else if (modelUnit instanceof Teleporter) {
            unitWidget = new TeleporterWidget((Teleporter) modelUnit);
        } else if (modelUnit instanceof StaticBox) {
            unitWidget = new StaticBoxWidget((StaticBox) modelUnit);
        } else if (modelUnit instanceof StaticBoxShredder) {
            unitWidget = new StaticBoxShredderWidget((StaticBoxShredder) modelUnit);
        }
        else {
            System.out.println(modelUnit.getClass());
            throw new IllegalArgumentException("Неизвестный тип сущности");
        }
        field.cellAt(modelUnit.position()).removeAll();
        field.cellAt(modelUnit.position()).addItem(unitWidget);
        field.cellAt(modelUnit.position()).repaint();
        field.cellAt(modelUnit.position()).revalidate();
        return unitWidget;
    }
}
