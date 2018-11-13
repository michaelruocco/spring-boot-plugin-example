package uk.co.mruoc.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableSet;

@Component
@Slf4j
public class AliasLoaderService {

    private final Map<String, Set<AliasLoader>> aliasLoaders = new HashMap<>();

    @Autowired
    public AliasLoaderService(final List<AliasLoader> aliasLoaders) {
        populateMap(aliasLoaders);
    }

    public Set<Alias> loadAliases(final String channelId, final Alias alias) {
        final Set<Alias> aliases = new HashSet<>(Collections.singletonList(alias));
        final String aliasType = alias.getType();
        final String key = buildKey(channelId, aliasType);
        log.info("finding alias loader with key {}", key);
        aliases.addAll(loadAliasesByKey(key, alias));
        log.info("returning aliases {}", toString(aliases));
        return unmodifiableSet(aliases);
    }

    private Set<Alias> loadAliasesByKey(final String key, final Alias alias) {
        if (!aliasLoaders.containsKey(key)) {
            log.info("no alias loaders found using key", key);
            return emptySet();
        }

        final Set<AliasLoader> loaders = aliasLoaders.get(key);
        log.info("found alias loader {} loading aliases with {}", toString(loaders), alias);
        final Set<Alias> loadedAliases = loaders.stream()
                .map(loader -> loader.loadAliases(alias))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        log.info("loaded aliases {}", toString(loadedAliases));
        return loadedAliases;
    }

    private void populateMap(final List<AliasLoader> aliasLoaders) {
        log.info("populating {} with {} aliasLoaders", this.getClass(), aliasLoaders.size());
        aliasLoaders.forEach(this::add);
    }

    private void add(final AliasLoader loader) {
        final String channelId = loader.getChannelId();
        final Set<String> aliasTypes = loader.getSupportedAliasTypes();
        log.info("got supported channel id {} and alias types {} for alias loader {}", channelId, toString(aliasTypes), loader);
        aliasTypes.forEach(aliasType -> add(loader, aliasType));
    }

    private void add(final AliasLoader loader, final String aliasType) {
        final String channelId = loader.getChannelId();
        final String key = buildKey(channelId, aliasType);
        log.info("adding alias loader {} under key {}", loader, key);
        if (aliasLoaders.containsKey(key)) {
            aliasLoaders.get(key).add(loader);
            return;
        }
        aliasLoaders.put(key, new HashSet<>(singletonList(loader)));
    }

    private static String buildKey(final String channelId, final String aliasType) {
        return String.format("%s-%s", channelId, aliasType);
    }

    private static String toString(Set<?> values) {
        return Arrays.toString(values.toArray());
    }

}
