package Model.units;

import Model.gamefield.Cell;
import Model.ownership.Unit;
import Model.units.effects.Effect;
import Model.updatableunit.Interactable;

public class Grass extends Unit implements Interactable {

    private final Effect GrassEffect;

    public Effect getGrassEffect() {
        return GrassEffect;
    }

    public Grass(Effect e)
    {
        GrassEffect = e;
    }

    @Override
    public void InteractWith(Unit unit) {
        if(unit instanceof Goat) {
            Goat goat = (Goat)unit;

            //TODO
            goat.setEffect(GrassEffect);
            ((Cell) typedOwner()).extractUnit();
            System.out.println("Goat strength: " + goat.getStrength() + " Goat buff duration: " + goat.getEffect().getBuffDuration());
        }
    }
}
