package hu.mcs.emarsys.calculateduedate;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.BeforeClass;
import org.junit.Test;

import hu.mcs.emarsys.calculateduedate.services.DateCalculatorService;

public class DateCalculatorServiceUnitTest {

	private static DateCalculatorService dueDateCalculatorServiceImpl;

	@BeforeClass
	public static void setDueDateCalculatorUnitTest() {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		dueDateCalculatorServiceImpl = container.select(DateCalculatorService.class).get();
		container.shutdown();
	}
	
	@Test
	public void assertTwoDayInHour() {
		LocalDateTime testDate = LocalDateTime.of(2020, 1, 20, 10, 23);
		LocalDateTime testResult = LocalDateTime.of(2020, 1, 22, 10, 23);

		assertTrue(dueDateCalculatorServiceImpl.calculateDueDateByHours(testDate, 16).isEqual(testResult));
	}
	
	@Test
	public void assertOneDayInHour() {
		LocalDateTime testDate = LocalDateTime.of(2020, 1, 20, 10, 23);
		LocalDateTime testResult = LocalDateTime.of(2020, 1, 21, 10, 23);

		assertTrue(dueDateCalculatorServiceImpl.calculateDueDateByHours(testDate, 8).isEqual(testResult));
	}
	
	@Test
	public void assertHalfDayMorningInHour() {
		LocalDateTime testDate = LocalDateTime.of(2020, 1, 20, 10, 23);
		LocalDateTime testResult = LocalDateTime.of(2020, 1, 20, 14, 23);

		assertTrue(dueDateCalculatorServiceImpl.calculateDueDateByHours(testDate, 4).isEqual(testResult));
	}
	
	@Test
	public void assertHalfDayAfterNoonInHour() {
		LocalDateTime testDate = LocalDateTime.of(2020, 1, 20, 14, 00);
		LocalDateTime testResult = LocalDateTime.of(2020, 1, 21, 10, 00);

		assertTrue(dueDateCalculatorServiceImpl.calculateDueDateByHours(testDate, 4).isEqual(testResult));
	}
	
	@Test
	public void assertMoreThanAWeekInHour() {
		LocalDateTime testDate = LocalDateTime.of(2020, 1, 20, 11, 30);
		LocalDateTime testResult = LocalDateTime.of(2020, 2, 3, 11, 30);

		assertTrue(dueDateCalculatorServiceImpl.calculateDueDateByHours(testDate, 80).isEqual(testResult));
	}
	
	@Test
	public void assertFridayOnefDayInHour() {
		LocalDateTime testDate = LocalDateTime.of(2020, 1, 17, 11, 30);
		LocalDateTime testResult = LocalDateTime.of(2020, 1, 20, 11, 30);

		assertTrue(dueDateCalculatorServiceImpl.calculateDueDateByHours(testDate, 8).isEqual(testResult));
	}
	
}
