package Model.gamefield;

// Абстрактный заполнитель поля
abstract public class Seeder {

    protected GameField _field;

    void setField(GameField field) {
        _field = field;
    }

    public void run() {
        seedRobot();
        seedUnits();
        seedCabbage();
    }

    abstract protected void seedCabbage();

    abstract protected void seedRobot();

    abstract protected void seedUnits();
}
