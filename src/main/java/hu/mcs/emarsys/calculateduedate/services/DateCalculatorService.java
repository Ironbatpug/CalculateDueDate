package hu.mcs.emarsys.calculateduedate.services;

import java.time.LocalDateTime;

public interface DateCalculatorService {
	
	/**
	 * 
	 * Calculate the due date for a starting date
	 * 
	 * @param startDate 		the starting date of the calculation
	 * @param turnAroundInHours how many hours will the due be
	 * @return 					the date when the due date expire
	 */
	public LocalDateTime calculateDueDateByHours(LocalDateTime startDate, double turnAroundInHours);

}
