package uk.co.mruoc.plugin.api;

import org.pf4j.ExtensionPoint;

import java.util.Set;

public interface AliasLoader extends ExtensionPoint, ChannelIdProvider {

    Set<String> getSupportedAliasTypes();

    Set<Alias> loadAliases(final Alias lookupAlias);

}
