package Model.units;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.ownership.Unit;
import Model.updatableunit.Interactable;

import java.util.Map;

public class Teleporter extends Unit implements Interactable {

    private Teleporter teleporter;

    public Teleporter getTeleporter() {
        return teleporter;
    }

    private void setTeleporter(Teleporter teleporter) {
        this.teleporter = teleporter;
    }

    public void Connect(Teleporter teleporter) {
        if(this == teleporter) {
            return;
        }

        this.setTeleporter(teleporter);
        teleporter.setTeleporter(this);

    }

    public void Teleport(Unit unit) {

        if(teleporter == null) return;

        Cell TeleporterCell = teleporter.typedOwner();
        Cell UnitCell = unit.typedOwner();

        Map<Direction, Cell> TeleporterCellNeighbours = TeleporterCell.neighbors();

        for (Map.Entry<Direction, Cell> entry : TeleporterCellNeighbours.entrySet()) {
            Cell cell = entry.getValue();

            if(cell.isEmpty())
            {
                Unit extractedUnit = UnitCell.extractUnit();
                cell.putUnit(extractedUnit);
                System.out.println("pos " + cell.position().column() + " " + cell.position().row());
                return;
            }
        }
    }

    @Override
    public void InteractWith(Unit unit) {
        if(unit instanceof Goat && ((Goat)unit).items() > 0) {
            Key key = ((Goat)unit).getItem(Key.class);

            if(key != null) {
                Teleport(unit);
                System.out.println("Keys remain: " + ((Goat) unit).items());
            } else {
                System.out.println("No keys!");
            }
        }
    }
}
