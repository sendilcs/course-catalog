package com.example.course.domain;

import com.example.course.util.SearchUtil;
import lombok.Generated;

public class Course
{
    private double courseDuration;
    private String courseTitle;

    private String searchFriendlyCourseTitle;

    public Course(double courseDuration, String courseTitle){
        this.courseDuration = courseDuration;
        this.courseTitle = courseTitle;
        this.searchFriendlyCourseTitle = SearchUtil.sanitizeText(courseTitle);
    }

    public double getCourseDuration()
    {
        return courseDuration;
    }

    public String getCourseTitle()
    {
        return courseTitle;
    }

    public String getSearchFriendlyCourseTitle()
    {
        return searchFriendlyCourseTitle;
    }
}
