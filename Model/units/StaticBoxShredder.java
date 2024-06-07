package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public class StaticBoxShredder extends Unit implements Interactable {
    @Override
    public void InteractWith(Unit unit) {
        if (unit instanceof Goat) {
            Goat goat = (Goat) unit;

            if(goat.staticBoxes() > 0)
            {
                while(goat.staticBoxes() != 0)
                {
                   int additionalSteps = goat.PopStaticBox().getAdditionalSteps();
                    System.out.println("steps added " + additionalSteps);
                   goat.addSteps(additionalSteps);
                }
                System.out.println("boxes in goat " + goat.staticBoxes());
            }
        }
    }

}
