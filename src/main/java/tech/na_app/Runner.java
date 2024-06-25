package tech.na_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Base64;

@SpringBootApplication
public class Runner extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Runner.class);
    }

    @PostConstruct
    public void extractCredentials() throws IOException {
        String header = "Basic ZC5wb25vbWFyZW5rb2FAZ21haWwuY29tOnF3ZXJ0eTEyMyE=";
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
            String token = new String(decoded, "UTF-8");
            System.out.println(token);
            int delim = token.indexOf(":");
            if (delim == -1) {
                throw new IllegalArgumentException("Invalid basic authentication token");
            }
            System.out.println(token.substring(0, delim));
            System.out.println(token.substring(delim + 1));
//            return new String[] {token.substring(0, delim), token.substring(delim + 1)};
        } catch (IllegalArgumentException e) {
            throw new IOException("Failed to decode basic authentication token");
        }
    }

}
