package sudoku;

import ru.vsu.sudoku.view.SudokuFrame;

/**
 * Главный класс приложения Судоку.
 * Единственная точка входа в программу.
 * Создаёт и отображает графический интерфейс игры.
 */
public class Main {

    /**
     * Главный метод приложения. Запускает графический интерфейс игры Судоку.
     * Создаёт экземпляр окна SudokuFrame и делает его видимым.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        new SudokuFrame().setVisible(true);
    }
}
