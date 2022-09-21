package edu.lernia.labb4;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class TollFeeCalculator {

    public TollFeeCalculator(String inputFile) {
        try {
            Scanner sc = new Scanner(new File(inputFile));
            String[] dateStrings = sc.nextLine().split(", ");
            LocalDateTime[] dates = new LocalDateTime[dateStrings.length];
            for(int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            System.out.println("The total fee for the inputfile is " + getTotalFeeCost(dates));
        } catch(IOException e) {
            System.err.println("Could not read file " + new File(inputFile).getAbsolutePath());
        }
    }

    public static int getTotalFeeCost(LocalDateTime[] dates) {
        int totalFee = 0;
        LocalDateTime intervalStart = dates[0];
        for(LocalDateTime date: dates) {
            System.out.println(date.toString());
            long diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);

            if(diffInMinutes > 60) {
                totalFee += getTollFeePerPassing(intervalStart, date);
                intervalStart = date;
            }
        }
        totalFee += getTollFeePerPassing(intervalStart, dates[dates.length - 1]);
        return Math.min(totalFee, 60);
    }

    
    public static int getTollFeePerPassing(LocalDateTime dates, LocalDateTime date) {
		int hour = dates.getHour();
		int minute = dates.getMinute();
		
		if(isTollFreeDate(dates)) {
			return 0;
		}
		if (hour <= 5 && minute <= 59 || hour >= 18 && minute >= 30) {
			return 0;
		} if (hour == 6 && minute >= 0 && hour == 6 && minute <= 29) {
			return 8;
		} if (hour == 6 && minute >= 30 && hour == 6 && minute <= 59) {
			return 13;
		} if (hour == 7 && minute >= 0 && hour == 7 && minute <= 59) {
			return 18;
		} if (hour == 8 && minute >= 0 && hour == 8 && minute <= 29) {
			return 13;
		} if (hour <= 14 && minute <= 59 && hour >= 8 && minute >= 0) {
			return 8;
		} if (hour == 15 && minute >= 0 && hour == 15 && minute <= 29) {
			return 13;
		} if (hour >= 15 && minute >= 30 && hour <= 16 && minute <= 59) {
			return 18;
		} if (hour == 17 && minute >= 0 && hour == 17 && minute <= 59) {
			return 13;
		} if (hour == 18 && minute >= 0 && hour == 18 && minute <= 29) {
			return 8;
		} else {
			return 0;
		}
    }
    
    public int test(int a, int b) {
    	return 1 + 2;
    }
    

    public static boolean isTollFreeDate(LocalDateTime date) {
    	if(date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7 || date.getMonth().getValue() == 7) {
            return true;
        } else {
        	return false;
        }
    }

    public static void main(String[] args) {
        new TollFeeCalculator("src/test/resources/Lab4.txt");
    }
}