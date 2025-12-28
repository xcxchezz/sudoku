package sudoku.view;

import ru.vsu.sudoku.controller.SudokuController;
import ru.vsu.sudoku.model.SudokuBoard;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

/**
 * Графический интерфейс игры Судоку.
 * Отображает игровое поле, разделённое на блоки 3x3,
 * и позволяет пользователю вводить числа.
 * Визуально выделяет изначально заполненные ячейки
 * и проверяет правильность ввода игрока.
 */
public class SudokuFrame extends JFrame {

    /** Массив текстовых полей для отображения и ввода значений судоку */
    private final JTextField[][] cells = new JTextField[9][9];

    /** Контроллер для управления логикой игры */
    private final SudokuController controller;

    /**
     * Создаёт главное окно приложения Судоку.
     */
    public SudokuFrame() {
        controller = new SudokuController();
        SudokuBoard board = controller.getBoard();

        setTitle("Sudoku");
        setSize(500, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        Font font = new Font("Arial", Font.BOLD, 18);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                JTextField field = new JTextField();
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setFont(font);

                // Толстые границы для блоков 3x3
                int top = (row % 3 == 0) ? 3 : 1;
                int left = (col % 3 == 0) ? 3 : 1;
                int bottom = (row == 8) ? 3 : 1;
                int right = (col == 8) ? 3 : 1;

                field.setBorder(new MatteBorder(top, left, bottom, right, Color.BLACK));

                // Если ячейка изначально заполнена
                if (board.getPuzzleValue(row, col) != 0) {
                    field.setText(String.valueOf(board.getPuzzleValue(row, col)));
                    field.setEditable(false);
                    field.setBackground(Color.LIGHT_GRAY);
                } else {
                    int r = row;
                    int c = col;

                    field.addActionListener(e -> checkInput(field, r, c));
                }

                cells[row][col] = field;
                gridPanel.add(field);
            }
        }

        add(gridPanel);
    }

    /**
     * Проверяет корректность введённого пользователем числа.
     *
     * @param field поле ввода
     * @param row строка
     * @param col столбец
     */
    private void checkInput(JTextField field, int row, int col) {
        try {
            int value = Integer.parseInt(field.getText());

            if (value < 1 || value > 9) {
                field.setForeground(Color.RED);
                return;
            }

            if (controller.checkMove(row, col, value)) {
                field.setForeground(Color.BLUE);
            } else {
                field.setForeground(Color.RED);
            }

        } catch (NumberFormatException e) {
            field.setText("");
            field.setForeground(Color.BLACK);
        }
    }
}
