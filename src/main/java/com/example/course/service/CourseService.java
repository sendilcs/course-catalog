package com.example.course.service;

import com.example.course.domain.CourseAndDurationSearchResult;
import com.example.course.domain.CourseSearchResult;

import java.util.List;

public interface CourseService
{
    CourseSearchResult getCourseTitles(String searchText);

    CourseAndDurationSearchResult getTotalDurationAndCourseTitles(String searchText);
}
