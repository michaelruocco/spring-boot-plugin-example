package uk.co.mruoc.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
public class IdentityData {

    private final UUID id;
    private final String type = "identities";
    private final IdentityAttributes attributes;

    public IdentityData(UUID id, Collection<IdentityAlias> aliases) {
        this.id = id;
        this.attributes = new IdentityAttributes(aliases);
    }

    @JsonIgnore
    public Collection<IdentityAlias> getAliases() {
        return attributes.getAliases();
    }

}
