package Model.updatableunit;

import Model.gamefield.CellPosition;

import java.util.EventObject;

// ивенты(письма)
public class MoveEvent extends EventObject {

    private final CellPosition newPosition;
    private final CellPosition prevPosition;

    public MoveEvent(Object source, CellPosition newPosition, CellPosition prevPosition) {
        super(source);

        this.newPosition = newPosition;
        this.prevPosition = prevPosition;
    }

    public CellPosition getNewPosition() {
        return newPosition;
    }

    public CellPosition getPreviousPosition() {
        return prevPosition;
    }
}
