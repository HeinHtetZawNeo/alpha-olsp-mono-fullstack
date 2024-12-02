package com.alpha.olsp;

import com.alpha.olsp.model.State;
import com.alpha.olsp.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class AlphaOnlineSystemApplication {
    private final StateRepository stateRepository;

    public static void main(String[] args) {
        SpringApplication.run(AlphaOnlineSystemApplication.class, args);
    }
//
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            stateRepository.save(new State("IA", "Iowa"));
//        };
//    }
}
