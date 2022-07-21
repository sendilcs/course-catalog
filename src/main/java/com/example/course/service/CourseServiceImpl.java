package com.example.course.service;

import com.example.course.domain.Course;
import com.example.course.domain.CourseAndDurationSearchResult;
import com.example.course.domain.CourseSearchResult;
import com.example.course.util.SearchUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService
{
    private static List<Course> courseCatalogs = loadCatalog();

    private static List<Course> loadCatalog()
    {
        return List.of(new Course(5.00, "Introduction to mechanical ventilation"),
                new Course(3.5, "Introduction to coronavirus disease 2019 (COVID-19)"),
                new Course(2.00, "Clinical pointers: COVID-19 in primary care"),
                new Course(1, "Clinical pointers: remote consultations in primary care"),
                new Course(10.25, "Infection control - including basic personal protective equipment"),
                new Course(7.50, "Introduction to testing for COVID-19"),
                new Course(2.00, "Airways management: tracheal intubation"),
                new Course(2.50, "Quick tips: proning in critical care"),
                new Course(3.0, "Quick tips: introduction to asthma"));
    }

    @Override
    public CourseSearchResult getCourseTitles(String searchText)
    {
        CourseSearchResult courseSearchResult = new CourseSearchResult();

        List<String> matchingCourseTitles = courseCatalogs.stream()
                .filter(course -> course.getSearchFriendlyCourseTitle().contains(SearchUtil.sanitizeText(searchText)))
                .map(course -> course.getCourseTitle()).collect(Collectors.toList());

        courseSearchResult.addCourseTitles(matchingCourseTitles);

        return courseSearchResult;
    }

    @Override
    public CourseAndDurationSearchResult getTotalDurationAndCourseTitles(String searchText)
    {
        CourseAndDurationSearchResult courseSearchResult = new CourseAndDurationSearchResult();

        courseCatalogs.stream()
                .filter(matchingCourse -> matchingCourse.getSearchFriendlyCourseTitle().contains(SearchUtil.sanitizeText(searchText)))
                .forEach(matchingCourse -> courseSearchResult.addCourse(matchingCourse.getCourseDuration(), matchingCourse.getCourseTitle()));

        return courseSearchResult;
    }
}
