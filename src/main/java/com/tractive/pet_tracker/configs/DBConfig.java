package com.tractive.pet_tracker.configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.tractive.pet_tracker.model.Pet;
import com.tractive.pet_tracker.repositories.InMemoryDB;
import com.tractive.pet_tracker.repositories.PetDB;
import com.tractive.pet_tracker.repositories.PetRepository;

@Configuration
public class DBConfig {

    @Bean
    @Profile("!test")
    public PetDB petRepositoryDBStore(PetRepository petRepository) {
        // use JPA repo as PetDB
        return new PetDB() {
            @Override
            public void save(Pet pet) { petRepository.save(pet); }

            @Override
            public List<Pet> findAll() { return petRepository.findAll(); }

            @Override
            public List<Pet> findByInZone(boolean inZone) { return petRepository.findByInZone(inZone); }
        };
    }

    @Bean
    @Profile("test")
    public PetDB inMemoryDB() {
        return new InMemoryDB();
    }
}
