package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@Data
@NoArgsConstructor(force = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class IdentityResponseDocument {

    private final Collection<IdentityData> data;
    private final Collection<ErrorData> errors;

    public IdentityResponseDocument(Collection<IdentityData> data, Collection<ErrorData> errors) {
        this.data = CollectionUtils.isEmpty(data) ? null : data;
        this.errors = CollectionUtils.isEmpty(errors) ? null : errors;
    }

}
