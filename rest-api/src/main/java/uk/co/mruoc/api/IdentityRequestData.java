package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentityRequestData {

    private final UUID id;
    private final String type = "identities";
    private final IdentityRequestAttributes attributes;

    public IdentityRequestData(IdentityAlias alias) {
        this(null, alias);
    }

    public IdentityRequestData(UUID id, IdentityAlias alias) {
        this.id = id;
        this.attributes = new IdentityRequestAttributes(alias);
    }

}
