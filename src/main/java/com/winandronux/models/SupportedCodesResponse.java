package com.winandronux.models;

import com.google.gson.annotations.SerializedName;

public record SupportedCodesResponse(
        String result,
        String[][] supportedCodes,
        @SerializedName("error-type")
        String errorType
) {}
