package Model.ownership;

// Может владеть юнитом
public interface CanOwnUnit {

    Object getUnit();
    boolean isEmpty();

    boolean putUnit(Unit init);
    Unit extractUnit();
}
