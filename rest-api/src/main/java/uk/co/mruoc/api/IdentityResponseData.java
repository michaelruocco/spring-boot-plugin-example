package uk.co.mruoc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class IdentityResponseData {

    private final UUID id;
    private final String type = "identities";
    private final IdentityResponseAttributes attributes;

    public IdentityResponseData(UUID id, Collection<IdentityAlias> aliases) {
        this.id = id;
        this.attributes = new IdentityResponseAttributes(aliases);
    }

}
