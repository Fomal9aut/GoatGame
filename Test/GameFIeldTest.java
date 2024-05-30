package Test;

import Model.gamefield.GameField;
import Model.seeders.TestSeeder;
import Model.units.Box;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameFIeldTest {
    @Test
    public void neighbourTest() {
        GameField field = new GameField(6,6, new TestSeeder());
        Assertions.assertTrue(field.cell(3, 3).isNeighbor(field.cell(3,2)));
        Assertions.assertTrue(field.cell(3, 3).isNeighbor(field.cell(3,4)));
        Assertions.assertTrue(field.cell(3, 3).isNeighbor(field.cell(4,3)));
        Assertions.assertTrue(field.cell(3, 3).isNeighbor(field.cell(2,3)));
    }

    @Test
    public void paddockFillTest() {
        GameField field = new GameField(6,6, new TestSeeder());
        Box box = new Box();
        field.cell(3, 3).putUnit(box);
        Assertions.assertFalse(field.cell(3,3).isEmpty());
        Assertions.assertInstanceOf(Box.class, field.cell(3, 3).getUnit());
    }

}
