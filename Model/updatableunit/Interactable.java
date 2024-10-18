package Model.updatableunit;

import Model.ownership.Unit;
import Model.units.Goat;

public interface Interactable {
    void InteractWith(Unit unit);
    void InteractWithGoat(Goat goat);
}
