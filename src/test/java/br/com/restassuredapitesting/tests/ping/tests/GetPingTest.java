package br.com.restassuredapitesting.tests.ping.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.ping.requests.GetPingRequest;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;


public class GetPingTest extends BaseTest {

    GetPingRequest getPingRequest = new GetPingRequest();

    @Test
    @Category({AllTests.class})
   public void validaApiOnline(){
       getPingRequest.pingReturnApi()
            .then()
            .statusCode(201);

    }


}
