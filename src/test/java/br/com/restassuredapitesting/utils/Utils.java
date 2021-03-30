package br.com.restassuredapitesting.utils;

import org.json.simple.JSONObject;

public class Utils {

    public static JSONObject validPayloadBooking(){
       JSONObject payload = new JSONObject();
       JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout","2019-01-01" );

        payload.put("firstname","Ronaldo");
        payload.put("lastname", "Fenomeno");
        payload.put("totalprice", 111);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Breakfast");


       return payload;
    }

    public static JSONObject invalidPayloadBooking(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2020-03-03");
        bookingDates.put("checkout", "2021-03-03");


        payload.put("firstname", "cwi");
        payload.put("price", 235);
        payload.put("depositpaid", true);
        payload.put("bookingDates", bookingDates);
        payload.put("additionalneeds", "Pool");


        return payload;
    }
    public static JSONObject payloadBookingAlterado(){
        JSONObject payload = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2020-03-03");
        bookingDates.put("checkout", "2021-03-03");

        payload.put("firstname", "cwi");
        payload.put("lastname", "Fenomeno");
        payload.put("price", 235);
        payload.put("depositpaid", true);
        payload.put("bookingDates", bookingDates);
        payload.put("additionalneeds", "Pool");
        payload.put("additionalneeds", "Breakfast");

        return payload;
    }


    public static  String getContractsBasePath(String pack, String contract) {
        return System.getProperty("user.dir")
                + "/src/test/java/br/com/restassuredapitesting/tests/"
                + pack
                + "/contracts/"
                + contract
                + ".json";
    }
}
