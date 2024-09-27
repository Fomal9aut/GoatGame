package Test;

import Model.gamefield.Pinfold;
import Model.seeders.TestSeeder;
import Model.units.Goat;
import Model.units.BuffGrass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuffGrassTest {
    @Test
    public void DefaultGrassInteraction() {
        Goat g = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(3, 3).putUnit(g);

        field.cell(3, 4).putUnit(new BuffGrass());
        g.Interact();
        Assertions.assertEquals(g.getStrength(), 2);
        Assertions.assertEquals(g.getStrengthBuffDuration(), 5);
    }

    @Test
    public void CustomGrassInteraction() {
        Goat g = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(3, 3).putUnit(g);

        field.cell(3, 4).putUnit(new BuffGrass(100, 100));
        g.Interact();
        Assertions.assertEquals(g.getStrength(), 100);
        Assertions.assertEquals(g.getStrengthBuffDuration(), 100);
    }

    @Test
    public void GrassBuffProcess() {
        Goat g = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(3, 3).putUnit(g);

        field.cell(3, 4).putUnit(new BuffGrass(3, 4));
        g.Interact();
        g.Sleep(4);
        Assertions.assertEquals(g.getStrength(), 1);
        Assertions.assertEquals(g.getStrengthBuffDuration(), 0);
    }

}
