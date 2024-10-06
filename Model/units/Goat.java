package Model.units;

import Model.gamefield.CellPosition;
import Model.gamefield.Direction;
import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.units.effects.Effect;
import Model.updatableunit.Interactable;
import Model.updatableunit.Movable;
import Model.updatableunit.MoveEvent;
import Model.updatableunit.UpdatableUnit;

import java.util.*;

// Коза
public class Goat extends UpdatableUnit implements Movable {

    public Goat() {

    }

    public Goat(int steps) {
        setSteps(steps);
    }

    private static final int DEFAULT_STRENGTH = 1;

    private int _steps = 25;
    private int _strength = DEFAULT_STRENGTH;

    private Effect effect;

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect e)
    {
        this.effect = e;
        effect.Apply(this);
    }

    public void removeEffect()
    {
        effect = null;
        setStrength(1);
    }

    public int getStrength() {
        return _strength;
    }

    public void setStrength(int _strength) {
        this._strength = _strength;
    }

    public void setSteps(int steps) {
        this._steps = steps;
    }

    private static final int REQUIRED_STEPS_FOR_MOVE = 1;


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

    public boolean hasSteps() {
        return _steps >= REQUIRED_STEPS_FOR_MOVE;
    }

    // --------------------------- Перемещение ------------------------------------
    public boolean canMoveTo(Cell to) {
        return to.isEmpty();
    }

    @Override
    public void move(Direction direct) {

        Cell pos = typedOwner();
        CellPosition prevPosition = pos.position();

        if (!isAvailableSteps(REQUIRED_STEPS_FOR_MOVE)) {
            return;
        }

        Cell newPos = pos.neighbour(direct);
        if (newPos == null) {
            return;
        }

        if (!canMoveTo(newPos)) {
            return;
        }

        Unit unit = pos.extractUnit();
        newPos.putUnit(unit);
        reduceSteps(REQUIRED_STEPS_FOR_MOVE);

        if (effect != null)
            effect.Apply(this);

        fireStateChanged(new MoveEvent(this, newPos.position(), prevPosition));
    }



    public void Sleep(int turns)
    {
        while (turns >= 0) {
            fireStateChanged(new MoveEvent(this, this.position(), this.position()));

            if (effect != null)
                effect.Apply(this);

            turns--;
        }
    }


    public void moveBoxWithStep(Direction direction)
    {
        if(moveBoxBehindWithStep(direction)) return;

        moveSeveralBoxesForwardWithStep(direction);

        if (effect != null)
            effect.Apply(this);
    }

    public boolean moveBoxBehindWithStep(Direction direction)
    {
        Cell pos = typedOwner();
        Box boxBehind = null;

        if(pos.neighbour(direction.opposite()) != null && pos.neighbour(direction.opposite()).getUnit() instanceof Box)
        {
            boxBehind = (Box) pos.neighbour(direction.opposite()).getUnit();
            System.out.println("boxBehind: " + boxBehind);
        }

        if(boxBehind != null)
        {
            move(direction);
            boxBehind.move(direction);
            return true;
        } else { return false; }
    }

    public boolean moveSeveralBoxesForwardWithStep(Direction direction)
    {
        Cell pos = typedOwner();
        int overallBoxesWeight = 0;
        Stack<Box> rowOfBoxes = new Stack<Box>();

        while (pos.neighbour(direction) != null && pos.neighbour(direction).getUnit() instanceof Box)
        {
            rowOfBoxes.push((Box) pos.neighbour(direction).getUnit());
            overallBoxesWeight += rowOfBoxes.peek().getWeight();
            System.out.println("rowOfBoxes: " + rowOfBoxes.size());
            pos = pos.neighbour(direction);
        }

        if(overallBoxesWeight > getStrength() * 2)
        {
            System.out.println("Не хватает силы");
            return false;
        } else {

           while (!rowOfBoxes.isEmpty())
           {
               Box boxOnTop = rowOfBoxes.peek();
               if( ((Cell) boxOnTop.typedOwner()).neighbour(direction) != null
                       && boxOnTop.canMoveTo( ((Cell) boxOnTop.typedOwner()).neighbour(direction)))
               {
                   boxOnTop = rowOfBoxes.pop();
                   boxOnTop.move(direction);
               } else
               {
                   System.out.println("Некуда двигать коробки");
                   return false;
               }
           }
           move(direction);
           return true;
        }
    }

    public boolean MoveBoxForwardWithStep(Direction direction)
    {
        Cell pos = typedOwner();
        Box boxForward = null;

        if(pos.neighbour(direction) != null && pos.neighbour(direction).getUnit() instanceof Box)
        {
            boxForward = (Box) pos.neighbour(direction).getUnit();
            System.out.println("boxForward: " + boxForward);
        }

        if(boxForward != null) {
            boxForward.move(direction);
            move(direction);
            return true;
        } else { return false; }
    }

    public void Interact()
    {
        Cell c = typedOwner();
        Map<Direction, Cell> neighbours = c.neighbors();

        for (Map.Entry<Direction, Cell> entry : neighbours.entrySet()) {
            Cell cell = entry.getValue();

            System.out.println(cell.position().column() + " " + cell.position().row() + " " + cell.getUnit());
            if(cell.getUnit() instanceof Interactable) {
                ((Interactable) cell.getUnit()).InteractWith(this);
                return;
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
