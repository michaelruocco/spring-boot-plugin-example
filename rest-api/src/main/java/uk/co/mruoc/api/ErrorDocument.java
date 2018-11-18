package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Getter
public class ErrorDocument {

    private final HttpStatus status;
    private final List<ErrorData> errors;

    public ErrorDocument(final ErrorData... error) {
        this(asList(error));
    }

    public ErrorDocument(final HttpStatus status, final ErrorData... error) {
        this(status, asList(error));
    }

    public ErrorDocument(final HttpStatus status, final List<ErrorData> errors) {
        this.status = status;
        this.errors = errors;
    }

    public ErrorDocument(final List<ErrorData> errors) {
        this.status = extractStatus(errors);
        this.errors = errors;
    }

    private static HttpStatus extractStatus(final List<ErrorData> errors) {
        if (errors.isEmpty()) {
            return INTERNAL_SERVER_ERROR;
        }
        return errors.get(0).getHttpStatus();
    }

    @JsonIgnore
    public HttpStatus getStatus() {
        return status;
    }

}