package uk.co.mruoc.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.ErrorData;
import uk.co.mruoc.api.IdentityRequestDocument;
import uk.co.mruoc.api.IdentityData;
import uk.co.mruoc.api.IdentityResponseDocument;
import uk.co.mruoc.api.JsonApiException;
import uk.co.mruoc.app.Identity;
import uk.co.mruoc.app.service.IdentityService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path= "/identities")
@Slf4j
public class IdentityController {

    private IdentityService identityService;
    private IdentityConverter identityConverter;

    public IdentityController(IdentityService identityService, IdentityConverter identityConverter) {
        this.identityService = identityService;
        this.identityConverter = identityConverter;
    }

    @PostMapping
    public IdentityResponseDocument create(final @RequestHeader("channel-id") String channelId, final @RequestBody IdentityRequestDocument request) {
        log.info("received request {}", request);
        final List<Identity> createdIdentities = new ArrayList<>();
        final List<ErrorData> errors = new ArrayList<>();
        for (IdentityData dataItem : request.getData()) {
            try {
                createdIdentities.add(create(channelId, dataItem));
            } catch (JsonApiException e) {
                log.error(e.getMessage(), e);
                errors.add(e.getErrorData());
            }
        }
        final Collection<IdentityData> responseData = identityConverter.toIdentityDataList(createdIdentities);
        final IdentityResponseDocument response = new IdentityResponseDocument(responseData, errors);
        log.info("returning response {}", response);
        return response;
    }

    private Identity create(String channelId, IdentityData dataItem) {
        final Identity identity = identityConverter.toModelIdentity(dataItem);
        log.info("creating identity from {}", identity);
        final Identity createdIdentity = identityService.create(channelId, identity);
        log.info("created identity from {}", createdIdentity);
        return createdIdentity;
    }

}