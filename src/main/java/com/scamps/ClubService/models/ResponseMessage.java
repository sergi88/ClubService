package com.scamps.ClubService.models;

public enum ResponseMessage {
    DEFAULTERROR("error.default"),
    DEFAULTSUCCESS("success.default"),
    DEFAULTNOTFOUND("error.notFound")
    ;

    private final String value;

    private ResponseMessage(String displayValue) {
        this.value = displayValue;
    }

    public String getValue() {
        return value;
    }
}
