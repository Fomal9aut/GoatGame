package Model.units.effects;

import Model.units.Goat;

public abstract class Effect {

    protected int buffDuration;

    protected int buffStrength;

    public int getBuffDuration() {
        return buffDuration;
    }



    public int getBuffStrength() {
        return buffStrength;
    }

    public Effect(int buffStrength, int buffDuration)
    {
        this.buffDuration = buffDuration;
        this.buffStrength = buffStrength;
    }

    public abstract void Apply(Goat g);
}
