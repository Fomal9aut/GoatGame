package Model.seeders;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.gamefield.Seeder;
import Model.units.*;

public class SimpleSeeder extends Seeder {

    @Override
    protected void seedRobot() {
        _field.cell(4, 0).putUnit( _field.Goat() );
    }

    @Override
    protected void seedCabbage() { _field.cell(3,7 ).putUnit(_field.Cabbage());}

    @Override
    protected void seedUnits() {

        Cell underGoatCell = _field.Goat().typedOwner();
        underGoatCell = underGoatCell.neighbour(Direction.south()) ;
        _field.cell(3, 2).putUnit( new Box() );
        _field.cell(2, 2).putUnit( new Box() );
        underGoatCell.putUnit( new Box());

        for(int i = 2; i < _field.height()-3; i++)
            _field.cell(i,6).putUnit(new Wall());

        _field.cell(4, 4).putUnit(new Grass());
        _field.cell(5, 5).putUnit(new Grass(8, 10));
        _field.cell(6, 5).putUnit(new RandomGrass(4, 10));


    }
}
