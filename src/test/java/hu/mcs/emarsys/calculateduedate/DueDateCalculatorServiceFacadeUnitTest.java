package hu.mcs.emarsys.calculateduedate;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import javax.inject.Singleton;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.BeforeClass;
import org.junit.Test;

@Singleton
public class DueDateCalculatorServiceFacadeUnitTest {


	private static DueDateCalculatorServiceFacade dueDateCalculatorServiceFacade;

	@BeforeClass
	public static void setDueDateCalculatorFacade() {
		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		dueDateCalculatorServiceFacade = container.select(DueDateCalculatorServiceFacade.class).get();
		container.shutdown();
	}

	@Test
	public void assertOneDay() throws IllegalArgumentException {
		LocalDateTime testDate = LocalDateTime.of(2020, 1, 20, 10, 00);
		LocalDateTime testResult = LocalDateTime.of(2020, 1, 21, 10, 00);

		assertTrue(dueDateCalculatorServiceFacade.calculateWorkingDate(testDate, 8).isEqual(testResult));
	}

	@Test
	public void assertWrongDay() {
		LocalDateTime testDate = LocalDateTime.of(2020, 1, 18, 10, 23);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			dueDateCalculatorServiceFacade.calculateWorkingDate(testDate, 8);
		});

		String expectedMessage = "The specified date is not a working date or is it not in the working hours.";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
