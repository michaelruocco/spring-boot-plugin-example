package uk.co.mruoc.app;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import uk.co.mruoc.plugin.api.Alias;

import java.util.Collection;
import java.util.UUID;

@Getter
@Builder
@ToString
public class Identity {

    private UUID id;
    private Collection<Alias> aliases;

    public boolean hasId() {
        return id != null;
    }

}
