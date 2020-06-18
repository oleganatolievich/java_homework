package Lesson_8;

import javax.swing.*;
import java.awt.*;

public class TicTacToeFieldPanel extends JPanel {

    public static final int GAME_MODE_HVAI = 0;
    public static final int GAME_MODE_HVH = 1;
    private JButton[][] buttonMatrix;

    public TicTacToeFieldPanel() {
        setBackground(Color.LIGHT_GRAY);
    }

    public void startGame(int gameMode, int fieldSize, int winLength) {
        System.out.println("Let the madness begin!");
        initGameField(fieldSize);
    }

    //1. Полностью разобраться с кодом (попробовать полностью самостоятельно переписать одно из окон).
    //Разобрался.

    //2. Составить список вопросов и приложить в виде комментария к домашней работе.
    //Нужна справка по иерархии классов, составляющих GUI окна (от JFrame и ниже), за что каждый отвечает.
    //Порекомендуйте литературу по Swing с обязательной практикой, и разъясните, пожалуйста, чем Swing лучше / хуже JavaFX.

    //3. Рачертить панель на поле для игры, согласно fieldSize
    //Приведено ниже:

    private void initGameField(int fieldSize) {
        JButton[][] buttonMatrix = new JButton[fieldSize][fieldSize];
        setLayout(new GridLayout(fieldSize, fieldSize));
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                buttonMatrix[i][j] = new JButton("X");
                add(buttonMatrix[i][j]);
            }
        }
        revalidate();
        repaint();
    }

}