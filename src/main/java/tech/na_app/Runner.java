package tech.na_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import tech.na_app.utils.jwt.PasswordUtils;

@SpringBootApplication
public class Runner extends SpringBootServletInitializer {

    public static void main(String[] args) {

        String salt = PasswordUtils.getSalt();
        String passwordEncode = PasswordUtils.generateSecurePassword("naDirector", salt);
        System.out.println(salt);
        System.out.println(passwordEncode);


//        SpringApplication.run(Runner.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Runner.class);
    }
}
