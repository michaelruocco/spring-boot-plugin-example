package uk.co.mruoc.api;

import lombok.Getter;

@Getter
public class JsonApiException extends RuntimeException {

    private final ErrorData errorData;

    public JsonApiException(final ErrorData errorData, final Throwable cause) {
        super(errorData.getDetail(), cause);
        this.errorData = errorData;
    }

}
