package com.example.course.service;

import com.example.course.domain.CourseAndDurationSearchResult;
import com.example.course.domain.CourseSearchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CourseServiceImplTest
{
    private CourseServiceImpl courseServiceImpl = new CourseServiceImpl();

    @Test
    public void test_getCourseTitles_whenSearchResultsReturned()
    {
        CourseSearchResult courseSearchResult = courseServiceImpl.getCourseTitles("covid");
        Assertions.assertEquals(3, courseSearchResult.getCourseTitles().size());
    }

    @Test
    public void test_getCourseTitles_whenNoSearchResultsReturned()
    {
        CourseSearchResult courseSearchResult = courseServiceImpl.getCourseTitles("covid123");
        Assertions.assertEquals(0, courseSearchResult.getCourseTitles().size());
    }

    @Test
    public void test_getTotalDurationAndCourseTitles_whenSearchResultsReturned()
    {
        CourseAndDurationSearchResult courseSearchResult = courseServiceImpl.getTotalDurationAndCourseTitles("covid");
        Assertions.assertEquals(3, courseSearchResult.getCourseTitles().size());
        Assertions.assertEquals(13, courseSearchResult.getTotalCourseDuration());

    }

    @Test
    public void test_getTotalDurationAndCourseTitles_whenNoSearchResultsReturned()
    {
        CourseAndDurationSearchResult courseSearchResult = courseServiceImpl.getTotalDurationAndCourseTitles("covid123");
        Assertions.assertEquals(0, courseSearchResult.getCourseTitles().size());
        Assertions.assertEquals(0, courseSearchResult.getTotalCourseDuration());
    }
}