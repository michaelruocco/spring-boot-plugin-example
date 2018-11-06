package uk.co.mruoc.api;

import org.pf4j.ExtensionPoint;

import java.util.List;

public interface AliasLoader extends ExtensionPoint {

    boolean supportsLookupAlias(Alias alias);

    List<Alias> loadAliases(Alias lookupAlias);

}
