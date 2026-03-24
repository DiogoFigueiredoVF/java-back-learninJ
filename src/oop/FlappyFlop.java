package oop;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class FlappyFlop {

    JFrame window;
    FlappyFlopPannel Pannel;

    public static void main(String args[]){
        JFrame window = new JFrame();
        window.setSize(400, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StartPannel startPanel = new StartPannel(window); // your menu screen

        window.setContentPane(startPanel);
        window.setVisible(true);
    }

    public void createwindow(){

        window = new JFrame();
        window.setSize(400, 600);
        window.setTitle("Flappy Bird");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // START SCREEN FIRST
        window.setContentPane(new StartPannel(window));
        }
}