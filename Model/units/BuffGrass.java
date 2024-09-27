package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public class BuffGrass extends Grass {

    private String Name = "Buff";

    public BuffGrass(int strengthBuff, int strengthBuffDuration)
    {
        super(strengthBuff, strengthBuffDuration);
    }

    public BuffGrass()
    {
       super();
    }


    @Override
    public void InteractWith(Unit unit) {
        if(unit instanceof Goat) {
            Goat goat = (Goat)unit;

            //TODO
            goat.applyStrengthBuff(strengthBuff, strengthBuffDuration, Name);
            ((Cell) typedOwner()).extractUnit();
            System.out.println("Goat strength: " + goat.getStrength() + " Goat buff duration: " + goat.getStrengthBuffDuration());
        }
    }
}
