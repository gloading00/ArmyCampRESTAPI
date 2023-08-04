package gr.marag.army.springboot.controller;

import gr.marag.army.springboot.payload.CampTypeDTO;
import gr.marag.army.springboot.service.CampTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campTypes")
public class CampTypeController {

    private CampTypeService campTypeService;

    public CampTypeController(CampTypeService campTypeService) {
        this.campTypeService = campTypeService;
    }

    // Build Add CampType REST API
    @PostMapping
    public ResponseEntity<CampTypeDTO> addCampType(@RequestBody CampTypeDTO campTypeDTO) {
        CampTypeDTO savedCampType = campTypeService.addCampType(campTypeDTO);
        return new ResponseEntity<>(savedCampType, HttpStatus.CREATED);
    }

    // Build Get CampType REST API
    @GetMapping("{id}")
    public ResponseEntity<CampTypeDTO> getCampType(@PathVariable("id") Long typeId) {
        CampTypeDTO campTypeDTO = campTypeService.getCampType(typeId);
        return ResponseEntity.ok(campTypeDTO);
    }

    // Build Get All CampTypes REST API
    @GetMapping
    public ResponseEntity<List<CampTypeDTO>> getCampTypes() {
        return ResponseEntity.ok(campTypeService.getAllCampTypes());
    }

    // Build Update CampType REST API
    @PutMapping("{id}")
    public ResponseEntity<CampTypeDTO> updateCampType(@RequestBody CampTypeDTO campTypeDTO,
                                                      @PathVariable("id") Long typeId) {
        return ResponseEntity.ok(campTypeService.updateCampType(campTypeDTO, typeId));
    }

    // Build Delete CampType REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCampType(@PathVariable("id") Long typeId) {
        campTypeService.deleteCampType(typeId);
        return ResponseEntity.ok("CampType deleted successfully!.");
    }
}




