
package oop;

public class My_First_Class {

    private int counter;

    //------------------------------main---------------------------------------------
    //------------------------------main---------------------------------------------
    public static void main(String[] args) {

        My_First_Class instance = new My_First_Class();

        String result = instance.addUser(null, "DiogoVelos");
        result = instance.addUser(result, "ZeMaria");
        instance.writetext(result);
        System.out.println("Counter: " + instance.counter);
    }

    //------------------------------writetext---------------------------------------
    //------------------------------writetext---------------------------------------
    public void writetext(String names) {
        System.out.println("Hello " + names + "!");
    }

    //------------------------------addUser-----------------------------------------
    //------------------------------addUser-----------------------------------------
    public String addUser(String existingName, String newName) {
        counter ++;
        if (existingName == null) {
            return newName;
        }

        return existingName + " " + newName;
    }
}
