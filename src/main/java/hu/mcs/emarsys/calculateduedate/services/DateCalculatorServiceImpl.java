package hu.mcs.emarsys.calculateduedate.services;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.inject.Singleton;

@Singleton
public class DateCalculatorServiceImpl implements DateCalculatorService {
	private final LocalTime endOfWorkingDay = LocalTime.parse("17:00");

	@Override
	public LocalDateTime calculateDueDateByHours(LocalDateTime startDate, double turnAroundInHours) {
		return recursive(startDate, (long)(turnAroundInHours * 60));
	}

	private LocalDateTime recursive(LocalDateTime date, long turnAround) {
		if (isWorkingDay(date)) return recursive(getNextDay(date), turnAround);

		int minutesOfTheDay = minutesOfTheDay(date);
		if (turnAround <= minutesOfTheDay) return date.plusMinutes(turnAround);
	
		return recursive(getNextDay(date), turnAround - minutesOfTheDay);
	}
	
	private int minutesOfTheDay(LocalDateTime date) {
		return ((endOfWorkingDay.getHour() * 60) + endOfWorkingDay.getMinute()) - ((date.getHour() * 60) + date.getMinute());
	}

	private LocalDateTime getNextDay(LocalDateTime date) {
		return date.plusDays(1).with(LocalTime.of(9, 0));
	}

	public boolean isWorkingDay(LocalDateTime date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

}
