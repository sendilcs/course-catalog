package com.example.course.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchUtilTest
{
    private static final String TEXT_WITH_SPECIAL_CHAR = "123wejhrg£%$£$£$sdjfsdjkf$%";
    private static final String TEXT_WITH_CAPTITAL_LETTER = "123wejhrg£%$£$£$sdjfsdjkf$%";
    private static final String TEXT_WITH_SPECIAL_CHAR_AND_SPACES = "123wejhr g£%$£$£$sdj fsdjkf$%";
    private static final String SANTITIZED_TEXT = "123wejhrgsdjfsdjkf";

    @Test
    public void test_sanitizeText_withSpecialChars()
    {
        Assertions.assertEquals(SANTITIZED_TEXT, SearchUtil.sanitizeText(TEXT_WITH_SPECIAL_CHAR));
        Assertions.assertEquals(SANTITIZED_TEXT, SearchUtil.sanitizeText(TEXT_WITH_CAPTITAL_LETTER));
        Assertions.assertEquals(SANTITIZED_TEXT, SearchUtil.sanitizeText(TEXT_WITH_SPECIAL_CHAR_AND_SPACES));
    }
}
