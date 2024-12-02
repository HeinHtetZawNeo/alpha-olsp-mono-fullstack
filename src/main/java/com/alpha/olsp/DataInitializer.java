package com.alpha.olsp;

import com.alpha.olsp.model.State;
import com.alpha.olsp.repository.StateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StateRepository stateRepository;

    public DataInitializer(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (stateRepository.count() == 0) {
            System.out.println("Initializing states...");

            stateRepository.save(new State("AL", "Alabama"));
            stateRepository.save(new State("AK", "Alaska"));
            stateRepository.save(new State("AZ", "Arizona"));
            stateRepository.save(new State("AR", "Arkansas"));
            stateRepository.save(new State("CA", "California"));
            stateRepository.save(new State("CO", "Colorado"));
            stateRepository.save(new State("CT", "Connecticut"));
            stateRepository.save(new State("DE", "Delaware"));
            stateRepository.save(new State("FL", "Florida"));
            stateRepository.save(new State("GA", "Georgia"));
            stateRepository.save(new State("HI", "Hawaii"));
            stateRepository.save(new State("ID", "Idaho"));
            stateRepository.save(new State("IL", "Illinois"));
            stateRepository.save(new State("IN", "Indiana"));
            stateRepository.save(new State("IA", "Iowa"));
            stateRepository.save(new State("KS", "Kansas"));
            stateRepository.save(new State("KY", "Kentucky"));
            stateRepository.save(new State("LA", "Louisiana"));
            stateRepository.save(new State("ME", "Maine"));
            stateRepository.save(new State("MD", "Maryland"));
            stateRepository.save(new State("MA", "Massachusetts"));
            stateRepository.save(new State("MI", "Michigan"));
            stateRepository.save(new State("MN", "Minnesota"));
            stateRepository.save(new State("MS", "Mississippi"));
            stateRepository.save(new State("MO", "Missouri"));
            stateRepository.save(new State("MT", "Montana"));
            stateRepository.save(new State("NE", "Nebraska"));
            stateRepository.save(new State("NV", "Nevada"));
            stateRepository.save(new State("NH", "New Hampshire"));
            stateRepository.save(new State("NJ", "New Jersey"));
            stateRepository.save(new State("NM", "New Mexico"));
            stateRepository.save(new State("NY", "New York"));
            stateRepository.save(new State("NC", "North Carolina"));
            stateRepository.save(new State("ND", "North Dakota"));
            stateRepository.save(new State("OH", "Ohio"));
            stateRepository.save(new State("OK", "Oklahoma"));
            stateRepository.save(new State("OR", "Oregon"));
            stateRepository.save(new State("PA", "Pennsylvania"));
            stateRepository.save(new State("RI", "Rhode Island"));
            stateRepository.save(new State("SC", "South Carolina"));
            stateRepository.save(new State("SD", "South Dakota"));
            stateRepository.save(new State("TN", "Tennessee"));
            stateRepository.save(new State("TX", "Texas"));
            stateRepository.save(new State("UT", "Utah"));
            stateRepository.save(new State("VT", "Vermont"));
            stateRepository.save(new State("VA", "Virginia"));
            stateRepository.save(new State("WA", "Washington"));
            stateRepository.save(new State("WV", "West Virginia"));
            stateRepository.save(new State("WI", "Wisconsin"));
            stateRepository.save(new State("WY", "Wyoming"));
            // Add more states as needed

            System.out.println("States initialized successfully.");
        } else {
            System.out.println("States already initialized.");
        }
    }
}