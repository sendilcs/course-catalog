package com.example.course.controller;

import com.example.course.exception.NotFoundException;
import com.example.course.controller.advice.ErrorResponse;
import com.example.course.domain.CourseAndDurationSearchResult;
import com.example.course.domain.CourseSearchResult;
import com.example.course.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping(value = "/course",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseCatalogController
{
    private static final String NO_RESULTS_FOUND = "No results found for search term :[%s]";
    @Resource
    private CourseService courseService;

    @Operation(summary = "Retrieve the course catalogues for the given search text")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a course catalogue",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseSearchResult.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(value = "/catalog/search/{searchText}")
    public ResponseEntity getCourseCatalogue(@PathVariable String searchText)
    {
        CourseSearchResult courseSearchResult = courseService.getCourseTitles(searchText);

        if (courseSearchResult.getCourseTitles().isEmpty())
        {
            String noResultsFound = String.format(NO_RESULTS_FOUND, searchText);
            log.error(noResultsFound);
            throw new NotFoundException(noResultsFound);
        }
        return ResponseEntity.ok(courseSearchResult);
    }

    @Operation(summary = "Retrieve the course catalogues and duration for the given search text")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved a course catalogue and duration",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = CourseAndDurationSearchResult.class))}),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Unexpected system exception",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping(value = "/catalogAndDuration/search/{searchText}")
    public ResponseEntity getTotalDurationAndCourseTitles(@PathVariable String searchText)
    {
        CourseAndDurationSearchResult courseSearchResult = courseService.getTotalDurationAndCourseTitles(searchText);
        if (courseSearchResult.getCourseTitles().isEmpty())
        {
            String noResultsFound = String.format(NO_RESULTS_FOUND, searchText);
            log.error(noResultsFound);
            throw new NotFoundException(noResultsFound);
        }
        return ResponseEntity.ok(courseSearchResult);
    }
}
