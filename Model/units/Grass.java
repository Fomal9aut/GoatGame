package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public class Grass extends Unit implements Interactable {

    final int strengthBuff;

    public int getStrengthBuff() {
        return strengthBuff;
    }

    final int strengthBuffDuration;

    public int getStrengthBuffDuration() {
        return strengthBuffDuration;
    }

    public Grass()
    {
        this.strengthBuff = 2;
        this.strengthBuffDuration = 5;
    }


    public Grass(int strengthBuff, int strengthBuffDuration)
    {
        this.strengthBuff = strengthBuff;
        this.strengthBuffDuration = strengthBuffDuration;
    }

    @Override
    public void InteractWith(Unit unit) {
        if(unit instanceof Goat) {
            Goat goat = (Goat)unit;

            //TODO
            goat.applyStrengthBuff(strengthBuff, strengthBuffDuration);
            ((Cell) typedOwner()).extractUnit();
            System.out.println("Goat strength: " + goat.getStrength() + " Goat buff duration: " + goat.getStrengthBuffDuration());
        }
    }
}
