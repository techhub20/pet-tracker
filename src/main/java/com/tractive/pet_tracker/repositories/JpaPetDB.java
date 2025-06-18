package com.tractive.pet_tracker.repositories;

import java.util.List;

import com.tractive.pet_tracker.model.Pet;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * JPA implementation of PetDB interface.
 * Uses PetRepository for DB operations.
 */

@Data
@RequiredArgsConstructor
public class JpaPetDB implements PetDB{

    private final PetRepository petRepository;

    @Override
    public void save(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> findByInZone(boolean inZone) {
         return petRepository.findByInZone(inZone);
    }
    
}
