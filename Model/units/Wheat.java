package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public class Wheat extends Unit implements Interactable {
    @Override
    public void InteractWith(Goat goat) {
            goat.IncreaseSteps(5);
            ((Cell) typedOwner()).extractUnit();
        }
    }
