package Model.seeders;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.gamefield.Seeder;
import Model.units.*;

public class SimpleSeeder extends Seeder {

    @Override
    protected void seedGoat() {
        _field.cell(4, 0).putUnit( _field.Goat() );
    }

    @Override
    protected void seedCabbage() { _field.cell(7,2 ).putUnit(_field.Cabbage());}

    @Override
    protected void seedUnits() {

        Cell underRobotCell = _field.Goat().typedOwner();
        underRobotCell = underRobotCell.neighbour( Direction.south()) ;
        _field.cell(2, 2).putUnit( new BoxWithUnit(new Teleporter()) );
        underRobotCell.putUnit( new Box());
        _field.cell(4, 4).putUnit(new Key());
        _field.cell(5, 5).putUnit(new Key());
        _field.cell(4, 5).putUnit(new Wheat());
        _field.cell(3, 5).putUnit(new Wheat());

        for(int i = 0; i < _field.width()/2; i++)
            _field.cell(6,i).putUnit(new Wall());

        Teleporter tp1= new Teleporter();
        Teleporter tp2= new Teleporter();

        _field.cell(0,0).putUnit(tp1);
        _field.cell(0,6).putUnit(tp2);

        tp1.Connect(tp2);

    }
}
