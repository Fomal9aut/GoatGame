package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public class Grass extends Unit implements Interactable {

    final int strengthBuff;

    private final String Name = "Buff";

    public int getStrengthBuff() {
        return strengthBuff;
    }

    final int strengthBuffDuration;

    public int getStrengthBuffDuration() {
        return strengthBuffDuration;
    }

    public Grass(int strengthBuff, int strengthBuffDuration)
    {
        this.strengthBuff = strengthBuff;
        this.strengthBuffDuration = strengthBuffDuration;
    }

    public Grass()
    {
        this.strengthBuff = 2;
        this.strengthBuffDuration = 5;
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
