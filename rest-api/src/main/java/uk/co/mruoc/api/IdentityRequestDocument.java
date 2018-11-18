package uk.co.mruoc.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Iterator;

import static java.util.Arrays.asList;

@Data
@NoArgsConstructor(force = true)
public class IdentityRequestDocument implements Iterable<IdentityData> {

    private final Collection<IdentityData> data;

    public IdentityRequestDocument(IdentityData... data) {
        this(asList(data));
    }

    public IdentityRequestDocument(Collection<IdentityData> data) {
        this.data = data;
    }

    @Override
    public Iterator<IdentityData> iterator() {
        return data.iterator();
    }

}
