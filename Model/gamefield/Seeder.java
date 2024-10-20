package Model.gamefield;

// Абстрактный заполнитель поля
abstract public class Seeder {

    protected Pinfold _field;

    void setField(Pinfold field) {
        _field = field;
    }

    public void run() {
        seedGoat();
        seedUnits();
        seedCabbage();
    }

    abstract protected void seedCabbage();

    abstract protected void seedGoat();

    abstract protected void seedUnits();
}
