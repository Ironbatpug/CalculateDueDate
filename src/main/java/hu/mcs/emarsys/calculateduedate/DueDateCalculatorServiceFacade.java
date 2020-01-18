package hu.mcs.emarsys.calculateduedate;

import java.time.LocalDateTime;

public interface DueDateCalculatorServiceFacade {

	/**
	 * 
	 * Calculate the due date for a starting date
	 * 
	 * @param startDate 		the starting date of the calculation
	 * @param turnAroundInHours how many hours will it be
	 * @return 					the date when the due date expire
	 * @throws NotWorkingDateTimeError	if the staring day is not valid
	 */
	public LocalDateTime calculateWorkingDate(LocalDateTime startingDate, double turnAround) throws IllegalArgumentException;

}
