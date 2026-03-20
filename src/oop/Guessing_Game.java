package oop;
import java.util.Random;
import java.util.Scanner;
public class Guessing_Game {

    private int guess_number;
    private int random_num;

    public static void main(String[] args){

        Guessing_Game game = new Guessing_Game();
        game.Insert_Number();
        game.Generate_Number();
        game.Compare_Numbers();
    }

    private void Compare_Numbers() {

        if (random_num == guess_number) {
            System.out.println("ÉS O MAIOR DESTA MERDA TODA");
        } else{ System.out.println("Pede p cagar e sai"); }

    }

    private void Generate_Number() {

        Random random = new Random();
        random_num = random.nextInt(10) + 1;
    }

    private void Insert_Number(){
        System.out.println("Inseres o numero ou rebento-te a boca. JÁ:");
        Scanner scanner = new Scanner(System.in);
        guess_number = scanner.nextInt();
    }

}
