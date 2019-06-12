package com.sixet.skeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * SkeletonApplication class provides the bootstrap of the application.
 * @since 11/06/2019
 * @author <a href="mailto:gtrevisane@gmail.com">Get Trevisan</a>
 */
@SpringBootApplication
public class SkeletonApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkeletonApplication.class, args);
    }

    /**
     * A PasswordEncoder bean that uses the BCrypt strong hashing function.
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
