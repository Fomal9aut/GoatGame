package Test;

import Model.gamefield.Pinfold;
import Model.seeders.TestSeeder;
import Model.units.Goat;
import Model.units.Key;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeyTest {
    @Test
    public void KeyAddingRemoving() {
        Goat g = new Goat();

        Key k = new Key();
        g.AddKey(k);
        Assertions.assertEquals(g.Keys(), 1);

        Key k2 = new Key();
        g.AddKey(k2);
        Assertions.assertEquals(g.Keys(), 2);

        g.PopKey();
        Assertions.assertEquals(g.Keys(), 1);

        g.PopKey();
        Assertions.assertEquals(g.Keys(), 0);
    }

    @Test
    public void KeyInteraction() {
        Goat g = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(3, 3).putUnit(g);

        field.cell(3, 4).putUnit(new Key());
        g.Interact();
        Assertions.assertEquals(g.Keys(), 1);
    }
}
