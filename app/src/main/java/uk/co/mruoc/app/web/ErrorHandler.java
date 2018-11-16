package uk.co.mruoc.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.mruoc.plugin.api.AliasLoaderException;
import uk.co.mruoc.api.ErrorData;
import uk.co.mruoc.api.ErrorDocument;

import java.util.UUID;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(AliasLoaderException.class)
    public ErrorDocument handle(AliasLoaderException exception) {
        ErrorData data = ErrorData.builder()
                .id(UUID.randomUUID())
                .detail(exception.getMessage())
                .build();
        return new ErrorDocument(data);
    }

}