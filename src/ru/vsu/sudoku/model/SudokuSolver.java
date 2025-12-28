package sudoku.model;

/**
 * Класс реализует алгоритм решения судоку
 * с использованием метода возврата (backtracking).
 */
public class SudokuSolver {

    /**
     * Решает судоку.
     *
     * @param board игровое поле
     * @return true, если решение найдено
     */
    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int col = 0; col < SudokuBoard.SIZE; col++) {
                if (board.getPuzzleValue(row, col) == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board.setPuzzleValue(row, col, num);
                            if (solve(board)) {
                                return true;
                            }
                            board.setPuzzleValue(row, col, 0);
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Проверяет корректность числа в ячейке.
     *
     * @param board игровое поле
     * @param row строка
     * @param col столбец
     * @param num число для проверки
     * @return true, если число допустимо
     */
    private boolean isValid(SudokuBoard board, int row, int col, int num) {
        for (int i = 0; i < SudokuBoard.SIZE; i++) {
            if (board.getPuzzleValue(row, i) == num ||
                    board.getPuzzleValue(i, col) == num) {
                return false;
            }
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board.getPuzzleValue(i, j) == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
