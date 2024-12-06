package com.alpha.olsp;

import com.alpha.olsp.model.State;
import com.alpha.olsp.repository.StateRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class AlphaOnlineSystemApplication {
    private final StateRepository stateRepository;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${jwt.secret}")
    private String jwtSecret;

    public static void main(String[] args) {
        SpringApplication.run(AlphaOnlineSystemApplication.class, args);
    }

    @PostConstruct
    public void logEnvVariables() {
        System.out.println("Database URL from Environment (Spring): " + datasourceUrl);
        System.out.println("jwtSecret URL from Environment (Spring): " + jwtSecret);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            stateRepository.save(new State("IA", "Iowa"));
        };
    }
}
