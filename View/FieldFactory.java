package View;

import Model.gamefield.Pinfold;
import View.Widgets.FieldPanel;

public class FieldFactory {

    public static FieldPanel fromField(Pinfold pinfold)
    {
        FieldPanel field = new FieldPanel();
        field.setModelField(pinfold);
        return field;
    }
}
