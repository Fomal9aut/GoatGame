package View;

import Model.ownership.Unit;
import Model.units.*;
import View.Widgets.*;

import java.awt.*;

public class WidgetFactory {
    public static UnitWidget placeEntityWidget(Unit modelEntity, FieldPanel field) {
        UnitWidget entityWidget;

        if(modelEntity == null)
        {
            return null;
        }

        if (modelEntity instanceof Box) {
            entityWidget = new BoxWidget((Box) modelEntity);
        } else if (modelEntity instanceof Wall) {
            entityWidget = new WallWidget((Wall) modelEntity);
        } else if (modelEntity instanceof Cabbage) {
            entityWidget = new CabbageWidget(field, (Cabbage) modelEntity);
        } else if (modelEntity instanceof Goat) {
            entityWidget = new GoatWidget((Goat) modelEntity);
        } else if (modelEntity instanceof Key) {
            entityWidget = new KeyWidget(field, (Key) modelEntity);
        } else if (modelEntity instanceof Teleporter) {
            entityWidget = new TeleporterWidget((Teleporter) modelEntity);
        }
        else {
            System.out.println(modelEntity.getClass());
            throw new IllegalArgumentException("Неизвестный тип сущности");
        }
        field.cellAt(modelEntity.position()).removeAll();
        field.cellAt(modelEntity.position()).addItem(entityWidget);
        field.cellAt(modelEntity.position()).repaint();
        field.cellAt(modelEntity.position()).revalidate();
        return entityWidget;
    }
}
