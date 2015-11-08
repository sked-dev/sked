package edu.bsu.sked.model;

import java.time.Period;

public class PeriodStringGenerator {

	private Period period;

	public PeriodStringGenerator(Period period) {
		this.period = period;
	}

	public String getPeriodString() {
		checkPeriodIsPositive();
		if (period.isZero()) {
			return "0 days";
		} else if (period.getYears() == 0 && period.getMonths() == 0) {
			return dayString();
		} else if (period.getYears() == 0) {
			return monthAndDayString();
		} else {
			return yearMonthAndDayString();
		}
	}
	
	private void checkPeriodIsPositive() {
		period = period.normalized();
		if (period.isNegative()) {
			period = period.negated();
		}
	}

	private String yearMonthAndDayString() {
		if (period.getMonths() == 0 && period.getDays() == 0) {
			return yearString();
		} else if (period.getMonths() != 0 && period.getDays() != 0) {
			return yearString() + ", " + monthString() + ", and " + dayString();
		} else if (period.getMonths() != 0) {
			return yearString() + " and " + monthString();
		} else {
			return yearString() + " and " + dayString();
		}
	}

	private String monthAndDayString() {
		String value = monthString();
		if (period.getDays() != 0) {
			value += " and " + dayString();
		}
		return value;
	}

	private String dayString() {
		if (period.getDays() == 1) {
			return "1 day";
		} else {
			return period.getDays() + " days";
		}
	}

	private String monthString() {
		if (period.getMonths() == 1) {
			return "1 month";
		} else {
			return period.getMonths() + " months";
		}
	}

	private String yearString() {
		if (period.getYears() == 1) {
			return "1 year";
		} else {
			return period.getYears() + " years";
		}
	}
}