package Test;

import Model.gamefield.Cell;
import Model.gamefield.Pinfold;
import Model.seeders.TestSeeder;
import Model.units.Cabbage;
import Model.units.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellTest {
    @Test
    public void setNewWallTest() {
        Cell cell = new Cell(null);
        cell.putUnit(new Wall());
        Assertions.assertFalse(cell.isEmpty());
        Assertions.assertInstanceOf(Wall.class, cell.getUnit());
    }

    @Test
    public void setExistingWallTest() {
        Cell cell = new Cell(null);
        cell.putUnit(new Wall());
        cell.putUnit(new Cabbage());
        Assertions.assertInstanceOf(Wall.class, cell.getUnit());
    }

    @Test
    public void removeWallTest() {
        Cell cell = new Cell(null);
        Wall wall = new Wall();
        Assertions.assertTrue(cell.putUnit(wall));
        cell.extractUnit();
        Assertions.assertTrue(cell.isEmpty());
    }

    @Test
    public void neighbourOnBorderTest() {
        Pinfold field = new Pinfold(5 ,5, new TestSeeder());
        Assertions.assertNull(field.cell(0, 4).neighbour(Model.gamefield.Direction.east()));
        Assertions.assertNull(field.cell(0, 0).neighbour(Model.gamefield.Direction.west()));
        Assertions.assertNull(field.cell(4, 4).neighbour(Model.gamefield.Direction.south()));
        Assertions.assertNull(field.cell(0, 0).neighbour(Model.gamefield.Direction.north()));
    }
}
