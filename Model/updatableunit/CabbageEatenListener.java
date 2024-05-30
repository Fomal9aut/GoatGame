package Model.updatableunit;

import java.util.EventListener;

public interface CabbageEatenListener extends EventListener {
    void cabbageEaten(CabbageEatenEvent event);
}
