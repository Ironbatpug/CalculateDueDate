package hu.mcs.emarsys.calculateduedate.services;

import java.time.LocalDateTime;

public interface DateValidatorService {
	
	/**
	 * Validates a date or time.
	 *
	 * @param date 		the date which need to be validated
	 * @return          true if the date is valid, otherwise false
	 */
	
	public boolean validateDateTime(LocalDateTime date);

}
