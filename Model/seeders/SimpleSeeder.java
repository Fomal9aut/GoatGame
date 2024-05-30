package Model.seeders;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.gamefield.Seeder;
import Model.units.Box;
import Model.units.Key;
import Model.units.Teleporter;
import Model.units.Wall;

public class SimpleSeeder extends Seeder {

    @Override
    protected void seedRobot() {
        _field.cell(4, 0).putUnit( _field.Goat() );
    }

    @Override
    protected void seedCabbage() { _field.cell(3,7 ).putUnit(_field.Cabbage());}

    @Override
    protected void seedUnits() {

        Cell underRobotCell = _field.Goat().typedOwner();
        underRobotCell = underRobotCell.neighbour( Direction.south()) ;
        _field.cell(2, 2).putUnit( new Box() );
        underRobotCell.putUnit( new Box());
        _field.cell(4, 4).putUnit(new Key());
        _field.cell(5, 5).putUnit(new Key());

        for(int i = 0; i < _field.height(); i++)
            _field.cell(i,6).putUnit(new Wall());

        Teleporter tp1= new Teleporter();
        Teleporter tp2= new Teleporter();

        _field.cell(0,0).putUnit(tp1);
        _field.cell(0,8).putUnit(tp2);

        tp1.Connect(tp2);

    }
}
