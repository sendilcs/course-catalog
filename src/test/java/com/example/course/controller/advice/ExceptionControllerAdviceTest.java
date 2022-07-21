package com.example.course.controller.advice;

import com.example.course.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(MockitoExtension.class)
public class ExceptionControllerAdviceTest
{
    private static final String NOT_FOUND_MSG = "NOT Found";
    private static final String RUNTIME_EXCEPTION_MSG = "Unexpected system exception ID:";


    private ExceptionControllerAdvice exceptionControllerAdvice = new ExceptionControllerAdvice();

    @Test
    public void test_handleNotFoundException()
    {
        ResponseEntity<ErrorResponse> response = exceptionControllerAdvice.handleNotFoundException(new NotFoundException(NOT_FOUND_MSG));

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat(response.getBody().getErrorMessage(), is(equalTo(NOT_FOUND_MSG)));
    }

    @Test
    public void test_handleRuntimeException()
    {
        ResponseEntity<ErrorResponse> response = exceptionControllerAdvice.handleRuntimeException(new RuntimeException(RUNTIME_EXCEPTION_MSG));

        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.INTERNAL_SERVER_ERROR)));
        assertThat(response.getBody().getErrorMessage(), is(containsString(RUNTIME_EXCEPTION_MSG)));
    }
}
