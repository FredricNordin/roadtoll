package edu.lernia.labb4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Tests {

	@Test /* Test if a date is NOT a toll-free date. Test with Wednesday. */
	void testTollFreeDateWorkday() {
		String testWithDate[] = {"2020-06-24 10:00"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertFalse(TollFeeCalculator.isTollFreeDate(date));
	}
	
	@Test /* Test if a date IS a toll-free date. Test with Saturday. */
	void testTollFreeDateWeekend() {
		String testWithDate[] = {"2020-06-27 10:00"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertTrue(TollFeeCalculator.isTollFreeDate(date));
	}
	
	@Test /* Test if a date IS a toll-free date. Test with Wednesday in July. */
	void testTollFreeDateJuly() {
		String testWithDate[] = {"2020-07-08 10:00"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertTrue(TollFeeCalculator.isTollFreeDate(date));
	}
	
	@Test /* Test a time between 06:00 - 06:29. Expect cost of 8:- */
	void testGetTollFeePerPassing1() {
		String testWithDate[] = {"2020-06-08 06:15"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(8, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 06:30 - 06:59. Expect cost of 13:- */
	void testGetTollFeePerPassing2() {
		String testWithDate[] = {"2020-06-08 06:40"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(13, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 07:00 - 07:59. Expect cost of 18:- */
	void testGetTollFeePerPassing3() {
		String testWithDate[] = {"2020-06-08 07:30"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(18, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 08:00 - 08:29. Expect cost of 13:- */
	void testGetTollFeePerPassing4() {
		String testWithDate[] = {"2020-06-08 08:05"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(13, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 08:30 - 14:59. Expect cost of 8:- */
	void testGetTollFeePerPassing5() {
		String testWithDate[] = {"2020-06-08 08:30"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(8, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 15:00 - 15:29. Expect cost of 13:- */
	void testGetTollFeePerPassing6() {
		String testWithDate[] = {"2020-06-08 15:10"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(13, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 15:30 - 16:59. Expect cost of 18:- */
	void testGetTollFeePerPassing7() {
		String testWithDate[] = {"2020-06-08 16:00"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(18, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 17:00 - 17:59. Expect cost of 13:- */
	void testGetTollFeePerPassing8() {
		String testWithDate[] = {"2020-06-08 17:01"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(13, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 18:00 - 18:29. Expect cost of 8:- */
	void testGetTollFeePerPassing9() {
		String testWithDate[] = {"2020-06-08 18:28"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(8, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test a time between 18:30 - 05:59. Expect cost of 0:- */
	void testGetTollFeePerPassing10() {
		String testWithDate[] = {"2020-06-08 03:30"};
		LocalDateTime date = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertEquals(0, TollFeeCalculator.getTollFeePerPassing(date, date));
	}
	
	@Test /* Test if the total fee will NOT exceed 60:- */
	void testGetTotalFeeCost() {
		try {
			String[] testWithDates = {
					"2020-06-08 06:00", 
					"2020-06-08 07:00", 
					"2020-06-08 08:00", 
					"2020-06-08 09:00", 
					"2020-06-08 10:00", 
					"2020-06-08 11:00", 
					"2020-06-08 12:00", 
					"2020-06-08 13:00", 
					"2020-06-08 14:00", 
					"2020-06-08 15:00", 
					"2020-06-08 16:00", 
					"2020-06-08 17:00", 
					"2020-06-08 18:00"
			};
		LocalDateTime[] date = new LocalDateTime[testWithDates.length];
        for(int i = 0; i < date.length; i++) {
            date[i] = LocalDateTime.parse(testWithDates[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		} 
        assertEquals(60, TollFeeCalculator.getTotalFeeCost(date));
		} finally {
			System.out.println("All the dates above were sent.");
		}
   }
}
