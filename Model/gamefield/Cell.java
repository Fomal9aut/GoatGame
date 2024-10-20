package Model.gamefield;

import Model.ownership.CanOwnUnit;
import Model.ownership.OwnershipProperty;
import Model.ownership.Unit;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Ячейка поля
// Может хранить в себе юнит(ТОЛЬКО ОДИН) и извлекать его из себя
public class Cell implements CanOwnUnit {


    private final CellPosition _pos;

    public CellPosition position() {
        return _pos;
    }


    public Cell(CellPosition position) {
        _pos = position;
    }

    private final Map<Direction, Cell> _neighbors = new HashMap<>();
    
    public Cell neighbour(Direction direct) {

        if(_neighbors.containsKey(direct)) {
            return _neighbors.get(direct);
        }        
        
        return null;
    }

    public Map<Direction, Cell> neighbors() {
        return Collections.unmodifiableMap(_neighbors);
    }
    
    void setNeighbor(Direction direct, Cell neighbor) {
        if(neighbor != this && !isNeighbor(neighbor)) {
            _neighbors.put(direct, neighbor);
            neighbor.setNeighbor(direct.opposite(), this);
        }
    }
    
    public boolean isNeighbor(Cell other) {
        return _neighbors.containsValue(other);
    }

    private final OwnershipProperty _ownership = new OwnershipProperty(this);

    public Unit getUnit() {
        return _ownership.getUnit();
    }
    
    public boolean isEmpty() {
        return _ownership.isEmpty();
    }
    
    public boolean putUnit(Unit unit) { return _ownership.putUnit(unit);  }
    
    public Unit extractUnit() { return _ownership.extractUnit(); }
}
