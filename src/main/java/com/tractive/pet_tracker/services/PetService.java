package com.tractive.pet_tracker.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;
import com.tractive.pet_tracker.dtos.PetRequestDto;
import com.tractive.pet_tracker.dtos.PetResponseSummaryDto;
import com.tractive.pet_tracker.exceptions.InvalidTrackerTypeException;
import com.tractive.pet_tracker.model.Cat;
import com.tractive.pet_tracker.model.Dog;
import com.tractive.pet_tracker.model.Pet;
import com.tractive.pet_tracker.repositories.PetDB;
import com.tractive.pet_tracker.utils.TrackerGroupKey;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/* Service class which implements business logic */

@Service
@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final PetDB petDB;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(PetService.class);

    /**
     * Adds a pet to the DB
     *
     * @param petRequestDto the DTO contans pet info
     * @throws IllegalArgumentException    if petType or trackerType is null or not
     *                                     supported
     * @throws InvalidTrackerTypeException if a cat is not supported tracker type
     */

    public void addPets(PetRequestDto petRequestDto) {

        logger.info("Received request to add pet: {}", petRequestDto);
        // null check for petType
        PetType petType = Optional.ofNullable(petRequestDto.getPetType())
                .orElseThrow(() -> new IllegalArgumentException("Pet type is required"));

        // null check for trackerType
        TrackerType trackerType = Optional.ofNullable(petRequestDto.getTrackerType())
                .orElseThrow(() -> new InvalidTrackerTypeException("Tracker type is required"));

        // validation check-InvalidTrackerTypeException
        if (petType == PetType.CAT && trackerType == TrackerType.MEDIUM) {
            logger.warn("Invalid tracker type {} for pet type CAT", trackerType);
            throw new InvalidTrackerTypeException("Cats can only have SMALL or BIG tracker types.");
        }

        // add pets based on petType
        if (petType == PetType.CAT) {
            Cat cat = modelMapper.map(petRequestDto, Cat.class);
            cat.setTrackerType(trackerType);
            petDB.save(cat);
        } else if (petType == PetType.DOG) {
            Dog dog = modelMapper.map(petRequestDto, Dog.class);
            dog.setTrackerType(trackerType);
            petDB.save(dog);
        } else {
            throw new IllegalArgumentException("pet type not supported: " + petType);
        }
    }

    /**
     * Get all pet data from DB
     * 
     */

    // get all petsData
    public List<PetRequestDto> getAllPetData() {
        logger.info("Get All Pet Data :");
        List<PetRequestDto> petData = petDB.findAll().stream()
                .map(p -> modelMapper.map(p, PetRequestDto.class)).collect(Collectors.toList());
        return petData;
    }

    /**
     * Get all pet outside Zone Summarydata from DB
     * 
     * @param boolean inZone
     * 
     */

    public List<PetResponseSummaryDto> getPetsZoneStautsSummary(boolean inZone) {
        logger.info("Get Pet inZone status summary :");  
        return getAllZoneData(inZone);
    }

   

    private List<PetResponseSummaryDto> getAllZoneData(boolean inZone) {

        // get all pets inZoneStatus
        List<Pet> pets = petDB.findByInZone(inZone);

        // Step 2: Group by petType and trackerType
        Map<TrackerGroupKey, Long> groupedData = pets.stream()
                .collect(Collectors.groupingBy(
                        p -> new TrackerGroupKey(p.getPetType(), p.getTrackerType()),
                        Collectors.counting()));

        // Step 3: Convert to DTO list
        return groupedData.entrySet().stream()
                .map(entry -> new PetResponseSummaryDto(
                        entry.getKey().getPetType(),
                        entry.getKey().getTrackerType(),
                        Math.toIntExact(entry.getValue())))
                .collect(Collectors.toList());

    }

}
