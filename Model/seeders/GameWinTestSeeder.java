package Model.seeders;

import Model.gamefield.Seeder;

public class GameWinTestSeeder extends Seeder {
    @Override
    protected void seedCabbage() {
        _field.cell(0, 0).putUnit(_field.Cabbage());
    }

    @Override
    protected void seedGoat() {
        _field.cell(0, 1).putUnit(_field.Goat());
    }

    @Override
    protected void seedUnits() {

    }
}
