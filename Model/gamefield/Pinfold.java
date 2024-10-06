package Model.gamefield;

import java.util.HashMap;
import java.util.Iterator;

import Model.units.Cabbage;
import Model.units.Goat;
import Model.updatableunit.CabbageEatenEvent;
import Model.updatableunit.CabbageEatenListener;

// Прямоугольное поле, состоящее из ячеек
public class Pinfold implements Iterable<Cell>, CabbageEatenListener {

    // ---------------------- Размеры -----------------------------

    private final int _width;
    private final int _height;

    public int width() {
        return _width;
    }

    public int height() {
        return _height;
    }


    // --------------------------- Ячейки ----------------------

    private final HashMap<CellPosition, Cell> _cells = new HashMap<>();

    public Cell cell(CellPosition pos) {
        return _cells.get( pos );
    }

    public Cell cell(int row, int col) {
        return cell(new CellPosition(row, col));
    }

    @Override
    public Iterator<Cell> iterator() {
        return new GameFieldIterator( this );
    }

    // ---------------------------- Порождение ---------------------

    public Pinfold(int height, int width, Seeder seeder) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }

        _width = width;
        _height = height;
        buildField();


        _cabbage.addCabbageEatenListener(this);
        seeder.setField(this);
        seeder.run();
    }

    private void buildField() {

        // Создаем ячейки
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                CellPosition pos = new CellPosition(row, col);
                _cells.put(pos, new Cell(pos));
            }
        }

        // Связываем ячейки
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {

                Cell cell = cell(row, col);

                if (height() > 1 && row < height() - 1) {
                    cell.setNeighbor(Direction.south(), cell(row + 1, col));
                }
                if (row > 0) {
                    cell.setNeighbor(Direction.north(), cell(row - 1, col));
                }
                if (width() > 1 && col < width() - 1) {
                    cell.setNeighbor(Direction.east(), cell(row, col + 1));
                }
                if (col > 0) {
                    cell.setNeighbor(Direction.west(), cell(row, col - 1));
                }
            }
        }
    }


    private final Goat _goat = new Goat(100);

    public Goat Goat() {
        return _goat;
    }

    private final Cabbage _cabbage = new Cabbage();
    public Cabbage Cabbage() {return _cabbage;}


    @Override
    public void cabbageEaten(CabbageEatenEvent event) {
        // Обработка события съедания капусты
        System.out.println("Коза съела капусту!");
    }


    private  class GameFieldIterator implements Iterator<Cell> {

        private Cell _cell = null;
        private final Pinfold _field;

        public GameFieldIterator(Pinfold field) {
            _field = field;
        }

        @Override
        public boolean hasNext() {

            return nextCell( _cell ) != null;
        }

        @Override
        public Cell next() {
            _cell = nextCell(_cell);
            return _cell;
        }

        private Cell nextCell(Cell cell) {
            Cell next_cell = null;

            if(cell == null) {
                next_cell = _field.cell(0, 0);
            } else {
                next_cell = cell.neighbour( Direction.east() );
                if( next_cell == null && cell.position().row() < _field.height()-1 ) {
                    next_cell = _field.cell( cell.position().row() + 1 , 0 );
                }
            }

            return next_cell;
        }
    }
}