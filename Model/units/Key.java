package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;
import Model.updatableunit.UpdatableUnit;

import java.util.EventObject;

// Ключ
public class Key extends Unit implements Interactable {

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
            goat.AddKey((Key)c.extractUnit());
            System.out.println("Keys: " + goat.Keys());
        }
    }

}
