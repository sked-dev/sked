package edu.bsu.sked.model;

import java.time.Period;
import java.time.LocalDate;

import org.junit.*;

public class PeriodStringGeneratorTest {
	
	@Test
	public void testSameDateIsZeroDays() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2015, 1, 1));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("0 days", g.getPeriodString());
	}
	
	@Test
	public void testSingularDay() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2015, 1, 2));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("1 day", g.getPeriodString());
	}
	
	@Test
	public void testMultipleDays() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2015, 1, 6));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("5 days", g.getPeriodString());
	}
	
	@Test
	public void testOneMonthOnly() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2015, 2, 1));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("1 month", g.getPeriodString());
	}
	
	@Test
	public void testMultipleMonthsOnly() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2015, 6, 1));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("5 months", g.getPeriodString());
	}
	
	@Test
	public void testOneMonthAndOneDay() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2015, 2, 2));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("1 month and 1 day", g.getPeriodString());
	}
	
	@Test
	public void testOneYearOnly() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2016, 1, 1));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("1 year", g.getPeriodString());
	}
	
	@Test
	public void testMultipleYearsOnly() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2019, 1, 1));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("4 years", g.getPeriodString());
	}
	
	@Test
	public void testOneYearOneMonthAndOneDay() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2016, 2, 2));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("1 year, 1 month, and 1 day", g.getPeriodString());
	}
	
	@Test
	public void testOneYearAndOneMonth() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2016, 2, 1));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("1 year and 1 month", g.getPeriodString());
	}
	
	@Test
	public void testOneYearAndOneDay() {
		Period period = LocalDate.of(2015, 1, 1).until(LocalDate.of(2016, 1, 2));
		PeriodStringGenerator g = new PeriodStringGenerator(period);
		Assert.assertEquals("1 year and 1 day", g.getPeriodString());
	}
	
	@Test
	public void testOrderDoesNotMatter() {
		Period controlPeriod = LocalDate.of(2015, 1, 1).until(LocalDate.of(2016, 2, 2));
		Period testPeriod = LocalDate.of(2016, 2, 2).until(LocalDate.of(2015, 1, 1));
		PeriodStringGenerator controlGenerator = new PeriodStringGenerator(controlPeriod);
		PeriodStringGenerator testGenerator = new PeriodStringGenerator(testPeriod);
		String control = controlGenerator.getPeriodString();
		String test = testGenerator.getPeriodString();
		Assert.assertEquals(control, test);
	}
	
}
