package gr.marag.army.springboot.controller;


import gr.marag.army.springboot.payload.SoldierDTO;
import gr.marag.army.springboot.service.SoldierService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SoldierController {

    private SoldierService soldierService;

    public SoldierController(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    @PostMapping("/camps/{campId}/soldiers")
    public ResponseEntity<SoldierDTO> createSoldier(@PathVariable(value = "campId") long campId,
                                                    @Valid @RequestBody SoldierDTO soldierDTO){
        return new ResponseEntity<>(soldierService.createSoldier(campId, soldierDTO), HttpStatus.CREATED);
    }

    @GetMapping("/camps/{campId}/soldiers")
    public List<SoldierDTO> getSoldiersByCampId(@PathVariable(value = "campId") Long campId){
        return soldierService.getSoldiersByCampId(campId);

    }

    @GetMapping("/camps/{campId}/soldiers/{id}")
    public ResponseEntity<SoldierDTO> getSoldierById(@PathVariable(value = "campId") Long campId,
                                                     @PathVariable(value = "id") Long soldierId){
        SoldierDTO soldierDTO = soldierService.getSoldierById(campId, soldierId);
        return new ResponseEntity<>(soldierDTO, HttpStatus.OK);
    }

    @PutMapping("/camps/{campId}/soldiers/{id}")
    public ResponseEntity<SoldierDTO> updateSoldier(@PathVariable(value = "campId") Long campId,
                                                    @PathVariable(value = "id") Long soldierId,
                                                    @Valid @RequestBody SoldierDTO soldierDTO){
        SoldierDTO updatedSoldier = soldierService.updateSoldier(campId, soldierId, soldierDTO);
        return new ResponseEntity<>(updatedSoldier, HttpStatus.OK);
    }

    @DeleteMapping("/camps/{campId}/soldiers/{id}")
    public ResponseEntity<String> deleteSoldier(@PathVariable(value = "campId") Long campId,
                                                @PathVariable(value = "id") Long soldierId){
        soldierService.deleteSoldier(campId, soldierId);
        return new ResponseEntity<>("Soldier deleted successfully", HttpStatus.OK);
    }
}

