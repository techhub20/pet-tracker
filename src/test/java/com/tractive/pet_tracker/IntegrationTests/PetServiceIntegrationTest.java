package com.tractive.pet_tracker.IntegrationTests;

import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;
import com.tractive.pet_tracker.dtos.PetRequestDto;
import com.tractive.pet_tracker.dtos.PetResponseSummaryDto;
import com.tractive.pet_tracker.exceptions.InvalidTrackerTypeException;
import com.tractive.pet_tracker.repositories.PetDB;
import com.tractive.pet_tracker.services.PetService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
public class PetServiceIntegrationTest {

    @Autowired
    private PetService petService;

    @Autowired
    private PetDB petDB;

    
    @BeforeEach
    void cleanUpDatabase() {
        petDB.clear();;
    }

    @Test
    public void testAddDogAndGetResult() {
        PetRequestDto dogDto = new PetRequestDto(TrackerType.SMALL, PetType.DOG, 100, true, null);
        petService.addPets(dogDto);

        List<PetRequestDto> allPets = petService.getAllPetData();

        assertEquals(1, allPets.size());
        assertEquals(PetType.DOG, allPets.get(0).getPetType());
        assertEquals(100, allPets.get(0).getOwnerId());
    }

    @Test
    public void testAddCatAndGetinZoneStatus() {
        PetRequestDto catDto = new PetRequestDto(TrackerType.BIG, PetType.CAT, 200, false, false);
        petService.addPets(catDto);

        List<PetResponseSummaryDto> summaries = petService.getPetsZoneStautsSummary(false);

        assertEquals(1, summaries.size());
        assertEquals(PetType.CAT, summaries.get(0).getPetType());
        assertEquals(TrackerType.BIG, summaries.get(0).getTrackerType());
        assertEquals(1, summaries.get(0).getCount());
    }

    @Test
    public void testAddCat_InvalidTrackerType() {
        PetRequestDto invalidCat = new PetRequestDto(TrackerType.MEDIUM, PetType.CAT, 300, true, true);

        Exception exception = assertThrows(InvalidTrackerTypeException.class, () -> {
            petService.addPets(invalidCat);
        });

        String expectedMessage = "Cats can only have SMALL or BIG tracker types.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
