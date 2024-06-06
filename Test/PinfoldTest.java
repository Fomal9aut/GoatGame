package Test;

import Model.gamefield.Cell;
import Model.gamefield.CellPosition;
import Model.gamefield.Pinfold;
import Model.seeders.TestSeeder;
import Model.units.Box;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PinfoldTest {
    @Test
    public void testCellCreation() {
        Pinfold pinfold = new Pinfold(3, 3, new TestSeeder());

        for (int row = 0; row < pinfold.height(); row++) {
            for (int col = 0; col < pinfold.width(); col++) {
                CellPosition pos = new CellPosition(row, col);
                assertNotNull(pinfold.cell(pos));
            }
        }
    }

    @Test
    public void testGoatCreation() {
        Pinfold pinfold = new Pinfold(3, 3, new TestSeeder());

        assertNotNull(pinfold.Goat());
        for (Cell cell : pinfold) {
            assertNotEquals(cell.getUnit(), pinfold.Goat());
        }
    }

    @Test
    public void testCabbageCreation() {
        Pinfold pinfold = new Pinfold(3, 3, new TestSeeder());

        assertNotNull(pinfold.Cabbage());
        for (Cell cell : pinfold) {
            assertNotEquals(cell.getUnit(), pinfold.Cabbage());
        }
    }

    @Test
    public void testIterator() {
        Pinfold pinfold = new Pinfold(3, 3, new TestSeeder());
        int count = 0;

        for (Cell cell : pinfold) {
            assertNotNull(cell);
            count++;
        }

        assertEquals(9, count);
    }

    @Test
    public void neighbourTest() {
        Pinfold field = new Pinfold(6,6, new TestSeeder());
        Assertions.assertTrue(field.cell(3, 3).isNeighbor(field.cell(3,2)));
        Assertions.assertTrue(field.cell(3, 3).isNeighbor(field.cell(3,4)));
        Assertions.assertTrue(field.cell(3, 3).isNeighbor(field.cell(4,3)));
        Assertions.assertTrue(field.cell(3, 3).isNeighbor(field.cell(2,3)));
    }

    @Test
    public void paddockFillTest() {
        Pinfold field = new Pinfold(6,6, new TestSeeder());
        Box box = new Box();
        field.cell(3, 3).putUnit(box);
        Assertions.assertFalse(field.cell(3,3).isEmpty());
        Assertions.assertInstanceOf(Box.class, field.cell(3, 3).getUnit());
    }

}
