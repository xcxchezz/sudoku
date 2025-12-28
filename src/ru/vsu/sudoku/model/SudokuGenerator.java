package sudoku.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Генерирует уровни судоку.
 * Использует алгоритм заполнения с возвратом (backtracking)
 * для создания корректного решения, а затем удаляет часть ячеек
 * для создания игрового поля.
 */
public class SudokuGenerator {

    /** Генератор случайных чисел для перемешивания и удаления ячеек */
    private final Random random = new Random();

    /**
     * Генерирует новое судоку.
     *
     * @param board игровая доска
     * @param emptyCells количество пустых ячеек
     */
    public void generate(SudokuBoard board, int emptyCells) {
        fillBoard(board, 0, 0);
        copySolution(board);
        removeCells(board, emptyCells);
    }

    /**
     * Рекурсивно заполняет поле корректным решением судоку
     * с использованием алгоритма возврата (backtracking).
     * Начинает заполнение с указанной позиции и продолжает до конца поля.
     *
     * @param board игровая доска для заполнения
     * @param row текущая строка для заполнения
     * @param col текущий столбец для заполнения
     * @return true, если поле успешно заполнено, false если решение невозможно
     */
    private boolean fillBoard(SudokuBoard board, int row, int col) {
        if (row == 9) return true;

        int nextRow = col == 8 ? row + 1 : row;
        int nextCol = (col + 1) % 9;

        List<Integer> numbers = shuffledNumbers();

        for (int num : numbers) {
            if (isValid(board, row, col, num)) {
                board.setSolutionValue(row, col, num);
                if (fillBoard(board, nextRow, nextCol)) {
                    return true;
                }
            }
        }
        board.setSolutionValue(row, col, 0);
        return false;
    }

    /**
     * Копирует полное решение в игровое поле.
     * Используется перед удалением ячеек для создания головоломки.
     *
     * @param board игровая доска, для которой копируется решение
     */
    private void copySolution(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.setPuzzleValue(i, j, board.getSolutionValue(i, j));
            }
        }
    }

    /**
     * Случайным образом удаляет указанное количество ячеек из игрового поля,
     * создавая головоломку заданной сложности.
     *
     * @param board игровая доска, из которой удаляются ячейки
     * @param count количество ячеек для удаления
     */
    private void removeCells(SudokuBoard board, int count) {
        while (count > 0) {
            int r = random.nextInt(9);
            int c = random.nextInt(9);
            if (board.getPuzzleValue(r, c) != 0) {
                board.setPuzzleValue(r, c, 0);
                count--;
            }
        }
    }

    /**
     * Проверяет, можно ли разместить указанное число в заданной ячейке,
     * согласно правилам судоку (не должно быть дубликатов в строке,
     * столбце и блоке 3x3).
     *
     * @param board игровая доска для проверки
     * @param row номер строки (0-8)
     * @param col номер столбца (0-8)
     * @param num число для проверки (1-9)
     * @return true, если число можно разместить, false в противном случае
     */
    private boolean isValid(SudokuBoard board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board.getSolutionValue(row, i) == num ||
                    board.getSolutionValue(i, col) == num) {
                return false;
            }
        }

        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board.getSolutionValue(i, j) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Создаёт и возвращает перемешанный список чисел от 1 до 9.
     * Используется для случайного порядка подстановки чисел при генерации,
     * что обеспечивает разнообразие решений.
     *
     * @return список чисел от 1 до 9 в случайном порядке
     */
    private List<Integer> shuffledNumbers() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) nums.add(i);
        Collections.shuffle(nums);
        return nums;
    }
}
