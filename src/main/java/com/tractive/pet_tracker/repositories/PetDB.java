package com.tractive.pet_tracker.repositories;

import java.util.List;

import com.tractive.pet_tracker.model.Pet;

/** 
 * Repository interface for CRUD operations on Pet entities.
 * 
 */ 

public interface PetDB {

    void save(Pet pet);

    List<Pet> findAll();

    List<Pet> findByInZone(boolean inZone);

    // clear off all the pet entities from the repo
    default void clear() {
        throw new UnsupportedOperationException("Not implemented");
    }

}
