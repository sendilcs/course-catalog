package com.example.course.controller;


import com.example.course.exception.NotFoundException;
import com.example.course.domain.CourseAndDurationSearchResult;
import com.example.course.domain.CourseSearchResult;
import com.example.course.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseCatalogControllerTest
{
    private static final String NO_RESULTS_FOUND = "No results found for search term :[%s]";
    @InjectMocks
    private CourseCatalogController courseCatalogController = new CourseCatalogController();
    private List<String> searchResult = List.of("searchTextTitle1", "searchTextTitle2");
    @Mock
    private CourseService courseService;

    @Mock
    private CourseAndDurationSearchResult courseAndDurationSearchResult;

    @Mock
    private CourseSearchResult courseSearchResult;

    @Test
    public void test_getCourseCatalogue_whenResultsFound() throws Exception
    {
        String searchText = "searchText";
        when(courseService.getCourseTitles(searchText)).thenReturn(courseSearchResult);
        when(courseSearchResult.getCourseTitles()).thenReturn(searchResult);

        ResponseEntity response = courseCatalogController.getCourseCatalogue(searchText);

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(response.getBody(), is(sameInstance(courseSearchResult)));
    }

    @Test
    public void test_getCourseCatalogue_whenNoResultsFound() throws Exception
    {
        String searchText = "searchText";

        when(courseService.getCourseTitles(searchText)).thenReturn(courseSearchResult);
        when(courseSearchResult.getCourseTitles()).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> courseCatalogController.getCourseCatalogue(searchText),
                "NotFoundException exception was expected");

        assertEquals(String.format(NO_RESULTS_FOUND, searchText), exception.getMessage());
    }

    @Test
    public void test_getTotalDurationAndCourseTitles_whenResultsFound() throws Exception
    {
        String searchText = "searchText";
        when(courseService.getTotalDurationAndCourseTitles(searchText)).thenReturn(courseAndDurationSearchResult);
        when(courseAndDurationSearchResult.getCourseTitles()).thenReturn(searchResult);

        ResponseEntity response = courseCatalogController.getTotalDurationAndCourseTitles(searchText);

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat(response.getBody(), is(sameInstance(courseAndDurationSearchResult)));
    }

    @Test
    public void test_getTotalDurationAndCourseTitles_whenNoResultsFound() throws Exception
    {
        String searchText = "searchText";
        when(courseService.getTotalDurationAndCourseTitles(searchText)).thenReturn(courseAndDurationSearchResult);
        when(courseAndDurationSearchResult.getCourseTitles()).thenReturn(List.of());

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> courseCatalogController.getTotalDurationAndCourseTitles(searchText),
                "NotFoundException exception was expected");

        assertEquals(String.format(NO_RESULTS_FOUND, searchText), exception.getMessage());
    }
}
