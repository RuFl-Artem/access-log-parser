package ru.courses.measurement;

import ru.courses.parse.LogEntry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;
    private HashSet<String> addres;
    private HashMap<String, Integer> osStats;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
        this.addres = new HashSet<>();
        this.osStats = new HashMap<>();
    }

    public void addEntry(LogEntry entry) {
        totalTraffic += entry.getResponseSize();
        if (minTime == null || entry.getDateTime().isBefore(minTime))
            minTime = entry.getDateTime();
        if (maxTime == null || entry.getDateTime().isAfter(maxTime))
            maxTime = entry.getDateTime();
        if (entry.getResponseCode() == 200)
            addres.add(entry.getPath());
        if (entry.getUserAgent() != null) {
            for (String os : entry.getUserAgent().getOperatingSystem())
                //Обновляем статистику, если уже есть запись увеличиваем значение на 1, иначе добавляем запись с 1
                osStats.put(os, osStats.getOrDefault(os, 0) + 1);
        }
    }

    public double getTrafficRate() {
        if (minTime == null || maxTime == null)
            return 0;
        long hours = Duration.between(minTime, maxTime).toHours();
        if (hours == 0)
            return totalTraffic;
        return (double) totalTraffic / hours;
    }

    public long getTotalTraffic() {
        return totalTraffic;
    }

    public LocalDateTime getMinTime() {
        return minTime;
    }

    public LocalDateTime getMaxTime() {
        return maxTime;
    }
    //Метод для получения существующих страниц
    public HashSet<String> getExistingAddres(){
        return addres;
    }
    //Метод для получения статистики ОС
    public HashMap<String, Double> getOSStatistics(){
        //Создаем новую карту osStatsPercentage, которая будет хранить долю каждой операционной системы
        HashMap<String, Double> osStatsPercentage = new HashMap<>();
        //Подсчет общего количества ОС
        int totalOsCount = osStats.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        /*Проходим по всем записям в карте osStats. Для каждой записи вычисляем долю,
        разделив количество данной ОС на общее количество всех ОС (totalOsCount).
        Результат добавляем в новую карту osStatsPercentage */
        for (Map.Entry<String, Integer> entry : osStats.entrySet()){
            String os = entry.getKey();
            int count = entry.getValue();
            //Вычисление доли
            osStatsPercentage.put(os, (double) count / totalOsCount);
        }
        return osStatsPercentage;
    }
}
