package hu.mcs.emarsys.calculateduedate.services;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.enterprise.inject.Default;
import javax.inject.Singleton;

@Default
@Singleton

public class DateTimeValidator implements DateValidatorService{
	/**
	 * Start of the working day.
	 */
	private final LocalTime startOfWorkingDay = LocalTime.parse("09:00");
	
	/**
	 * End of the working day.
	 */
	private final LocalTime endOfWorkingDay = LocalTime.parse("17:00");
		
	/**
	 * Validates a date time.
	 *
	 * @param date 		the date which need to be validated
	 * @return          true if the time is working day and working hour as well, otherwise false
	 */
	@Override
	public boolean validateDateTime(LocalDateTime date) {
		return validateDate(date) && validateTime(date);
	}
	
	/**
	 * Validates a date.
	 *
	 * @param date 		the date which need to be validated
	 * @return          true if the time is working day, otherwise false
	 */
	private boolean validateDate(LocalDateTime date) {
		return !(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY);
	}
	
	/**
	 * Validates a time.
	 *
	 * @param date 		the date which need to be validated
	 * @return          true if the time is working hour, otherwise false
	 */
	private boolean validateTime(LocalDateTime date) {
		LocalTime localTime = LocalTime.of(date.getHour(), date.getMinute());
		return localTime.isAfter(startOfWorkingDay) && localTime.isBefore(endOfWorkingDay);
	}

}
