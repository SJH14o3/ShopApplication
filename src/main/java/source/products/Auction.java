package source.products;

import source.exceptions.PassedDeadLineException;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Auction {
    private final int id;
    private final String title;
    private final double startingBid;
    private double highestBid;
    private final String deadline; //example: 202309121650: 12th September 2023 16:50
    private final String imageAddress;

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public double getStartingBid() {
        return startingBid;
    }
    public double getHighestBid() {
        return highestBid;
    }
    public String getDeadline() {
        return deadline;
    }
    public String getImageAddress() {
        return imageAddress;
    }
    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }
    public String calculateDeadLine() throws PassedDeadLineException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String now = dtf.format(LocalDateTime.now());
        StringBuilder stringBuilder = new StringBuilder();
        int year = Integer.parseInt(deadline.substring(0, 4)) - Integer.parseInt(now.substring(0,4));
        int month = Integer.parseInt(deadline.substring(4, 6)) - Integer.parseInt(now.substring(4,6));
        int day = Integer.parseInt(deadline.substring(6, 8)) - Integer.parseInt(now.substring(6,8));
        int hour = Integer.parseInt(deadline.substring(8, 10)) - Integer.parseInt(now.substring(8,10));
        int minute = Integer.parseInt(deadline.substring(10, 12)) - Integer.parseInt(now.substring(10,12));
        if (minute < 0) {
            hour--;
            minute += 60;
        }
        if (hour < 0) {
            day--;
            hour += 24;
        }
        if (day < 0) {
            month--;
            day += 30; //I was lazy to calculate months without 30 days.
        }
        if (month < 0) {
            year--;
            month += 12;
        }
        if (year < 0) {
            throw new PassedDeadLineException("Passed Dead line:\nNow: " + now + "\nDead Line: " + deadline);
        }
        if (year != 0) {
            stringBuilder.append(year).append(" year");
            if (year != 1) {
                stringBuilder.append("s");
            }
            stringBuilder.append(" ");
        }
        if (month != 0) {
            stringBuilder.append(month).append(" month");
            if (month != 1) {
                stringBuilder.append("s");
            }
            stringBuilder.append(" ");
        }
        if (day != 0) {
            stringBuilder.append(day).append(" day");
            if (day != 1) {
                stringBuilder.append("s");
            }
            stringBuilder.append(" ");
        }
        if (hour != 0) {
            stringBuilder.append(hour).append(" hour");
            if (hour != 1) {
                stringBuilder.append("s");
            }
            stringBuilder.append(" ");
        }
        if (minute != 0) {
            stringBuilder.append(minute).append(" minute");
            if (minute != 1) {
                stringBuilder.append("s");
            }
        }
        return stringBuilder.toString();
    }
    public Auction(int id, String title, double startingBid, double highestBid, String deadline, String imageAddress) {
        this.id = id;
        this.title = title;
        this.startingBid = startingBid;
        this.highestBid = highestBid;
        this.deadline = deadline;
        this.imageAddress = imageAddress;
    }
}
