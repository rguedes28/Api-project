package br.com.restassuredapitesting.tests.booking.payloads;

import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import org.json.JSONObject;

@Feature("Festure - Payloads")
public class BookingPayloads {

    //pauload para alterar booking
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

    //Payload para inserir booking
    public static JSONObject payloadInsertBooking() {

        Faker faker = new Faker();

        JSONObject payloadist = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2020-05-21");
        bookingDates.put("checkout", "2021-05-21");

        payloadist.put("firstname", faker.dog().name());
        payloadist.put("lastname", faker.funnyName().name());
        payloadist.put("totalprice", 111);
        payloadist.put("depositpaid", true);
        payloadist.put("bookingdates", bookingDates);
        payloadist.put("additionalneeds", "Breakfast");

        return payloadist;
    }

    //Payload com itens a mais
    public static JSONObject payloadMoreItensBooking() {
        JSONObject payloadadd = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payloadadd.put("firstname", "Jorge");
        payloadadd.put("lastname", "Miguel");
        payloadadd.put("totalprice", 111);
        payloadadd.put("depositpaid", true);
        payloadadd.put("bookingdates", bookingDates);
        payloadadd.put("additionalneeds", "Breakfast");
        payloadadd.put("number roons", 2);
        payloadadd.put("carrental", "yes");

        return payloadadd;
    }

    //Payload invalido
    public static JSONObject payloadInvalidBooking() {
        JSONObject payloadiv = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        payloadiv.put("bookingdates", bookingDates);

        return payloadiv;
    }

}