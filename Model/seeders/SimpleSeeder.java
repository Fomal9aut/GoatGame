package Model.seeders;

import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.gamefield.Seeder;
import Model.units.*;
import Model.units.effects.RandomBuffEffect;
import Model.units.effects.StrengthBuffEffect;

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

        _field.cell(4, 4).putUnit(new Grass(new StrengthBuffEffect(4, 10)));
        _field.cell(4, 5).putUnit(new Grass(new RandomBuffEffect(4, 100)));



    }
}
