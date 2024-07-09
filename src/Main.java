import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите число №1 = ");
        int number1 = new Scanner(System.in).nextInt();
        System.out.print("Введите число №2 = ");
        int number2 = new Scanner(System.in).nextInt();
        System.out.println("Сумма чисел = " + sum(number1, number2));
        System.out.println("Разность чисел = " + diff(number1, number2));
        System.out.println("Произведение чисел = " + multiply(number1, number2));
        System.out.println("Частное чисел = " + quotient(number1, number2));
    }

    public static int sum(int x, int y) {
        return x + y;
    }
    public static int diff(int x, int y) {
        return x - y;
    }
    public static int multiply(int x, int y) {
        return x * y;
    }
    public static double quotient(int x, int y) {
        return (double) x / y;
    }
}