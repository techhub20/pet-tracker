package com.tractive.pet_tracker.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.tractive.pet_tracker.Enums.PetType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/*Entity class for DOG which extends from parent class PET */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("DOG")

public class Dog extends Pet {

    @Override
    public PetType getPetType() {
        return PetType.DOG;
    }

}
