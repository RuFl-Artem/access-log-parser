package ru.courses;

import ru.courses.exception.LineTooLongException;
import ru.courses.measurement.Statistics;
import ru.courses.parse.LogEntry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

            Statistics statistics = new Statistics();
            //Обработка чтения файла
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineCount = 0;
                int googleBotCount = 0;
                int yandexBotCount = 0;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    int length = line.length();
                    //Выбрасываем исключение если строка длиннее 1024 символов
                    if (length > 1024)
                        throw new LineTooLongException("Cтрока " + lineCount + " длиннее 1024 символов " + line);
                    LogEntry logEntry = new LogEntry(line);
                    statistics.addEntry(logEntry);
                    //В методе извлекаем userAgent
                    String userAgent = extractUserAgent(line);
                    if (userAgent != null) {
                        if (userAgent.equalsIgnoreCase("Googlebot"))
                            googleBotCount++;
                        if (userAgent.equalsIgnoreCase("YandexBot"))
                            yandexBotCount++;
                    }
                }
                System.out.println("Общее количество строк в файле: " + lineCount);
                System.out.println("Общий объем трафика: " + statistics.getTotalTraffic());
                System.out.println("Средний объём трафика за час: " + statistics.getTrafficRate());

                System.out.println("Статистика по операционным системам: " + statistics.getOSStatistics());
                System.out.println("Статистика по браузерам: " + statistics.getBrowserStatistics());

                System.out.println("Среднее количество посещений за час: " + statistics.getAverageVisitsHour());
                System.out.println("Среднее количество ошибочных запросов за час: " + statistics.getAverageInvalidRequestHour());
                System.out.println("Средняя посещаемость одним пользователем: " + statistics.getAverageVisitsUser());

                System.out.println("Количество запросов от Googlebot: " + googleBotCount);
                System.out.println("Количество запросов от YandexBot: " + yandexBotCount);
                double googleBotShare = (double) googleBotCount / lineCount * 100;
                double yandexBotShare = (double) yandexBotCount / lineCount * 100;
                System.out.printf("Доля запросов от Googlebot: %.2f%%\n", googleBotShare);
                System.out.printf("Доля запросов от YandexBot: %.2f%%\n", yandexBotShare);
            } catch (LineTooLongException ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            } catch (IOException ex) {
                System.out.println("Ошибка при чтении файла: " + ex.getMessage());
            }
        }
    }

    private static String extractUserAgent(String line) {
        Matcher matcher = Pattern.compile("\\(([^()]*?)\\)[^()]*$").matcher(line);
        if (matcher.find()) {
            String userAgentFragment = matcher.group(1).trim();
            String[] parts = userAgentFragment.split(";");
            if (parts.length >= 2) {
                String fragment = parts[1].trim();
                return fragment.split("/")[0].trim();
            }
        }
        return null;
    }
}