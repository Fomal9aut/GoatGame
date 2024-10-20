package Model.units;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

//
public class Teleporter extends Unit implements Interactable {

    private Teleporter teleporter;

    public Teleporter getTeleporter() {
        return teleporter;
    }

    private void setTeleporter(Teleporter teleporter) {
        this.teleporter = teleporter;
    }

    public void Connect(Teleporter teleporter) {
        if (this == teleporter) {
            return;
        }

        this.setTeleporter(teleporter);
        teleporter.setTeleporter(this);
    }

    public void Teleport(Unit unit) {

        if (teleporter == null) return;

        Cell TeleporterCell = teleporter.typedOwner();
        Cell UnitCell = unit.typedOwner();

        Map<Direction, Cell> TeleporterCellNeighbours = TeleporterCell.neighbors();

        for (Map.Entry<Direction, Cell> entry : TeleporterCellNeighbours.entrySet()) {
            Cell cell = entry.getValue();

            if (cell.isEmpty()) {
                Unit extractedUnit = UnitCell.extractUnit();
                cell.putUnit(extractedUnit);
                System.out.println("pos " + cell.position().column() + " " + cell.position().row());
                return;
            }
        }
    }

    public void TeleportNearby(Unit unit, Cell c)
    {
        Cell pos = unit.typedOwner();

        // рандомайзер
        Random rnd = new Random();

        // все соседние клетки от коробки
        Map<Direction, Cell> neighbours = c.neighbors();

        // список пустых клеток
        List<Cell> emptyCells = new ArrayList<>();

        // смотрим какие клетки вокруг коробки пустые
        for (Map.Entry<Direction, Cell> entry : neighbours.entrySet()) {
            Cell cell = entry.getValue();

            // если клетка пустая, вносим в список
            if(cell.isEmpty()) {
                emptyCells.add(cell);
            }
        }

        // берем случайную клетку из списка
        int idx = rnd.nextInt(emptyCells.size());
        Cell selectedCell = emptyCells.get(idx);

        if(selectedCell != null) {
            // перемещаем туда козу
            Unit g = pos.extractUnit();
            selectedCell.putUnit(g);
        }
    }

    @Override
    public void InteractWith(Goat goat) {
        if(goat.hasKeys()) {
            goat.UseKey();
            Teleport(goat);
            System.out.println("Keys remain: " + goat.Keys());
        }
    }
}
