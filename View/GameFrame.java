package View;

import Model.Game;
import Model.seeders.SimpleSeeder;
import Model.updatableunit.GameResultEvent;
import Model.updatableunit.GameStateListener;
import View.Widgets.FieldPanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class GameFrame extends JFrame {
    private FieldPanel _panel;

    private final Game _model;

    public GameFrame() {
        _model = new Game();
        _model.addGameStateListener(new GameStateListener() {
            @Override
            public void GameFinished(GameResultEvent result) {
                if(_model.is_gameInProcess()) {
                    _model.finish();
                    GameFrame.this.requestFocus();
                    if (result.GetResult()) {
                        JOptionPane.showMessageDialog(GameFrame.this, "Игра завершена. Коза съела капусту");
                    } else {
                        JOptionPane.showMessageDialog(GameFrame.this, "Игра завершена. Шаги закончились. Вы проиграли");
                    }

                }
            }
        });

        setLayout(new MigLayout("align center center"));
        createMenu();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGame();
    }



    public void startGame() {
        _model.start(10, 10, new SimpleSeeder());
        if (_panel != null) {
            remove(_panel);
        }

        _panel = FieldFactory.fromField(_model.getField());

        add(_panel, "wrap");
        repaint();
        revalidate();
        pack();
        if (_panel.getActorWidget() != null) {
            _panel.getActorWidget().requestFocus();
        }
    }

    public void createMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Игра");
        JMenuItem newGameItem = new JMenuItem("Начать новую игру");
        JMenuItem exitItem = new JMenuItem("Выход");
        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        newGameItem.addActionListener((ActionEvent e) -> {
            startGame();
        });

        menuBar.add(gameMenu);

        setJMenuBar(menuBar);

        exitItem.addActionListener(e -> System.exit(0));
    }
}
