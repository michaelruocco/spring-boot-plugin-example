package uk.co.mruoc.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.api.AliasLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.*;

@Component
@Slf4j
public class AliasLookupService {

    private List<AliasLoader> aliasLoaders;

    @Autowired
    public AliasLookupService(List<AliasLoader> aliasLoaders) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%***************** loading alias lookup service with " + aliasLoaders.size() + " loaders *********************%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        this.aliasLoaders = aliasLoaders;
    }

    public List<Alias> lookupAliases(Alias alias) {
        Set<Alias> allAliases = new HashSet<>();
        allAliases.add(alias);
        log.info("looking up aliases from alias {}", alias);
        for (AliasLoader loader : aliasLoaders) {
            allAliases.addAll(loadAliases(loader, alias));
        }
        log.info("returning all aliases {}", Arrays.toString(allAliases.toArray()));
        return Collections.unmodifiableList(new ArrayList<>(allAliases));
    }

    private static List<Alias> loadAliases(AliasLoader loader, Alias alias) {
        if (loader.supportsLookupAlias(alias)) {
            log.info("alias loader {} supports alias {}", loader, alias);
            List<Alias> aliases = loader.loadAliases(alias);
            log.info("alias loader {} loaded {}", loader, Arrays.toString(aliases.toArray()));
            return aliases;
        }
        log.info("alias loader {} does not support alias {}", loader, alias);
        return emptyList();
    }

}
