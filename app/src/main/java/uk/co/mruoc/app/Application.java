package uk.co.mruoc.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        if (isRunningOnHeroku()) {
            configureHerokuPort();
        }
        SpringApplication.run(Application.class, args);
    }

    private static boolean isRunningOnHeroku() {
        final String port = getHerokuPort();
        final String dyno = System.getenv().get("DYNO");
        return port != null && dyno != null;
    }

    private static void configureHerokuPort() {
        System.getProperties().put("server.port", getHerokuPort());
    }

    private static String getHerokuPort() {
        return System.getenv().get("PORT");
    }

}