package Model;

import Model.gamefield.Pinfold;
import Model.gamefield.Seeder;
import Model.units.Goat;
import Model.updatableunit.*;

import java.util.ArrayList;
import java.util.EventObject;

public class Game implements StateChangeListener {
    private Pinfold _field;
    private boolean _gameInProcess = false;

    private ArrayList<GameStateListener> _listeners = new ArrayList<>();

    public boolean is_gameInProcess() {return _gameInProcess;}

    public void finish() {_gameInProcess = false;}

    public Pinfold getField() {return _field;}

    public void start(int width,int height, Seeder seeder) {
        _field = new Pinfold(height, width, seeder);

        _field.Goat().addListener(this);
        _field.Cabbage().addCabbageEatenListener(new CabbageEatenListener() {
            @Override
            public void cabbageEaten(CabbageEatenEvent event) {
                fireGameFinished(true);
            }
        });

        _gameInProcess = true;
    }

    @Override
    public void stateChanged(EventObject e) {
        if (e.getSource() instanceof Goat)
        {
            if(!((Goat) e.getSource()).hasSteps())
                fireGameFinished(false);
        }
    }

    private void fireGameFinished(boolean result) {
        for( GameStateListener listener : _listeners)
            listener.GameFinished(new GameResultEvent(result));
    }

    public void addGameStateListener(GameStateListener listener) {
        _listeners.add(listener);
    }

    public void removeGameStateListener(GameStateListener listener) {
        _listeners.add(listener);
    }

    public boolean hasGameStateListeners() {return !_listeners.isEmpty();}
}
