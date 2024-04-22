package com.winandronux.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record ExchangeRateResponse (
        String result,
        long timeLastUpdateUnix,
        String baseCode,
        Map<String, Double> conversionRates,
        @SerializedName("error-type")
        String errorType
) {}
