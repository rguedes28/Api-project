package br.com.restassuredapitesting.tests.booking.payloads;

import com.github.javafaker.Faker;
import org.json.JSONObject;

import java.util.Random;

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

        Random random = new Random();
        int mouth = random.nextInt(12);
        int days = random.nextInt(30);

        bookingDates.put("checkin", "2020-05-21");
        bookingDates.put("checkout", "2021-05-21");

        //bookingDates.put("checkin", "2020-"+mouth+"-"+days);
        //bookingDates.put("checkout", "2021-"+mouth+"-"+days);

        payloadist.put("firstname", faker.dog().name());
        payloadist.put("lastname", faker.funnyName().name());
        payloadist.put("totalprice", 111);
        payloadist.put("depositpaid", true);
        payloadist.put("bookingdates", bookingDates);
        payloadist.put("additionalneeds", "Breakfast");

        return payloadist;


    }
}