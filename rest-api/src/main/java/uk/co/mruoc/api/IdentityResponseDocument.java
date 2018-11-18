package uk.co.mruoc.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

import static java.util.Arrays.asList;

@Data
@NoArgsConstructor(force = true)
public class IdentityResponseDocument {

    private final Collection<IdentityResponseData> data;

    public IdentityResponseDocument(IdentityResponseData... data) {
        this(asList(data));
    }

    public IdentityResponseDocument(Collection<IdentityResponseData> data) {
        this.data = data;
    }

}
