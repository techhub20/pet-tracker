package com.tractive.pet_tracker.IntegrationTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;
import com.tractive.pet_tracker.dtos.PetRequestDto;
import com.tractive.pet_tracker.repositories.PetDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest 
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetDB petDB;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        //reset db before all test run
        petDB.clear(); 
    }

      @Test
    public void testGetAllPets_Empty() throws Exception {
        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


    @Test
    public void testAddPet_Created() throws Exception {
        PetRequestDto dto = new PetRequestDto(TrackerType.SMALL, PetType.DOG, 101, false, null);

        mockMvc.perform(post("/addpet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

  
    @Test
    public void testInZoneStatus_Summary() throws Exception {
        // Add a dog outside zone
        PetRequestDto dto1 = new PetRequestDto(TrackerType.SMALL, PetType.DOG, 111, false, null);
        // Add a cat in zone
        PetRequestDto dto2 = new PetRequestDto(TrackerType.BIG, PetType.CAT, 112, true, false);

        mockMvc.perform(post("/addpet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto1)))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/addpet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto2)))
                .andExpect(status().isCreated());

        // perform query
        mockMvc.perform(get("/inzone-status").param("inZone", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].petType").value("DOG"))
                .andExpect(jsonPath("$[0].trackerType").value("SMALL"))
                .andExpect(jsonPath("$[0].count").value(1));
    }
}

