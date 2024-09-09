package ru.courses.parse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    public enum HttpMethod {
        GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH
    }

    private final String ipAddr;
    private final LocalDateTime dateTime;
    private final HttpMethod method;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String logLine) {
        String[] parts = logLine.split(" ");
        this.ipAddr = parts[0];
        Matcher dateTime = Pattern.compile("\\[(.*?)\\]").matcher(logLine);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        if (dateTime.find())
            this.dateTime = LocalDateTime.parse(dateTime.group(1), formatter);
        else this.dateTime = null;
        Matcher method = Pattern.compile("\"(\\w+)\\s").matcher(logLine);
        if (method.find())
            this.method = HttpMethod.valueOf(method.group(1));
        else this.method = null;
        Matcher path = Pattern.compile("\"[A-Z]+ ([^ ]+) (HTTP/[0-9.]+|FTP/[0-9.]+)\"").matcher(logLine);
        if (path.find())
            this.path = path.group(1);
        else this.path = null;
        this.responseCode = Integer.parseInt(parts[8]);
        this.responseSize = Integer.parseInt(parts[9]);
        Matcher referer = Pattern.compile("\"([^\"]*)\"[^\"]*\"([^\"]*)\"").matcher(logLine);
        if (referer.find())
            this.referer = referer.group(2);
        else this.referer = null;
        Matcher userAgent = Pattern.compile("\"([^\"]*)\"\\s+\"([^\"]*)\"$").matcher(logLine);
        if (userAgent.find())
            this.userAgent = new UserAgent(userAgent.group(2));
        else this.userAgent = null;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }
}
