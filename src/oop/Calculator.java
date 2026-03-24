package oop;
import java.util.Scanner;
public class Calculator {


    public static void main(String[] args){

        Calculator Calculator = new Calculator(); // isto esta bem?
         Calculator.Insert_num1();
         Calculator.cenas();
         Calculator.Insert_num2();


        Scanner scanner = new Scanner(System.in);

    }
    public void Insert_num1(){
        System.out.println("Mete o primeiro numero caralho:");
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();

        }


    public void cenas(){
        System.out.println("Escolhe o que caralho queres fazer A)+ B)-");   //COMO É QUE METO ISTO EM LINHAS DIFERENTES
        Scanner scanner = new Scanner(System.in);


    }



    public void Insert_num2(){
        System.out.println("Mete o segundo numero caralho:");
        Scanner scanner = new Scanner(System.in);
        int num2 = scanner.nextInt();

    }
    //FAZER A PUTA DA CONTA
}
