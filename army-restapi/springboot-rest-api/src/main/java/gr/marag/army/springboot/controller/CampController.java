package gr.marag.army.springboot.controller;

import gr.marag.army.springboot.payload.CampDTO;
import gr.marag.army.springboot.payload.CampResponse;
import gr.marag.army.springboot.service.CampService;
import gr.marag.army.springboot.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/camps")
@Tag(
        name = "CRUD REST APIs for Camp Resource"
)
public class CampController {
    private CampService campService;

    public CampController(CampService campService) {
        this.campService = campService;
    }

    // create camp rest api
    @Operation(
            summary = "Create Camp REST API",
            description = "Create Camp REST API is used to save camp into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http status 201 CREATED"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CampDTO> createCamp(@Valid @RequestBody CampDTO campDTO){
        return new ResponseEntity<>(campService.createCamp(campDTO), HttpStatus.CREATED);
    }

    // get all camps rest api
    @Operation(
            summary = "Get all Camps REST API",
            description = "Get all Camps REST API is used to get all the camps from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"

    )
    @GetMapping
    public CampResponse getAllCamps(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return campService.getAllCamps(pageNo, pageSize, sortBy, sortDir);

    }

    // get camp by id
    @Operation(
            summary = "Get Camp by id REST API",
            description = "Get Camp by id REST API is used to get a single camp from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"

    )
    @GetMapping("/{id}")
    public ResponseEntity<CampDTO> getCampById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(campService.getCampById(id));
    }

    // update camp by id rest api
    @Operation(
            summary = "Update Camp REST API",
            description = "Update Camp REST API is used to update a particular camp from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CampDTO> updatePost(@Valid @RequestBody CampDTO campDTO, @PathVariable(name = "id") long id){

        CampDTO campResponse = campService.updateCamp(campDTO, id);

        return new ResponseEntity<>(campResponse, HttpStatus.OK);
    }


    // delete camp rest api
    @Operation(
            summary = "Delete Camp REST API",
            description = "Delete Camp REST API is used to delete a particular camp from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http status 200 SUCCESS"

    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCamp(@PathVariable(name = "id") long id){

        campService.deleteCampById(id);

        return new ResponseEntity<>("Camp entity deleted successfully.", HttpStatus.OK);
    }

    @GetMapping("/type/{id}")
    public ResponseEntity<List<CampDTO>> getCampsByType(@PathVariable("id") Long typeId){
        List<CampDTO> campDTOs = campService.getCampsByType(typeId);
        return ResponseEntity.ok(campDTOs);
    }

}
