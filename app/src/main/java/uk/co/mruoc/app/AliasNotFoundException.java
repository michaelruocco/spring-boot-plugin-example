package uk.co.mruoc.app;

import uk.co.mruoc.api.ErrorData;
import uk.co.mruoc.api.JsonApiException;

public class AliasNotFoundException extends JsonApiException {

    public AliasNotFoundException(final ErrorData errorData) {
        super(errorData);
    }

    public AliasNotFoundException(final ErrorData errorData, final Throwable cause) {
        super(errorData, cause);
    }

}
