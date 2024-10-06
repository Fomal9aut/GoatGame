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
        g.addItem(k);
        Assertions.assertEquals(g.items(), 1);

        Key k2 = new Key();
        g.addItem(k2);
        Assertions.assertEquals(g.items(), 2);

        g.getItem(Key.class);
        Assertions.assertEquals(g.items(), 1);

        g.getItem(Key.class);
        Assertions.assertEquals(g.items(), 0);
    }

    @Test
    public void KeyInteraction() {
        Goat g = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(3, 3).putUnit(g);

        field.cell(3, 4).putUnit(new Key());
        g.Interact();
        Assertions.assertEquals(g.items(), 1);
    }
}
