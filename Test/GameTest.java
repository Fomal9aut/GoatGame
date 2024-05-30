package Test;

import Model.Game;
import Model.gamefield.Direction;
import Model.seeders.SimpleSeeder;
import Model.updatableunit.CabbageEatenEvent;
import Model.updatableunit.StateChangeListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.EventObject;

public class GameTest {

    @Test
    public void GameWinTest() {
        Game game = new Game();
        game.start(10, 10, new SimpleSeeder());

        game.getField().Goat().addListener(
                new StateChangeListener() {
                    @Override
                    public void stateChanged(EventObject e) {
                        if(e instanceof CabbageEatenEvent) {
                            Assertions.assertEquals(game.getField().Cabbage().position(),
                                    ((CabbageEatenEvent) e).getCabbagePosition());
                        }
                    }
                }
        );

        game.addGameStateListener( result -> {
            Assertions.assertTrue(result.GetResult());
        });

        Assertions.assertTrue(game.is_gameInProcess());
        game.getField().Goat().Interact();
    }

    @Test
    public void GameDefeatTest() {
        Game game = new Game();
        game.start(10, 10, new SimpleSeeder());

        game.addGameStateListener( result -> {
            Assertions.assertFalse(result.GetResult());
        });

        game.getField().Goat().setSteps(1);
        game.getField().Goat().move(Direction.east());
    }
}
