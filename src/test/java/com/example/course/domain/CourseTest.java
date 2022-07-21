package com.example.course.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CourseTest
{
    private static final String TEXT_WITH_SPECIAL_CHAR = "123wejhrg£%$£$£$sdjfsdjkf$%";
    private static final String SANTITIZED_TEXT = "123wejhrgsdjfsdjkf";

    @Test
    public void testSearchFriendlyCourseTitle()
    {
        Course course = new Course(10, TEXT_WITH_SPECIAL_CHAR);

        Assertions.assertEquals(10, course.getCourseDuration());
        Assertions.assertEquals(TEXT_WITH_SPECIAL_CHAR, course.getCourseTitle());
        Assertions.assertEquals(SANTITIZED_TEXT, course.getSearchFriendlyCourseTitle());
    }
}
