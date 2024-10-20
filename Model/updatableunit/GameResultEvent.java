package Model.updatableunit;

import Model.ownership.Unit;

import java.util.EventObject;
import java.util.Optional;

// ивенты(письма)
public class GameResultEvent extends EventObject {

    private final boolean gameResult;

    public GameResultEvent(boolean result) {
        super(Optional.of(result));
        gameResult = result;
    }

    public boolean GetResult() { return gameResult;}
}
