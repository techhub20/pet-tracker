package com.tractive.pet_tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tractive.pet_tracker.model.Pet;

/* Repository Interface to interact with JPA repo to provide crud operations */

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

      /**
     * Finds all pets filtered by their inZone status.
     *
     * @param inZone boolean flag indicating if pet is in zone
     * @return list of pets matching the inZone status
     */
    List<Pet> findByInZone(boolean inZone);

}
