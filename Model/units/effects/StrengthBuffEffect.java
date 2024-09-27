package Model.units.effects;

import Model.units.Goat;

public class StrengthBuffEffect extends Effect{

    public StrengthBuffEffect(int buffStrength, int buffDuration) {
        super(buffStrength, buffDuration);
    }

    private boolean _strengthBuffApplied = false;

    @Override
    public void Apply(Goat g)
    {
        if(buffDuration == 0) g.removeEffect();

        if (!_strengthBuffApplied) {
            g.setStrength(buffStrength);
            _strengthBuffApplied = true;
            return;
        }
        buffDuration--;
    }
}
