package Model.units;

import Model.gamefield.Cell;
import Model.gamefield.CellPosition;
import Model.gamefield.Direction;
import Model.ownership.Unit;
import Model.updatableunit.Movable;
import Model.updatableunit.MoveEvent;
import Model.updatableunit.UpdatableUnit;

// Коробка
public class Box extends UpdatableUnit implements Movable {
    // ------------------- Открывание коробки ---------------------------
    public boolean canMoveTo(Cell to) {
        return to.isEmpty();
    }
    @Override
    public void move(Direction direction) {
        Cell pos = typedOwner();
        CellPosition prevPosition = pos.position();

        Cell newPos = pos.neighbour(direction);
        if(newPos == null) {
            return;
        }

        if(!canMoveTo(newPos)) {
            return;
        }

        Unit unit = pos.extractUnit();
        newPos.putUnit(unit);
        System.out.println("box moved");

        fireStateChanged(new MoveEvent(this, newPos.position(), prevPosition));
    }


    public String toString() {
        String msg = "B";
        return msg;
    }

}

