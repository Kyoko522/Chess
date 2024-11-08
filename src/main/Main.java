package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        // Main Game Frame for Human vs Human
        JFrame frame = new JFrame("Human vs Human");
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board();
        board.setBackground(Color.BLACK);
        frame.add(board);

        // Menu Frame
        JFrame menu = new JFrame("Menu");
        menu.setSize(400, 200);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(new GridBagLayout());

        // Human vs AI Frame
        JFrame alternateFrame = new JFrame("Human vs AI");
        alternateFrame.setMinimumSize(new Dimension(800, 800));
        alternateFrame.setLocationRelativeTo(null);
        alternateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add buttons to the menu
        JButton humanVsHumanButton = new JButton("Human vs Human");
        JButton humanVsAiButton = new JButton("Human vs AI");

        // Action for Human vs Human button
        humanVsHumanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false); // Hide menu
                frame.setVisible(true); // Show Human vs Human game
            }
        });

        // Action for Human vs AI button
        humanVsAiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false); // Hide menu
                alternateFrame.setVisible(true); // Show Human vs AI screen
            }
        });

        // Add buttons to menu layout
        JPanel menuPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        menuPanel.add(humanVsHumanButton);
        menuPanel.add(humanVsAiButton);
        menu.add(menuPanel);

        // Show the menu initially
        menu.setVisible(true);
    }
}