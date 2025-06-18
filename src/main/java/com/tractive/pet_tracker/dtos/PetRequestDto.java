package com.tractive.pet_tracker.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/* DTO class for pet Entities*/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetRequestDto {

    private TrackerType trackerType;
    private PetType petType;
    private Integer ownerId;
    private Boolean inZone;
    
    //only for pet 'CAT'
    private Boolean lostTracker;



    @JsonInclude(JsonInclude.Include.NON_NULL)   // As dogs do not have lostTracker field, do not include in JSON response 
    public Boolean getLostTracker() {
        if (petType == PetType.DOG) {          
            return null;
        }
        return lostTracker;
    }

    
}
