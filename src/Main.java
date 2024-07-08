import java.util.Scanner;

public class Main {
    //метод main для себя
    public static void main(String[] args) {
        //System.out.println("Случайное число от 0 до 1: " + Math.random());
        System.out.println("Введите текст и нажмите <Enter>: ");
        String text = new Scanner(System.in).nextLine();
        System.out.println("Длина текста: "+text.length());
    }
    //что ниже то для проверки, метод main для себя
    //задача 0.1
    public static int example(int x, int y){
        return x+y;
    }
    //Курсовой проект
    public static void course1(){
        String text = new Scanner(System.in).nextLine();
        System.out.println("Длина текста: "+text.length());
    }
}