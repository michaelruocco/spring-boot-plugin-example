package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.UUID;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultErrorData implements ErrorData {

    @Builder.Default
    private final UUID id = UUID.randomUUID();
    private final String code;
    private final String title;
    private final String detail;
    private final Map<String, String> meta;
    private final HttpStatus httpStatus;

    public String getStatus() {
        return Integer.toString(httpStatus.value());
    }

    @JsonIgnore
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
