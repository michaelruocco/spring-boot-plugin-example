package uk.co.mruoc.plugin.as3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:as3-plugin-ods.properties")
@PropertySource("classpath:as3-plugin-tcv.properties")
public class PluginConfiguration {

    @Bean
    public OdsClientConfig odsClientConfig(@Value("${ods.username}") final String username,
                                          @Value("${ods.password}") final String password,
                                          @Value("${ods.url}") final String url) {
        return OdsClientConfig.builder()
                .username(username)
                .password(password)
                .url(url)
                .build();
    }

    @Bean
    public TcvClientConfig tcvClientConfig(@Value("${tcv.username}") final String username,
                                           @Value("${tcv.password}") final String password,
                                           @Value("${tcv.url}") final String url,
                                           @Value("${tcv.environment}") final String environment) {
        return TcvClientConfig.builder()
                .username(username)
                .password(password)
                .url(url)
                .environment(environment)
                .build();
    }

}
