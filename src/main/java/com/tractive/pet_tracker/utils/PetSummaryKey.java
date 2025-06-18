package com.tractive.pet_tracker.utils;

import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*  PetSummaryKey class for correct output display used in to get inZone status */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetSummaryKey {
private PetType petType;
private TrackerType trackerType;


    
    
}
