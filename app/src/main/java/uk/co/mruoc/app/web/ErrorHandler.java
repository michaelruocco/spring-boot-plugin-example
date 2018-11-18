package uk.co.mruoc.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.mruoc.api.HttpResponse;
import uk.co.mruoc.api.JsonApiException;
import uk.co.mruoc.api.ErrorData;
import uk.co.mruoc.api.ErrorDocument;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(JsonApiException.class)
    public HttpResponse handle(JsonApiException exception) {
        log.error(exception.getMessage(), exception);
        ErrorData errorData = exception.getErrorData();
        return HttpResponse.buildWithError(new ErrorDocument(NOT_FOUND, errorData));
    }

}