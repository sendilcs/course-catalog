package com.example.course.controller.advice;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum ErrorType
{
    UNKNOWN_DATA_ITEM,
    UNEXPECTED_ERROR;

    public static ErrorType fromValue(String v) {
        return valueOf(v);
    }
}
