package com.tractive.pet_tracker.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tractive.pet_tracker.dtos.PetRequestDto;
import com.tractive.pet_tracker.dtos.PetResponseSummaryDto;
import com.tractive.pet_tracker.services.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/* Rest Controller class with API End Points */

@RestController
@RequiredArgsConstructor
@Tag(name = "Pet Tracking", description = "API for managing pet tracking data") //for swagger API config
public class PetController {

    private final PetService petService;



     /**
     * Handles GET requests to retrieve all pets.
     *
     * @return ResponseEntity containing list of PetRequestDto objects
     */

    @GetMapping("/pets")
    @Operation(summary = "Get all pets", description = "Returns list of all stored pets") //for swagger API config
    public ResponseEntity<List<PetRequestDto>> getPetData() {
        List<PetRequestDto> allPetData = petService.getAllPetData();
        return ResponseEntity.ok(allPetData);
    }



     /**
     * Handles Post requests to add pets.
     *
     * @return ResponseEntity for HttpStatus CREATED
     */
    @PostMapping("/addpet")
    @Operation(summary = "Add a new pet", 
     description = "Adds a new cat or dog. 'lostTracker' must be used only for CAT." ,
    responses = {
            @ApiResponse(responseCode = "201", description = "Pet created"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    }) //for swagger API config
   
    public ResponseEntity<Void> addPets(
        //for swagger api doc
         @io.swagger.v3.oas.annotations.parameters.RequestBody(
        description = "JSON payload to add a pet . If petType is DOG, do NOT include lostTracker' in the request" ,
            required = true
        )
        @RequestBody PetRequestDto petRequestDto) {
        petService.addPets(petRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



     /**
     * Handles GET requests to retrieve inZone Status .
     *
     * @return ResponseEntity with pet summary list
     */

    @GetMapping("/outside-zone-status")
    @Operation(summary = "Get summary by zone status with inZone -'false'", description = "Returns grouped count of pets outside power saving zone") //for swagger API config
    public List<PetResponseSummaryDto> getPetsOutsideZoneSummary() {
        return petService.getPetsZoneStautsSummary(false);       
    }

     /**
     * Handles GET requests to retrieve to retrieve inZone Status when given inZone param ("true/false")
     *
     * @return ResponseEntity with pet summary list
     */
    @GetMapping("/inzone-status")
    @Operation(summary = "Get summary by zone status", description = "Returns grouped count of pets inside/outside power saving zone") //for swagger API config
    public List<PetResponseSummaryDto> getPetsZoneSummary(
            @RequestParam(defaultValue = "false") boolean inZone) {
        return petService.getPetsZoneStautsSummary(inZone);
    }
}
