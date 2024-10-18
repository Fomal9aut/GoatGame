package Model.units;

import Model.gamefield.CellPosition;
import Model.gamefield.Direction;
import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;
import Model.updatableunit.Movable;
import Model.updatableunit.MoveEvent;
import Model.updatableunit.UpdatableUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Коза
public class Goat extends UpdatableUnit implements Movable {

    public Goat() {

    }

    public Goat(int steps){
        setSteps(steps);
    }

    private int _steps = 25;

    public void setSteps(int steps) {
        this._steps = steps;
    }

    private static final int REQUIRED_STEPS_FOR_MOVE = 1;

    ItemContainer _items = new ItemContainer();

    public void addItem(Item i) {
        _items.addItem(i);
    }

    public <T> T getItem(Class<T> clazz)
    {
       return _items.getItem(clazz);
    }

    public int items() { return _items._items.size(); }

    public int steps() {
        return _steps;
    }
    
    public boolean isAvailableSteps(int stepsValue) {
        return stepsValue <= _steps;
    }
    
    protected int reduceSteps(int stepsValue) {
        int retrievedSteps = Math.min(_steps, stepsValue);
        _steps -= retrievedSteps;
        return retrievedSteps;
    }

    public boolean hasSteps() { return _steps >= REQUIRED_STEPS_FOR_MOVE;}

    // --------------------------- Перемещение ------------------------------------
    public boolean canMoveTo(Cell to) {
        return to.isEmpty();
    }

    @Override
    public void move(Direction direct) {

        Cell pos = typedOwner();
        CellPosition prevPosition = pos.position();

        if(!isAvailableSteps(REQUIRED_STEPS_FOR_MOVE)) {
            return;
        }

        Cell newPos = pos.neighbour(direct);
        if(newPos == null) {
            return;
        }

        if(!canMoveTo(newPos)) {
            return;
        }

        Unit unit = pos.extractUnit();
        newPos.putUnit(unit);
        reduceSteps(REQUIRED_STEPS_FOR_MOVE);


        fireStateChanged(new MoveEvent(this, newPos.position(), prevPosition));
    }

    public void MoveBoxWithStep(Direction direction)
    {
        Cell pos = typedOwner();
        Box boxForward = null, boxBehind = null;

        if(pos.neighbour(direction.opposite()) != null && pos.neighbour(direction.opposite()).getUnit() instanceof Box)
        {
            boxBehind = (Box) pos.neighbour(direction.opposite()).getUnit();
            System.out.println("boxBehind: " + boxBehind);
        }

        if(boxBehind != null)
        {
            move(direction);
            boxBehind.move(direction);
        }

        if(pos.neighbour(direction) != null && pos.neighbour(direction).getUnit() instanceof Box)
        {
            boxForward = (Box) pos.neighbour(direction).getUnit();
            System.out.println("boxForward: " + boxForward);
        }

        if(boxForward != null) {
            boxForward.move(direction);
            move(direction);
        }

    }

    public void Interact()
    {
        Cell c = typedOwner();
        Map<Direction, Cell> neighbours = c.neighbors();

        for (Map.Entry<Direction, Cell> entry : neighbours.entrySet()) {
            Cell cell = entry.getValue();

            System.out.println(cell.position().column() + " " + cell.position().row() + " " + cell.getUnit());
            if(cell.getUnit() instanceof Item) {
                ((Item) cell.getUnit()).InteractWithGoat(this);
                return;
            }

            if(cell.getUnit() instanceof Interactable) {
                ((Interactable) cell.getUnit()).InteractWithGoat(this);
                return;
            }
        }
    }

    class ItemContainer {
        private List<Item> _items;

        public ItemContainer(List<Item> items) {
            _items = items != null ? items : new ArrayList<>(); // Инициализация списка, если он не передан
        }

        public ItemContainer() {
            _items = new ArrayList<>();
        }

        public <T> T getItem(Class<T> clazz) {
            for (Item item : _items) {
                if (clazz.isInstance(item)) {

                    T i = clazz.cast(item);
                    _items.remove(item);

                    return i;
                }
            }
            return null; // Возвращает null, если ничего не найдено
        }

        public void addItem(Item item) {
            if (item != null) {
                _items.add(item);
            }
        }
    }


    @Override
    public String toString() {

        String msg;
        msg = "G(" + steps() + ")";

        return msg;
    }
}
