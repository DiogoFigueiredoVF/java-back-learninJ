package oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyFlopPannel extends JPanel implements KeyListener {
    // ===== SPRITES ======
    private Image birdImg;
    private Image pipeImg;

    // ===== GAME STATE =====
    private enum GameState { START, PLAYING, GAMEOVER }
    private GameState state = GameState.START;
    private String playerName;

    // ===== BIRD POSITION =====
    private double y = 100;
    private double velocity = 0;
    private double gravity = 0.8;

    // ===== SCORE =====
    private int score = 0;

    // ===== PIPES =====
    private ArrayList<Pipe> pipes = new ArrayList<>();

    public FlappyFlopPannel(String name) {

        this.playerName = name;
        pipeImg = new ImageIcon(getClass().getResource("/oop/pipes.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("/oop/flappyflo.png")).getImage();
        setFocusable(true);
        addKeyListener(this);

        // initial pipes
        pipes.add(new Pipe(400, 200));
        pipes.add(new Pipe(650, 150));
        pipes.add(new Pipe(900, 250));

        new Thread(() -> {
            while (true) {

                if (state == GameState.PLAYING) {

                    // physics
                    velocity += gravity;
                    y += velocity;

                    // move pipes
                    for (Pipe p : pipes) {
                        p.x -= 2;
                    }

                    // remove old pipes
                    pipes.removeIf(p -> p.x < -100);

                    // spawn new pipes (FIXED LOCATION)
                    if (pipes.size() < 5) {
                        int lastX = pipes.get(pipes.size() - 1).x;
                        pipes.add(new Pipe(lastX + 250, (int)(Math.random() * 300) + 50));
                    }

                    // scoring
                    for (Pipe p : pipes) {
                        if (!p.scored && p.x + p.width < 100) {
                            score++;
                            p.scored = true;
                        }
                    }

                    checkCollision();
                }

                repaint();

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // ===== DRAW =====
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // BIRD
        g.drawImage(birdImg, 100, (int)y, 32, 32, null);

        // PIPES
        for (Pipe p : pipes) {
            // ==== TOP PIPE ====
            g2.drawImage(
                    pipeImg,
                    p.x,
                    p.gapY,
                    p.width,
                    -p.gapY,   // negative height = flipped
                    null
            );

            // ==== BOTTOM PIPE ====
            g2.drawImage(
                    pipeImg,
                    p.x,
                    p.gapY + p.gap,
                    p.width,
                    600,
                    null
            );

        }

        // SCORE
        if (state == GameState.PLAYING) {
            g.setFont(new Font("Arial", Font.BOLD, 24));

            g.setColor(Color.BLACK);
            g.drawString("Score: " + score, 22, 42);

            g.setColor(Color.WHITE);
            g.drawString("Score: " + score, 20, 40);
        }

        // START SCREEN
        if (state == GameState.START) {
            g.setFont(new Font("Arial", Font.BOLD, 36));

            g.setColor(Color.BLACK);
            g.drawString("FLAPPY FLOP", 72, 202);

            g.setColor(new Color(255, 200, 0));
            g.drawString("FLAPPY FLOP", 70, 200);

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.setColor(Color.BLACK);
            g.drawString("Press SPACE to start", 90, 260);

        }

        // GAME OVER
        if (state == GameState.GAMEOVER) {
            g.setFont(new Font("Arial", Font.BOLD, 36));

            g.setColor(Color.BLACK);
            g.drawString("GAME OVER", 102, 302);

            g.setColor(Color.RED);
            g.drawString("GAME OVER", 100, 300);

            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.setColor(Color.GRAY);
            g.drawString("R = Restart | ESC = Menu", 80, 340);
        }
    }

    // ===== COLLISION =====
    private void checkCollision() {
        for (Pipe p : pipes) {

            int birdX = 100;
            int birdSize = 30;

            boolean xOverlap =
                    birdX + birdSize > p.x &&
                            birdX < p.x + p.width;

            boolean hitTop = y < p.gapY;
            boolean hitBottom = y + birdSize > p.gapY + p.gap;

            if (xOverlap && (hitTop || hitBottom)) {
                state = GameState.GAMEOVER;

                ScoreManager.saveScore(this.playerName, score);
            }
        }
    }

    // ===== INPUT =====
    @Override
    public void keyPressed(KeyEvent e) {

        if (state == GameState.START) {

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                state = GameState.PLAYING;
            }
        }

        else if (state == GameState.PLAYING) {

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                velocity = -10;
            }
        }

        else if (state == GameState.GAMEOVER) {

            if (e.getKeyCode() == KeyEvent.VK_R) {
                resetGame();
            }

            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                goToStartScreen();
            }
        }
    }

    private void resetGame() {
        y = 100;
        velocity = 0;
        score = 0;

        pipes.clear();
        pipes.add(new Pipe(400, 200));
        pipes.add(new Pipe(650, 150));
        pipes.add(new Pipe(900, 250));

        state = GameState.PLAYING;
    }

    private void goToStartScreen() {
        JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
        window.setContentPane(new StartPannel(window));
        window.revalidate();
        window.repaint();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
}