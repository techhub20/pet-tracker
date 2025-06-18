package com.tractive.pet_tracker.model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*Parent Entity class  PET */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pet_type", discriminatorType = DiscriminatorType.STRING)

public abstract class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Enumerated(EnumType.STRING)
    @Column(name = "tracker_type", nullable = false)
    private TrackerType trackerType;

    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;

     @Column(name = "in_zone", nullable = false)
    private Boolean inZone = false;

  
    public abstract PetType getPetType();
    
}
