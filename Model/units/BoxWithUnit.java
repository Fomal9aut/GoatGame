package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;
// наследник коробки, который может внутри себя хранить юнит
public class BoxWithUnit extends Box implements Interactable {
    // хранящайся юнит
    private Unit _unitInside;

    public BoxWithUnit(Unit unitInside) {
        if(unitInside != null)
            _unitInside = unitInside;
    }

    /*
     при взаимодействии или разрушается, выкладывая вместо себя юнит,
     или применяет действие юнита внутри себя
    */
    @Override
    public void InteractWith(Goat goat) {
        // если с юнитом внутри можно взаимодейстовать - взаимодействуем
        if(_unitInside instanceof Interactable)
        {
            // если юнит внутри коробки это телепорт - телепортим козу
            if(_unitInside instanceof Teleporter)
            {
                ((Teleporter) _unitInside).TeleportNearby(goat, this.typedOwner());
                return;
            }
        }
        //иначе уничтожаем коробку и на ее место ставим юнит
        Cell c = this.typedOwner();

        c.extractUnit();
        c.putUnit(_unitInside);
        _unitInside = null;
    }
}
