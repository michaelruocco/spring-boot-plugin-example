package uk.co.mruoc.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class AliasLoaderService {

    private final Map<String, AliasLoader> aliasLoaders = new HashMap<>();

    @Autowired
    public AliasLoaderService(final List<AliasLoader> aliasLoaders) {
        populateMap(aliasLoaders);
    }

    public Set<Alias> loadAliases(final String channelId, final Alias alias) {
        final String aliasType = alias.getType();
        log.info("finding alias loader for channel {} and alias type {}", channelId, aliasType);
        final Set<Alias> aliases = new HashSet<>();
        aliases.add(alias);
        final String key = buildKey(channelId, aliasType);
        if (aliasLoaders.containsKey(key)) {
            final AliasLoader loader = aliasLoaders.get(key);
            log.info("found alias loader {} loading aliases with {}", loader, alias);
            final Set<Alias> loadedAliases = loader.loadAliases(alias);
            log.info("loaded aliases {}", Arrays.toString(loadedAliases.toArray()));
            aliases.addAll(loadedAliases);
        } else {
            log.info("no alias loaders found for channel {} and alias type {}", channelId, aliasType);
        }
        return aliases;
    }

    private void populateMap(final List<AliasLoader> aliasLoaders) {
        log.info("populating {} with {} aliasLoaders", this.getClass(), aliasLoaders.size());
        aliasLoaders.forEach(this::add);
    }

    private void add(final AliasLoader loader) {
        final String channelId = loader.getChannelId();
        final Set<String> aliasTypes = loader.getSupportedAliasTypes();
        log.info("got supported channel id {} and alias types {} for alias loader {}", channelId, Arrays.toString(aliasTypes.toArray()), loader);
        for(String aliasType : aliasTypes) {
            final String key = buildKey(channelId, aliasType);
            log.info("adding alias loader {} under key {}", loader, key);
            aliasLoaders.put(key, loader);
        }
    }

    private static String buildKey(final String channelId, final String aliasType) {
        return String.format("%s-%s", channelId, aliasType);
    }

}
