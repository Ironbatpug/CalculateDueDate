package hu.mcs.emarsys.calculateduedate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.BeforeClass;
import org.junit.Test;

import hu.mcs.emarsys.calculateduedate.services.DateValidatorService;

public class DateTimeValidatorUnitTest {
	private static DateValidatorService workingdateTimeValidator;

	@BeforeClass
	public static void setWorkingDateTimeValidatorUnitTest() {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		workingdateTimeValidator = container.select(DateValidatorService.class).get();
		container.shutdown();
	}

	@Test
	public void assertWeekEnd() {
		LocalDateTime testWeekendDay = LocalDateTime.of(2020, 1, 18, 10, 23);
		assertFalse(workingdateTimeValidator.validateDateTime(testWeekendDay));
	}

	@Test
	public void assertWeekEndAfterWork() {
		LocalDateTime testWeekendDay = LocalDateTime.of(2020, 1, 18, 22, 23);
		assertFalse(workingdateTimeValidator.validateDateTime(testWeekendDay));
	}

	@Test
	public void assertWeekDay() {
		LocalDateTime testWeekdDay = LocalDateTime.of(2020, 1, 22, 10, 23);
		assertTrue(workingdateTimeValidator.validateDateTime(testWeekdDay));
	}

	@Test
	public void assertBeforeWorkingHour() {
		LocalDateTime testBeforeWorkingHour = LocalDateTime.of(2020, 1, 22, 5, 23);

		assertFalse(workingdateTimeValidator.validateDateTime(testBeforeWorkingHour));
	}

	@Test
	public void assertAfterWorkingHour() {
		LocalDateTime testAfterWorkingHour = LocalDateTime.of(2020, 1, 22, 22, 23);
		assertFalse(workingdateTimeValidator.validateDateTime(testAfterWorkingHour));
	}

}
