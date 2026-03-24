package oop;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class StartPannel extends JPanel {

    String name;
    public StartPannel(JFrame window) {

        setLayout(null);
        setBackground(new Color(135, 206, 235)); // nice sky color

        JButton startButton = new JButton("START GAME");
        startButton.setBounds(120, 250, 150, 50);
        JTextField nameField = new JTextField();
        nameField.setBounds(120, 200, 150, 30);
        add(nameField);

        startButton.addActionListener(e -> {

            name = nameField.getText().trim();

            if (name.isEmpty()) name = "Player";
            String name = nameField.getText().trim();
            FlappyFlopPannel game = new FlappyFlopPannel(name);

            window.setContentPane(game);
            window.revalidate();
            window.repaint();

            game.requestFocusInWindow();
        });

        add(startButton);

        JButton scoresButton = getJButton();

        add(scoresButton);

    }

    private JButton getJButton() {
        JButton scoresButton = new JButton("SCORES");
        scoresButton.setBounds(120, 310, 150, 50);

        scoresButton.addActionListener(e -> {

            java.util.List<ScoreEntry> scores = ScoreManager.loadScores();

            if (scores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No scores yet.");
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("🏆 SCORES\n\n");

            for (ScoreEntry s : scores) {
                sb.append(s.name)
                        .append(" - ")
                        .append(s.score)
                        .append("\n");
            }

            JOptionPane.showMessageDialog(
                    this,
                    sb.toString(),
                    "Leaderboard",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
        return scoresButton;
    }
}