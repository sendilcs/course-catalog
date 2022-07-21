package com.example.course.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema
public class CourseAndDurationSearchResult
{
    @Schema(required = true, description = "Total Course Duration")
    private double totalCourseDuration = 0;
    @Schema(required = true, description = "Course Title")
    private List<String> courseTitles = new ArrayList<>();

    public double getTotalCourseDuration()
    {
        return totalCourseDuration;
    }

    public List<String> getCourseTitles()
    {
        return courseTitles;
    }

    public void addCourse(double courseDuration, String courseTitles){
        this.totalCourseDuration += courseDuration;
        this.courseTitles.add(courseTitles);
    }
}
