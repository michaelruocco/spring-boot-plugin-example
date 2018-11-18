package uk.co.mruoc.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HttpResponse<T> extends ResponseEntity<T> {

    public HttpResponse(T body, HttpHeaders headers, HttpStatus status) {
        super(body, headers, status);
    }

    public static HttpResponse<ErrorDocument> buildWithError(ErrorDocument body) {
        return new HttpResponse<>(body, buildDefaultHeaders(), body.getStatus());
    }

    private static HttpHeaders buildDefaultHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "vnd.api+json"));
        return headers;
    }

}
