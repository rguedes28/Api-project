package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.tests.booking.tests.GetBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.AccptanceTests.class)
@Suite.SuiteClasses({
        AccptanceTests.class,
        GetBookingTest.class


})
public class AccptanceTests {
}
