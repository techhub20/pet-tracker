package com.tractive.pet_tracker.utils;

import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*  TrackerGroupKey class for correct output display  */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class TrackerGroupKey {
      private final PetType petType;
    private final TrackerType trackerType;

}
