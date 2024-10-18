package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public class BoxWithUnit extends Box implements Interactable {

    private Unit _unit;

    public BoxWithUnit(Unit unit)
    {
        _unit = unit;
    }

    public Unit GetUnit()
    {
        return _unit;
    }

    @Override
    public void InteractWith(Unit unit) {
        if(unit instanceof Goat && ((Goat)unit).items() > 0) {
            Key key = ((Goat)unit).getItem(Key.class);

            if(key != null) {
                Cell c = typedOwner();
                c.extractUnit();
                c.putUnit(_unit);

                System.out.println("Keys remain: " + ((Goat) unit).items());
            } else {
                System.out.println("No keys!");
            }
        }
    }
}
