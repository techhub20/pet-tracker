package com.tractive.pet_tracker.UnitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;
import com.tractive.pet_tracker.dtos.PetRequestDto;
import com.tractive.pet_tracker.exceptions.InvalidTrackerTypeException;
import com.tractive.pet_tracker.model.Dog;
import com.tractive.pet_tracker.repositories.PetDB;
import com.tractive.pet_tracker.services.PetService;

public class PetServiceTest {

    @Mock
    private PetDB petRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PetService petService;

    public PetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDogPet() {
        PetRequestDto dto = new PetRequestDto(TrackerType.SMALL, PetType.DOG, 1, true, null);
        Dog dog = new Dog();
        when(modelMapper.map(dto, Dog.class)).thenReturn(dog);

        petService.addPets(dto);

        verify(petRepository, times(1)).save(dog);
    }

    @Test
    public void testAddCatPet_InvalidTrackerTypeException() {
        PetRequestDto dto = new PetRequestDto(TrackerType.MEDIUM, PetType.CAT, 2, false, true);

        Exception exception = assertThrows(InvalidTrackerTypeException.class, () -> {
            petService.addPets(dto);
        });

        assertEquals("Cats can only have SMALL or BIG tracker types.", exception.getMessage());
    }

    @Test
    public void testGetAllPetData_emptyList() {
        when(petRepository.findAll()).thenReturn(Collections.emptyList());
        assertTrue(petService.getAllPetData().isEmpty());
    }

}
