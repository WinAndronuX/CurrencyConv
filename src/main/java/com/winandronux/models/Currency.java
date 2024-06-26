package com.winandronux.models;

import com.winandronux.exceptions.APIException;
import com.winandronux.exceptions.InvalidCurrencyCodeException;
import com.winandronux.utils.GlobalVars;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Currency {
    private final String code;
    private final String name;

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.code + " -> " + this.name;
    }

    private ExchangeRateResponse requestConvert() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/" + GlobalVars.API_KEY + "/latest/" + this.code))
                .build();
        HttpResponse<String> response = GlobalVars.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return GlobalVars.gson.fromJson(response.body(), ExchangeRateResponse.class);
    }

    public String[] convert(double amount, String currencyCodes) {
        var listCurrencyCodes = currencyCodes.split(",");

        var results = new ArrayList<String>();

        try {
            var response = requestConvert();

            if (response.result().equals("success")) {
                var data = response.conversionRates();

                for (var currencyCode : listCurrencyCodes) {

                    var x = data.get(currencyCode);
                    if (x != null) {
                        var targetCurrency = GlobalVars.currencies.get(currencyCode);

                        double convResult = (x * amount);
                        results.add(targetCurrency.getName() + " (" + targetCurrency.getCode() + ")" + " -> " + GlobalVars.decimalFormat.format(convResult));
                    } else {
                        throw new InvalidCurrencyCodeException(currencyCode);
                    }
                }
            } else {
                throw new APIException(response.errorType());
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return results.toArray(new String[0]);
    }

//    public String[] convert(double amount) {
//        var results = new ArrayList<String>();
//
//        try {
//            var response = requestConvert();
//
//            if (response.result().equals("success")) {
//                var data = response.conversionRates();
//
//                for (var key : data.keySet()) {
//                    var arg = data.get(key);
//
//                    if (!key.equals(this.code)) {
//                        double convResult = (arg * amount);
//                        results.add(getName() + " (" + getCode() + ")" + " -> " + GlobalVars.decimalFormat.format(convResult));
//                    }
//                }
//            } else {
//                throw new APIException(response.errorType());
//            }
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        return results.toArray(new String[0]);
//    }
}
