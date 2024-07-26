import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Счетчик для подсчета количества верно указанных файлов
        int fileCount = 0;
        //В бесконечном цикле while запрашиваем путь к файлу
        while (true) {
            //Запрашиваем путь к файлу в консоли
            System.out.print("Введите путь к файлу: ");
            String path = new Scanner(System.in).nextLine();
            //Создаем объект File для проверки существования файла
            File file = new File(path);
            //Создаем переменную для определения существования файла
            boolean fileExists = file.exists();
            //Создаем переменную для определения является ли указанный путь к папке
            boolean isDirectory = file.isDirectory();
            //Проверяем, существует ли файл или папка
            if (!fileExists) {
                System.out.println("Файл не найден");
                continue;
            }
            //Проверяем, является ли указанный путь к папке
            if (isDirectory) {
                System.out.println("Указанный путь является директорией");
                continue;
            }
            //путь ведёт к существующему файлу, выводим в консоль сообщение
            System.out.println("Путь указан верно");
            //Увеличиваем счетчик и выводим общее количество верно указанных путей к файлам
            fileCount++;
            System.out.println("Это файл номер " + fileCount);
        }
    }

    //метод для вычисления суммы двух чисел
    public static int sum(int x, int y) {
        return x + y;
    }

    //метод для вычисления разности двух чисел
    public static int diff(int x, int y) {
        return x - y;
    }

    //метод для вычисления произведения двух чисел
    public static int multiply(int x, int y) {
        return x * y;
    }

    //частное двух чисел с приведением к типу double
    public static double quotient(int x, int y) {
        return (double) x / y;
    }
}