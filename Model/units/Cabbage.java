package Model.units;

import Model.gamefield.Cell;
import Model.gamefield.CellPosition;
import Model.ownership.Unit;
import Model.updatableunit.*;

import java.util.ArrayList;
import java.util.List;

public class Cabbage extends UpdatableUnit implements Interactable {

    private List<CabbageEatenListener> listeners = new ArrayList<>();

    public void addCabbageEatenListener(CabbageEatenListener listener) {
        listeners.add(listener);
    }

    public void removeCabbageEatenListener(CabbageEatenListener listener) {
        listeners.remove(listener);
    }

    public void eatenByGoat(CellPosition pos) {
        CabbageEatenEvent event = new CabbageEatenEvent(this, pos);
        for (CabbageEatenListener listener : listeners) {
            listener.cabbageEaten(event);
        }
    }

    @Override
    public void InteractWith(Unit unit) {
        if (unit instanceof Goat) {
            Cell c = typedOwner();
            c.extractUnit();
            eatenByGoat(c.position());
            System.out.println("cabbage interacted");

        }
    }

    @Override
    public void addListener(StateChangeListener listener) {
        super.addListener(listener);
    }

    @Override
    public String toString() {
        return "C";
    }
}
