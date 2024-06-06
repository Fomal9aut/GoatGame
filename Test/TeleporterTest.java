package Test;

import Model.gamefield.CellPosition;
import Model.gamefield.Direction;
import Model.gamefield.Pinfold;
import Model.seeders.TestSeeder;
import Model.units.Goat;
import Model.units.Key;
import Model.units.Teleporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TeleporterTest {

    @Test
    public void ConnectTest() {
        Teleporter tp1 = new Teleporter();
        Teleporter tp2 = new Teleporter();

        tp1.Connect(tp2);
        Assertions.assertEquals(tp1.getTeleporter(), tp2);
        Assertions.assertEquals(tp2.getTeleporter(), tp1);
    }

    @Test
    public void ConnectInnerTest() {
        Teleporter tp1 = new Teleporter();
        tp1.Connect(tp1);

        Assertions.assertEquals(tp1.getTeleporter(), null);
    }

    @Test
    public void TeleportTest() {
        Goat g = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(2, 0).putUnit(g);

        Teleporter tp1 = new Teleporter();
        field.cell(0, 0).putUnit(tp1);
        Teleporter tp2 = new Teleporter();
        field.cell(0, 7).putUnit(tp2);
        tp1.Connect(tp2);

        field.cell(2,1).putUnit(new Key());
        g.Interact();
        g.move(Direction.north());
        g.Interact();
        Assertions.assertEquals(g.position(), new CellPosition(0, 6));
        Assertions.assertEquals(g.Keys(), 0);
    }

    @Test
    public void TeleportNowhereTest() {
        Goat g = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(2, 0).putUnit(g);

        Teleporter tp1 = new Teleporter();
        field.cell(0, 0).putUnit(tp1);;

        field.cell(2,1).putUnit(new Key());
        g.Interact();
        g.move(Direction.north());
        g.Interact();
        Assertions.assertEquals(g.position(), new CellPosition(1, 0));
        Assertions.assertEquals(g.Keys(), 0);
    }

}
