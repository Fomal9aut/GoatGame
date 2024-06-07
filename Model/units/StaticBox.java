package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public class StaticBox extends AbstractBox implements Interactable {

    public StaticBox(int additionalSteps) {
        this.additionalSteps = additionalSteps;
    }

    private final int additionalSteps;

    public int getAdditionalSteps() { return additionalSteps; }

    @Override
    public void InteractWith(Unit unit) {
        if (unit instanceof Goat) {
            Goat goat = (Goat) unit;

            Cell c = typedOwner();
            goat.AddStaticBox((StaticBox) c.extractUnit());
            System.out.println("boxes in goat " + goat.staticBoxes());
        }
    }
}
