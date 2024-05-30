package View;

import Model.gamefield.GameField;
import View.Widgets.FieldPanel;

public class FieldFactory {

    public static FieldPanel fromField(GameField gameField)
    {
        FieldPanel field = new FieldPanel();
        field.setModelField(gameField);
        return field;
    }
}
