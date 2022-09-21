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
		LocalDateTime dates = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertTrue(TollFeeCalculator.isTollFreeDate(dates));
	}
	
	@Test /* Test if a date IS a toll-free date. Test with Wednesday in July. */
	void testTollFreeDateJuly() {
		String testWithDate[] = {"2020-07-08 10:00"};
		LocalDateTime dates = LocalDateTime.parse(testWithDate[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		assertTrue(TollFeeCalculator.isTollFreeDate(dates));
	}

}
