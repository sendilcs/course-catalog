package com.example.course.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Schema
@Data
public class CourseSearchResult
{
    @Schema(required = true, description = "Course Title")
    private List<String> courseTitles = new ArrayList<>();

    public void addCourseTitles(List<String> courseTitles)
    {
        if (!CollectionUtils.isEmpty(courseTitles))
        {
            this.courseTitles.addAll(courseTitles);
        }
    }
}
