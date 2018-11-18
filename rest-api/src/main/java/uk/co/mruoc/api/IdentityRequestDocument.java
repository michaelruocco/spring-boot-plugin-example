package uk.co.mruoc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class IdentityRequestDocument {

    private final IdentityRequestData data;

    public IdentityRequestDocument(IdentityAlias alias) {
        this.data = new IdentityRequestData(alias);
    }

    public IdentityAlias getAlias() {
        return data.getAttributes().getAlias();
    }

}
