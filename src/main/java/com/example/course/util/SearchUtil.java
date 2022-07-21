package com.example.course.util;

public class SearchUtil
{
    public static String sanitizeText(String text){
        return text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    }
}
