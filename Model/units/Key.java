package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;

// Ключ
public class Key extends Item {


    @Override
    public String toString() {
        return "K";
    }

    @Override
    public void InteractWith(Unit unit)
    {
        if(unit instanceof Goat) {
            Goat goat = (Goat)unit;

            Cell c = typedOwner();
            goat.addItem((Key)c.extractUnit());
            System.out.println("Keys: " + goat.items());
        }
    }

}
