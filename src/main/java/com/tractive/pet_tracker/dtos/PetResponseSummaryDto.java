package com.tractive.pet_tracker.dtos;

import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Dto class for getting the pet summary for inZone status */

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class PetResponseSummaryDto {
    private final PetType petType;
    private final TrackerType trackerType;
    private final int count;
}
