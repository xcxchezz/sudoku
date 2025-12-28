package sudoku.model;

/**
 * Хранит игровое поле и решение судоку.
 * Предоставляет методы для работы с игровым полем (головоломкой)
 * и правильным решением, а также для проверки корректности ввода.
 */
public class SudokuBoard {

    /** Размер игрового поля судоку (стандартно 9x9) */
    public static final int SIZE = 9;

    /** Игровое поле (частично заполненное) */
    private final int[][] puzzle;

    /** Полное правильное решение */
    private final int[][] solution;

    /**
     * Создаёт пустые поля судоку.
     */
    public SudokuBoard() {
        puzzle = new int[SIZE][SIZE];
        solution = new int[SIZE][SIZE];
    }

    /**
     * Устанавливает значение в игровом поле по указанным координатам.
     *
     * @param row номер строки (0-8)
     * @param col номер столбца (0-8)
     * @param value значение для установки (0-9, где 0 означает пустую ячейку)
     */
    public void setPuzzleValue(int row, int col, int value) {
        puzzle[row][col] = value;
    }

    /**
     * Устанавливает значение в решении по указанным координатам.
     *
     * @param row номер строки (0-8)
     * @param col номер столбца (0-8)
     * @param value значение для установки (1-9)
     */
    public void setSolutionValue(int row, int col, int value) {
        solution[row][col] = value;
    }

    /**
     * Возвращает значение из игрового поля по указанным координатам.
     *
     * @param row номер строки (0-8)
     * @param col номер столбца (0-8)
     * @return значение в ячейке (0-9, где 0 означает пустую ячейку)
     */
    public int getPuzzleValue(int row, int col) {
        return puzzle[row][col];
    }

    /**
     * Возвращает правильное значение из решения по указанным координатам.
     *
     * @param row номер строки (0-8)
     * @param col номер столбца (0-8)
     * @return правильное значение в ячейке (1-9)
     */
    public int getSolutionValue(int row, int col) {
        return solution[row][col];
    }

    /**
     * Проверяет, совпадает ли введённое число с правильным решением.
     *
     * @param row номер строки (0-8)
     * @param col номер столбца (0-8)
     * @param value значение для проверки
     * @return true, если значение совпадает с решением, false в противном случае
     */
    public boolean isCorrect(int row, int col, int value) {
        return solution[row][col] == value;
    }

    /**
     * Проверяет, является ли ячейка изначально заполненной (неизменяемой).
     *
     * @param row номер строки (0-8)
     * @param col номер столбца (0-8)
     * @return true, если ячейка изначально заполнена, false если ячейка пустая
     */
    public boolean isFixedCell(int row, int col) {
        return puzzle[row][col] != 0;
    }
}
