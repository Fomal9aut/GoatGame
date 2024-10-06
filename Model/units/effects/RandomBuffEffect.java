package Model.units.effects;

import Model.units.Goat;

import java.util.Random;

public class RandomBuffEffect extends Effect{

    public RandomBuffEffect(int buffStrength, int buffDuration) {
        super(buffStrength, buffDuration);
    }

    private final Random rnd = new Random();

    @Override
    public void Apply(Goat g) {
        if(buffDuration == 0) g.removeEffect();

        g.setStrength(g.getStrength() + rnd.nextInt(2 * buffStrength + 1) - buffStrength);

        buffDuration--;
    }
}
