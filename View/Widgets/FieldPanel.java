package View.Widgets;

import Model.gamefield.Cell;
import Model.gamefield.CellPosition;
import Model.gamefield.Pinfold;


import Model.ownership.Unit;
import View.WidgetFactory;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;

public class FieldPanel extends JPanel {
    private int _horizontalCellCount = 10;
    private int _verticalCellCount = 10;

    private UnitWidget _actor;

    private static final int MAX_HORIZONTAL_CELL_COUNT = 24;
    private static final int MAX_VERTICAL_CELL_COUNT = 15;

    private Pinfold _modelField;

    public void setHorizontalCellCount(int count) throws IllegalArgumentException {
        if (count > MAX_HORIZONTAL_CELL_COUNT) {
            throw new IllegalArgumentException(String.format(
                    "Количество ячеек по горизонтали превышает максимальное допустимое. Максимальное: %d",
                    MAX_HORIZONTAL_CELL_COUNT));
        }
        _horizontalCellCount = count;
    }

    public void setVerticalCellCount(int count) {
        if (count > MAX_VERTICAL_CELL_COUNT) {
            throw new IllegalArgumentException(String.format(
                    "Количество ячеек по вертикали превышает максимальное допустимое. Максимальное: %d",
                    MAX_VERTICAL_CELL_COUNT));
        }
        _verticalCellCount = count;
    }

    void prepareNewField() {
        this.removeAll();
        for (int i = 0; i < _verticalCellCount; i++) {
            for (int j = 0; j < _horizontalCellCount; j++) {
                if (j == _horizontalCellCount - 1) {
                    add(new CellWidget(this), "wrap");
                }
                else {
                    add(new CellWidget(this));
                }
            }
        }
        int maxCellInLine = Math.max(_horizontalCellCount, _verticalCellCount);
        if (maxCellInLine <= 5) {
            setCellSize(CellWidget.LARGE_SIZE);
        } else if (maxCellInLine <= 8) {
            setCellSize(CellWidget.MEDIUM_SIZE);
        } else {
            setCellSize(CellWidget.SMALL_SIZE);
        }
    }

    public void setCellSize(int size) {
        for (Component cmp : getComponents()) {
            Dimension dim = new Dimension(size, size);
            cmp.setPreferredSize(dim);
            cmp.setSize(dim);
        }
    }


    public CellWidget cellAt(CellPosition pos) {
        return cellAt(pos.row(), pos.column());
    }

    public CellWidget cellAt(int row, int col) {
        return (CellWidget) getComponent((row) * _horizontalCellCount + (col));
    }

    public UnitWidget getActorWidget() {
        return _actor;
    }

    public FieldPanel() {
        super();
    }

    public void setModelField(Pinfold pinfold) {
        _modelField = pinfold;
        setHorizontalCellCount(pinfold.width());
        setVerticalCellCount(pinfold.height());
        setLayout(new MigLayout("gap 0"));
        prepareNewField();
        placeWidgets();
    }
    public Pinfold getModelField()
    {
        return _modelField;
    }

     void placeWidgets() {
        for (Cell cell : _modelField) {
            Unit unit = cell.getUnit();
                UnitWidget entityWidget = WidgetFactory.placeUnitWidget(unit, this);
                if(entityWidget == null)
                {
                    cellAt(cell.position()).removeAll();
                    cellAt(cell.position()).repaint();
                    cellAt(cell.position()).revalidate();
                }

                if (entityWidget instanceof GoatWidget) {
                    _actor = entityWidget;
                    _actor.requestFocus();
                }
        }
    }

}
