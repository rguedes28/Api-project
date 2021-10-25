package br.com.restassuredapitesting.tests.booking.payloads;

import com.github.javafaker.Faker;
import org.json.JSONObject;

public class BookingPayloads {
    public static JSONObject payloadValidBooking() {
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payload.put("firstname", "Cristiano");
        payload.put("lastname", "Ronaldo");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");

        return payload;


    }

    public static JSONObject payloadInsertBooking() {

        Faker faker = new Faker();
        JSONObject payloadist = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2021-04-14");
        bookingDates.put("checkout", "2021-05-14");

        payloadist.put("firstname", faker.yoda());
        payloadist.put("lastname", faker.hipster());
        payloadist.put("totalprice", 111);
        payloadist.put("depositpaid", true);
        payloadist.put("bookingdates", bookingDates);
        payloadist.put("additionalneeds", "Breakfast");

        return payloadist;


    }
}