package hu.mcs.emarsys.calculateduedate;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.validation.constraints.Positive;

import hu.mcs.emarsys.calculateduedate.services.DateCalculatorService;
import hu.mcs.emarsys.calculateduedate.services.DateValidatorService;

public class DueDateCalculatorServiceFacadeImpl implements hu.mcs.emarsys.calculateduedate.DueDateCalculatorServiceFacade {

	private final DateValidatorService workingDateTimeValidator;
	private final DateCalculatorService dateCalculator;

	@Inject
    public DueDateCalculatorServiceFacadeImpl(DateValidatorService workingDateTimeValidator, DateCalculatorService dateCalculator) {
		this.workingDateTimeValidator = workingDateTimeValidator;
		this.dateCalculator = dateCalculator;
	}

	@Override
	public LocalDateTime calculateWorkingDate(LocalDateTime date, @Positive double turnAround) throws IllegalArgumentException {
		if (!workingDateTimeValidator.validateDateTime(date)) {
			throw new IllegalArgumentException("The specified date is not a working date or is it not in the working hours.");
		}
		return dateCalculator.calculateDueDateByHours(date, turnAround);
	}

}
