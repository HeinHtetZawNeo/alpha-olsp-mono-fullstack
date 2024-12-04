package com.alpha.olsp;

import com.alpha.olsp.model.Catalog;
import com.alpha.olsp.model.State;
import com.alpha.olsp.repository.CatalogRepository;
import com.alpha.olsp.repository.StateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StateRepository stateRepository;
    private final CatalogRepository catalogRepository;

    public DataInitializer(StateRepository stateRepository, CatalogRepository catalogRepository) {
        this.stateRepository = stateRepository;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        initializeStates();
        initializeCatalogs();

    }

    private void initializeStates() {
        // Check if data already exists
        if (stateRepository.count() == 0) {
            System.out.println("Initializing states...");

//            stateRepository.save(new State("AL", "Alabama"));
//            stateRepository.save(new State("AK", "Alaska"));
//            stateRepository.save(new State("AZ", "Arizona"));
//            stateRepository.save(new State("AR", "Arkansas"));
//            stateRepository.save(new State("CA", "California"));
//            stateRepository.save(new State("CO", "Colorado"));
//            stateRepository.save(new State("CT", "Connecticut"));
//            stateRepository.save(new State("DE", "Delaware"));
//            stateRepository.save(new State("FL", "Florida"));
//            stateRepository.save(new State("GA", "Georgia"));
//            stateRepository.save(new State("HI", "Hawaii"));
//            stateRepository.save(new State("ID", "Idaho"));
//            stateRepository.save(new State("IL", "Illinois"));
//            stateRepository.save(new State("IN", "Indiana"));
            stateRepository.save(new State("IA", "Iowa"));
//            stateRepository.save(new State("KS", "Kansas"));
//            stateRepository.save(new State("KY", "Kentucky"));
//            stateRepository.save(new State("LA", "Louisiana"));
//            stateRepository.save(new State("ME", "Maine"));
//            stateRepository.save(new State("MD", "Maryland"));
//            stateRepository.save(new State("MA", "Massachusetts"));
//            stateRepository.save(new State("MI", "Michigan"));
//            stateRepository.save(new State("MN", "Minnesota"));
//            stateRepository.save(new State("MS", "Mississippi"));
//            stateRepository.save(new State("MO", "Missouri"));
//            stateRepository.save(new State("MT", "Montana"));
//            stateRepository.save(new State("NE", "Nebraska"));
//            stateRepository.save(new State("NV", "Nevada"));
//            stateRepository.save(new State("NH", "New Hampshire"));
//            stateRepository.save(new State("NJ", "New Jersey"));
//            stateRepository.save(new State("NM", "New Mexico"));
//            stateRepository.save(new State("NY", "New York"));
//            stateRepository.save(new State("NC", "North Carolina"));
//            stateRepository.save(new State("ND", "North Dakota"));
//            stateRepository.save(new State("OH", "Ohio"));
//            stateRepository.save(new State("OK", "Oklahoma"));
//            stateRepository.save(new State("OR", "Oregon"));
//            stateRepository.save(new State("PA", "Pennsylvania"));
//            stateRepository.save(new State("RI", "Rhode Island"));
//            stateRepository.save(new State("SC", "South Carolina"));
//            stateRepository.save(new State("SD", "South Dakota"));
//            stateRepository.save(new State("TN", "Tennessee"));
//            stateRepository.save(new State("TX", "Texas"));
//            stateRepository.save(new State("UT", "Utah"));
//            stateRepository.save(new State("VT", "Vermont"));
//            stateRepository.save(new State("VA", "Virginia"));
//            stateRepository.save(new State("WA", "Washington"));
//            stateRepository.save(new State("WV", "West Virginia"));
//            stateRepository.save(new State("WI", "Wisconsin"));
//            stateRepository.save(new State("WY", "Wyoming"));
            // Add more states as needed

            System.out.println("States initialized successfully.");
        } else {
            System.out.println("States already initialized.");
        }
    }

    private void initializeCatalogs() {
        System.out.println("Initializing catalogs...");
        if (catalogRepository.count() != 0) {
            System.out.println("Catalog already initialized.");
            return;
        }
        List<Catalog> catalogs = new ArrayList<>();

        // Parent Catalogs
        Catalog electronics = createCatalog("Electronics", "All electronic items");
        Catalog homeAppliances = createCatalog("Home Appliances", "Appliances for your home");
        Catalog clothing = createCatalog("Clothing", "Men's, Women's, and Kids' clothing");
        Catalog sports = createCatalog("Sports", "Sports equipment and accessories");

        // Subcatalogs for Electronics
        Catalog phones = createSubCatalog("Phones", "Mobile phones", electronics);
        Catalog laptops = createSubCatalog("Laptops", "Laptops and accessories", electronics);
        Catalog televisions = createSubCatalog("Televisions", "Smart and LED TVs", electronics);

        electronics.setSubCatalogs(new ArrayList<>(List.of(phones, laptops, televisions)));

        // Subcatalogs for Home Appliances
        Catalog refrigerators = createSubCatalog("Refrigerators", "Various types of refrigerators", homeAppliances);
        Catalog washingMachines = createSubCatalog("Washing Machines", "Automatic and semi-automatic", homeAppliances);
        Catalog microwaves = createSubCatalog("Microwaves", "Microwave ovens", homeAppliances);

        homeAppliances.setSubCatalogs(new ArrayList<>(List.of(refrigerators, washingMachines, microwaves)));

        // Subcatalogs for Clothing
        Catalog mensClothing = createSubCatalog("Men's Clothing", "Formal and casual wear for men", clothing);
        Catalog womensClothing = createSubCatalog("Women's Clothing", "Formal and casual wear for women", clothing);
        Catalog kidsClothing = createSubCatalog("Kids' Clothing", "Clothing for kids", clothing);

        clothing.setSubCatalogs(new ArrayList<>(List.of(mensClothing, womensClothing, kidsClothing)));

        // Subcatalogs for Sports
        Catalog outdoorSports = createSubCatalog("Outdoor Sports", "Equipment for outdoor sports", sports);
        Catalog indoorSports = createSubCatalog("Indoor Sports", "Equipment for indoor sports", sports);

        sports.setSubCatalogs(new ArrayList<>(List.of(outdoorSports, indoorSports)));

        // Add parent catalogs to the list
        catalogs.addAll(List.of(electronics, homeAppliances, clothing, sports));

        // Add more subcatalogs for variety
        Catalog gamingConsoles = createSubCatalog("Gaming Consoles", "Consoles and accessories", electronics);
        Catalog kitchenAppliances = createSubCatalog("Kitchen Appliances", "Blenders, toasters, and more", homeAppliances);
        Catalog fitnessEquipment = createSubCatalog("Fitness Equipment", "Treadmills and weights", sports);

        electronics.getSubCatalogs().add(gamingConsoles);
        homeAppliances.getSubCatalogs().add(kitchenAppliances);
        sports.getSubCatalogs().add(fitnessEquipment);

        catalogRepository.saveAll(catalogs);

        System.out.println("States initialized successfully.");
    }

    private Catalog createCatalog(String name, String description) {
        Catalog catalog = new Catalog();
        catalog.setName(name);
        catalog.setDescription(description);
        catalog.setSubCatalogs(new ArrayList<>());
        return catalog;
    }

    private Catalog createSubCatalog(String name, String description, Catalog parentCatalog) {
        Catalog catalog = createCatalog(name, description);
        catalog.setParentCatalog(parentCatalog);
        return catalog;
    }
}