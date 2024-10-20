package Model.units;

import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public class BoxWithUnit extends Box implements Interactable {

    private final Unit _unitInside;

    public BoxWithUnit(Unit unitInside) {
        _unitInside = unitInside;
    }

    @Override
    public void InteractWith(Goat goat) {
        if(_unitInside == null) return;

        if(_unitInside instanceof Interactable)
        {
            if(_unitInside instanceof Teleporter)
            {
                ((Teleporter) _unitInside).TeleportNearby(goat, this.typedOwner());
                return;
            }
            ((Interactable)_unitInside).InteractWith(goat);
        }
    }
}
