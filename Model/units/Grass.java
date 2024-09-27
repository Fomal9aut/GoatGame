package Model.units;

import Model.ownership.Unit;
import Model.updatableunit.Interactable;

public abstract class Grass extends Unit implements Interactable {
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
}
