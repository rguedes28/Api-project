package br.com.restassuredapitesting.runners;

import br.com.restassuredapitesting.suites.SchemaTests;
import br.com.restassuredapitesting.tests.booking.tests.GetBookingTest;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(SchemaTests.class)
@Suite.SuiteClasses({
        Schematests.class,
        GetBookingTest.class


})
public class Schematests {

}
