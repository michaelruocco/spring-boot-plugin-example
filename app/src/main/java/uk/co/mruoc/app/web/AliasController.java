package uk.co.mruoc.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.api.Alias;
import uk.co.mruoc.app.service.AliasLookupService;

import java.util.List;

@RestController
@RequestMapping(path= "/aliases")
@Slf4j
public class AliasController {

    private AliasLookupService lookupService;

    public AliasController(AliasLookupService lookupService) {
        this.lookupService = lookupService;
    }

    @PostMapping
    public List<Alias> create(final @RequestBody Alias alias) {
        log.info("got alias {}", alias);
        return lookupService.lookupAliases(alias);
    }

}