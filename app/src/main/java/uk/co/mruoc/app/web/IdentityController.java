package uk.co.mruoc.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.IdentityAlias;
import uk.co.mruoc.api.IdentityRequestDocument;
import uk.co.mruoc.api.IdentityResponseData;
import uk.co.mruoc.api.IdentityResponseDocument;
import uk.co.mruoc.plugin.api.Alias;
import uk.co.mruoc.app.service.AliasLoaderService;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path= "/identities")
@Slf4j
public class IdentityController {

    private AliasLoaderService aliasLoaderService;
    private AliasConverter aliasConverter;

    public IdentityController(AliasLoaderService aliasLoaderService, AliasConverter aliasConverter) {
        this.aliasLoaderService = aliasLoaderService;
        this.aliasConverter = aliasConverter;
    }

    @PostMapping
    public IdentityResponseDocument create(final @RequestHeader("channel-id") String channelId, final @RequestBody IdentityRequestDocument request) {
        log.info("received request {}", request);
        IdentityAlias identityAlias = request.getAlias();
        Alias alias = aliasConverter.toModelAlias(identityAlias);
        Set<Alias> loadedAliases = aliasLoaderService.loadAliases(channelId, alias);
        Set<IdentityAlias> responseAliases = aliasConverter.toApiAlias(loadedAliases);
        IdentityResponseDocument response = new IdentityResponseDocument(new IdentityResponseData(UUID.randomUUID(), responseAliases));
        log.info("returning response {}", response);
        return response;
    }

}