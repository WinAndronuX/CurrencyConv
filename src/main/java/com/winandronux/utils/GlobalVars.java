package com.winandronux.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.winandronux.models.Currency;
import com.winandronux.models.SupportedCodesResponse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GlobalVars {

    public static String API_KEY;

    public static DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public static HttpClient httpClient = HttpClient.newHttpClient();

    public static Map<String, Currency> currencies = new HashMap<>();

    public static void init (String apiKey) {
        API_KEY = apiKey;

        decimalFormat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
        readSupportedCodes();
    }

    private static void readSupportedCodes() {
        try {
            var reader = new FileReader(".codes.json");
            var spCodes = gson.fromJson(reader, SupportedCodesResponse.class);
            reader.close();

            var data = spCodes.supportedCodes();
            for (var d : data) {
                var currency = new Currency(d[0], d[1]);
                currencies.put(d[0], currency);
            }

        } catch (FileNotFoundException f) {
            getSupportedCodes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getSupportedCodes() {
        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/" + API_KEY + "/codes"))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            var writer = new FileWriter(".codes.json");
            writer.write(response.body());
            writer.close();

            readSupportedCodes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
