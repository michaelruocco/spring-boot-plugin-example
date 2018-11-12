package uk.co.mruoc.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.app.service.AliasLoaderService;

import java.util.Set;

@RestController
@RequestMapping(path= "/aliases")
@Slf4j
public class AliasController {

    private AliasLoaderService aliasLoaderService;

    public AliasController(AliasLoaderService aliasLoaderService) {
        this.aliasLoaderService = aliasLoaderService;
    }

    @PostMapping
    public Set<Alias> create(final @RequestHeader("channel-id") String channelId, final @RequestBody Alias alias) {
        log.info("got alias {}", alias);
        return aliasLoaderService.loadAliases(channelId, alias);
    }

}