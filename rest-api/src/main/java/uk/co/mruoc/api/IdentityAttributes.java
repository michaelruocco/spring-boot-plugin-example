package uk.co.mruoc.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Data
@NoArgsConstructor(force = true)
@ToString
public class IdentityAttributes {

    private final Collection<IdentityAlias> aliases;

    public IdentityAttributes(Collection<IdentityAlias> aliases) {
        this.aliases = aliases;
    }

}
