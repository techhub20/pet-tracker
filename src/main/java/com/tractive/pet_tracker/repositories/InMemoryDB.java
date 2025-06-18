package com.tractive.pet_tracker.repositories;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import com.tractive.pet_tracker.model.Pet;


/**
 * In-memory implementation of PetDB interface.
 * 
 */
public class InMemoryDB implements PetDB {

    // hold pets in memory which is thread safe
    private final CopyOnWriteArrayList<Pet> petDBRepo = new CopyOnWriteArrayList<>();

    @Override
    public void save(Pet pet) {
        petDBRepo.add(pet);
    }

    @Override
    public List<Pet> findAll() {
        return List.copyOf(petDBRepo);
    }

    @Override
    public List<Pet> findByInZone(boolean inZone) {
        return petDBRepo.stream()
                .filter(pet -> pet.getInZone() != null && pet.getInZone() == inZone)
                .collect(Collectors.toList());
    }

    // clear memory
    public void clear() {
        petDBRepo.clear();
    }

}
