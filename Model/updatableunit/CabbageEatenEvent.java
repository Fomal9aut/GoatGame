package Model.updatableunit;

import Model.gamefield.CellPosition;

import java.util.EventObject;
// ивенты(письма)
public class CabbageEatenEvent extends EventObject {
    private final CellPosition cabbagePos;

    public CabbageEatenEvent(Object source, CellPosition cabbagePos) {
        super(source);
        this.cabbagePos = cabbagePos;
    }

    public CellPosition getCabbagePosition() {return cabbagePos;}
}