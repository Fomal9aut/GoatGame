package Model.ownership;

public class OwnershipProperty implements CanOwnUnit {

    private final CanOwnUnit _owner;

    public OwnershipProperty(CanOwnUnit owner) {
        if(owner == null) {
            throw new NullPointerException();
        }
        _owner = owner;
    }

    private Unit _unit = null;

    public boolean putUnit(Unit unit){
        boolean ok = false;

        if(_unit == null) {
            ok = unit.setOwner(_owner);
            if( ok ) {
                _unit = unit;
            }
        }

        return ok;
    }

    public Unit extractUnit(){
        if( !isEmpty() ) {
            _unit.removeOwner();
        }

        Unit removedUnit = _unit;
        _unit = null;

        return removedUnit;
    }

    public Unit getUnit() {
        return _unit;
    }

    public boolean isEmpty() {
        return _unit == null;
    }
}
