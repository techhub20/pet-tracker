package com.tractive.pet_tracker.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.tractive.pet_tracker.Enums.PetType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/*Entity class for CAT which extends from parent class PET */
@Data
@Entity
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CAT")

public class Cat extends Pet{

    @Column(name = "lost_tracker", nullable = true)
    private Boolean lostTracker;

    @Override
    public PetType getPetType() {
        return PetType.CAT;
    } 
    
}
