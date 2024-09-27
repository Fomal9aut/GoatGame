package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;

public class RandomBuffGrass extends Grass {

    private final String Name = "Random";

    public RandomBuffGrass(int strengthBuff, int strengthBuffDuration)
    {
        super(strengthBuff, strengthBuffDuration);
    }

    @Override
    public void InteractWith(Unit unit) {
        if(unit instanceof Goat) {
            Goat goat = (Goat)unit;

            //TODO
            goat.applyRandomBuff(strengthBuff, strengthBuffDuration, Name);
            ((Cell) typedOwner()).extractUnit();
            System.out.println("Goat strength: " + goat.getStrength() + " Goat buff duration: " + goat.getStrengthBuffDuration());
        }
    }
}
