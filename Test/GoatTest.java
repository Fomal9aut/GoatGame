package Test;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.gamefield.Pinfold;
import Model.seeders.TestSeeder;
import Model.units.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GoatTest {
    @Test
    public void moveToDirectionsTest() {
        Goat gt = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(3, 3).putUnit(gt);

        gt.move(Direction.east());
        Assertions.assertEquals(gt.position(), field.cell(3,4).position());
        Assertions.assertEquals(gt.typedOwner(), field.cell(3, 4));

        gt.move(Direction.west());
        Assertions.assertEquals(gt.position(), field.cell(3,3).position());
        Assertions.assertEquals(gt.typedOwner(), field.cell(3, 3));

        gt.move(Direction.south());
        Assertions.assertEquals(gt.position(), field.cell(4,3).position());
        Assertions.assertEquals(gt.typedOwner(), field.cell(4, 3));

        gt.move(Direction.north());
        Assertions.assertEquals(gt.position(), field.cell(3,3).position());
        Assertions.assertEquals(gt.typedOwner(), field.cell(3, 3));
    }

    @Test
    public void connectionWithCellTest() {
        Goat gt = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(3, 3).putUnit(gt);
        Assertions.assertEquals(gt.typedOwner(), field.cell(3, 3));
    }

    @Test
    public void goatCollideWithInnerWallTest() {
        Goat gt = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(4, 3).putUnit(gt);
        field.cell(5, 3).putUnit(new Wall());
        gt.move(Direction.south());
        Assertions.assertEquals(gt.typedOwner(), field.cell(4, 3));
    }

    @Test
    public void GoatInteraction() {
        Goat g = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(3, 3).putUnit(g);

        field.cell(3, 4).putUnit(new Cabbage());
        g.Interact();
        for (Cell cell : field) {
            assertNotEquals(cell.getUnit(), field.Cabbage());
        }
    }

    @Test
    public void goatCollideWithOuterWallTest() {
        Goat gt = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(1, 1).putUnit(gt);
        gt.move(Direction.west());
        Assertions.assertEquals(gt.typedOwner(), field.cell(1, 1));
    }

    @Test
    public void goatCollideWithBoxNearOuterWallTest() {
        Goat gt = new Goat();
        Pinfold field = new Pinfold(10, 10, new TestSeeder());
        field.cell(9, 8).putUnit(gt);
        field.cell(9, 9).putUnit(new Box());
        gt.move(Direction.east());
        Assertions.assertEquals(gt.typedOwner(), field.cell(9, 8));
    }
}
