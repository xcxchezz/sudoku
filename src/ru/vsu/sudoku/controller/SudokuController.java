package sudoku.controller;

import ru.vsu.sudoku.model.SudokuBoard;
import ru.vsu.sudoku.model.SudokuGenerator;

/**
 * Управляет логикой игры судоку.
 * Координирует взаимодействие между моделью данных (SudokuBoard)
 * и генератором головоломок (SudokuGenerator).
 */
public class SudokuController {

    /** Игровая доска с текущим состоянием головоломки и решением */
    private final SudokuBoard board;

    /** Генератор новых головоломок судоку */
    private final SudokuGenerator generator;

    /**
     * Создаёт контроллер и генерирует уровень.
     */
    public SudokuController() {
        board = new SudokuBoard();
        generator = new SudokuGenerator();
        generator.generate(board, 30); // сложность
    }

    public SudokuBoard getBoard() {
        return board;
    }

    /**
     * Проверяет правильность хода игрока, сравнивая введённое значение
     * с правильным решением в указанной ячейке.
     *
     * @param row номер строки (0-8)
     * @param col номер столбца (0-8)
     * @param value значение, введённое игроком
     * @return true, если значение правильное, false в противном случае
     */
    public boolean checkMove(int row, int col, int value) {
        return board.isCorrect(row, col, value);
    }
}
