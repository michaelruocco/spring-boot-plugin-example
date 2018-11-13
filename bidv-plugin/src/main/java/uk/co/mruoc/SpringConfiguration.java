package uk.co.mruoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.mruoc.api.ChannelIdProvider;

@Configuration
public class SpringConfiguration {

    @Bean
    public ChannelIdProvider messageProvider() {
        return new BidvChannelIdProvider();
    }

}
