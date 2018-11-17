package uk.co.mruoc.plugin.as3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:test.properties")
public class TestConfiguration {

    @Bean
    public TestProperties testProperties(@Value("${test.username}") final String username,
                                         @Value("${test.password}") final String password,
                                         @Value("${test.url}") final String url) {
        return TestProperties.builder()
                .username(username)
                .password(password)
                .url(url)
                .build();
    }

}
