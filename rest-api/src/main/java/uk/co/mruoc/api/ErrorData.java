package uk.co.mruoc.api;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.UUID;

public interface ErrorData {

    UUID getId();

    String getCode();

    String getTitle();

    String getDetail();

    Map<String, String> getMeta();

    String getStatus();

    HttpStatus getHttpStatus();

}
