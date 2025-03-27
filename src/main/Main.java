package main;

import ai.ChessAI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static boolean isHumanVsAI = false; // Flag to check if it's Human vs AI mode
    private static ChessAI aiOpponent; // Instance of AI for Human vs AI mode

    public static void main(String[] args) {

        // Menu Frame
        JFrame menu = new JFrame("Menu");
        menu.setSize(400, 200);
        menu.setLocationRelativeTo(null);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(new GridBagLayout());

        // Main Game Frame for Human vs Human
        JFrame frame = new JFrame("Human vs Human");
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //TODO: Human vs AI Frame
        JFrame alternateFrame = new JFrame("Human vs AI");
        alternateFrame.setLayout(new GridBagLayout());
        alternateFrame.setMinimumSize(new Dimension(800, 800));
        alternateFrame.setLocationRelativeTo(null);
        alternateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add buttons to the menu
        JButton humanVsHumanButton = new JButton("Human vs Human");
        //TODO: This will enable the option to compete against the AI
        JButton humanVsAiButton = new JButton("Human vs AI");

        // Action for Human vs Human button
        humanVsHumanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isHumanVsAI = false; // Set mode to Human vs Human

                // Create a new Board instance for Human vs Human and add it to frame
                Board board = new Board(isHumanVsAI); // Pass false for Human vs Human
                board.setBackground(Color.BLACK);
                frame.add(board);

                menu.setVisible(false); // Hide menu
                frame.setVisible(true); // Show Human vs Human game
            }
        });

        //TODO: Action for Human vs AI button
        humanVsAiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isHumanVsAI = true; // Set mode to Human vs AI
                aiOpponent = new ChessAI(3); // Initialize AI with depth level of 3

                // Create a new Board instance for Human vs AI and add it to alternateFrame
                Board board = new Board(isHumanVsAI); // Pass true for Human vs AI
                board.setBackground(Color.BLACK);
                alternateFrame.add(board);

                menu.setVisible(false); // Hide menu
                alternateFrame.setVisible(true); // Show Human vs AI game
            }
        });

        // Add buttons to menu layout
        JPanel menuPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        menuPanel.add(humanVsHumanButton);
        //TODO: reable the button for the ai and human match
//        menuPanel.add(humanVsAiButton);
        menu.add(menuPanel);

        // Show the menu initially
        menu.setVisible(true);
    }
}