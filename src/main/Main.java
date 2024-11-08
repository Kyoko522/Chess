package main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setLocationRelativeTo(null);

        Board board = new Board();
        board.setBackground(Color.BLACK);
        frame.add(board);

        frame.setVisible(true);

    }

}