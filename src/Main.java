import ru.courses.exception.LineTooLongException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

            //Обработка чтения файла
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineCount = 0;
                int maxLength = 0;
                int minLength = 1024;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    int length = line.length();
                    if (length > 1024)
                        throw new LineTooLongException("Cтрока "+ lineCount + " длиннее 1024 символов " + line);
                        //throw new RuntimeException("Ошибка: строка длиннее 1024 символов");
                    if (length > maxLength)
                        maxLength = length;
                    if (length < minLength)
                        minLength = length;
                }
                System.out.println("Общее количество строк в файле: " + lineCount);
                System.out.println("Длина самой длинной строки в файле: " + maxLength);
                System.out.println("Длина самой короткой строки в файле: " + (minLength == 1024 ? 0 : minLength));
            } catch (LineTooLongException ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("Ошибка при чтении файла: " + ex.getMessage());
            }
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