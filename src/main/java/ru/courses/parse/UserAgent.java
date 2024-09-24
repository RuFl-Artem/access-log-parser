package ru.courses.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgent {
    private final List<String> operatingSystem;
    private final List<String> browser;

    public UserAgent(String userAgent) {
        this.operatingSystem = new ArrayList<>();
        this.browser = new ArrayList<>();
        extractOS(userAgent);
        extractBrowsers(userAgent);
    }

    private void extractOS(String userAgent) {
        Matcher osMatcher = Pattern.compile("\\(([^)]+)\\)").matcher(userAgent);
        if (osMatcher.find()) {
            String osPart = osMatcher.group(1).trim();
            if (osPart.contains("Windows 98")) {
                operatingSystem.add("Windows 98");
                return;
            } else if (osPart.contains("Windows NT")) {
                operatingSystem.add("Windows NT");
                return;
            } else if (osPart.contains("Windows")) {
                operatingSystem.add("Windows");
                return;
            } else if (osPart.contains("Macintosh")
                    && osPart.contains("Mac OS X")) {
                operatingSystem.add("Mac OS");
                return;
            } else if (osPart.contains("X11; Ubuntu; Linux")) {
                operatingSystem.add("Ubuntu");
                return;
            } else if (osPart.contains("Linux")
                    && osPart.contains("Android")) {
                operatingSystem.add("Android");
                return;
            } else if (osPart.contains("Linux")) {
                operatingSystem.add("Linux");
                return;
            } else if (osPart.contains("iPhone OS")) {
                operatingSystem.add("iPhone OS");
                return;
            }
            operatingSystem.add("Other");
        }
    }

    private void extractBrowsers(String userAgent) {
        Matcher browserMatcher = Pattern.compile("\\)[^()]*\\(([^()]+)\\) ([^;()]+)").matcher(userAgent);
        if (browserMatcher.find()) {
            String browserPart = browserMatcher.group(2).trim();
            if (browserPart.contains("Chrome")
                    && browserPart.contains("Safari")
                    && browserPart.contains("Edge")) {
                browser.add("Microsoft Edge");
                return;
            } else if (browserPart.contains("Chrome")
                    && browserPart.contains("Safari")) {
                browser.add("Chrome");
                return;
            } else if (browserPart.contains("Chrome")
                    && browserPart.contains("Safari")
                    && browserPart.contains("Mobile")) {
                browser.add("Chrome Mobile");
                return;
            } else if (browserPart.contains("Gecko")
                    && browserPart.contains("Firefox")) {
                browser.add("Firefox");
                return;
            } else if (browserPart.contains("Safari")) {
                browser.add("Safari");
                return;
            } else if (browserPart.contains("Opera")) {
                browser.add("Opera");
                return;
            } else if (browserPart.contains("OPiOS")) {
                browser.add("Opera Mini");
                return;
            } else if (browserPart.contains("MSIE")) {
                browser.add("Windows Internet Explorer");
                return;
            }
            browser.add("Other");
        }
    }
    //Метод для проверки, в UserAgent информации о боте
    public static boolean isBot(String userAgent) {
        return userAgent != null && userAgent.toLowerCase().contains("bot");
    }

    public List<String> getOperatingSystem() {
        return operatingSystem;
    }

    public List<String> getBrowser() {
        return browser;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "operatingSystem='" + operatingSystem + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }
}
