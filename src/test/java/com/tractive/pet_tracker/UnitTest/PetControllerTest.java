package com.tractive.pet_tracker.UnitTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tractive.pet_tracker.Enums.PetType;
import com.tractive.pet_tracker.Enums.TrackerType;
import com.tractive.pet_tracker.controller.PetController;
import com.tractive.pet_tracker.dtos.PetRequestDto;
import com.tractive.pet_tracker.dtos.PetResponseSummaryDto;
import com.tractive.pet_tracker.services.PetService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddPets_returnsCreated() throws Exception {
        PetRequestDto dto = new PetRequestDto(TrackerType.SMALL, PetType.DOG, 1, true, null);

        mockMvc.perform(post("/addpet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetPetData_returnsEmptyList() throws Exception {
        Mockito.when(petService.getAllPetData()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetPetsinZoneStatus() throws Exception {
        PetResponseSummaryDto summaryDto = new PetResponseSummaryDto(PetType.DOG, TrackerType.SMALL, 3);

        Mockito.when(petService.getPetsZoneStautsSummary(false))
                .thenReturn(Collections.singletonList(summaryDto));

        mockMvc.perform(get("/inzone-status")
                .param("inZone", "false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].petType").value("DOG"))
                .andExpect(jsonPath("$[0].trackerType").value("SMALL"))
                .andExpect(jsonPath("$[0].count").value(3));
    }

}
