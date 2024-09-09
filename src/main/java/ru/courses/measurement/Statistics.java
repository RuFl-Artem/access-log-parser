package ru.courses.measurement;

import ru.courses.parse.LogEntry;

import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = null;
        this.maxTime = null;
    }

    public void addEntry(LogEntry entry) {
        totalTraffic += entry.getResponseSize();
        if (minTime == null || entry.getDateTime().isBefore(minTime))
            minTime = entry.getDateTime();
        if (maxTime == null || entry.getDateTime().isAfter(maxTime))
            maxTime = entry.getDateTime();
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
}
