package gr.marag.army.springboot.service;

import gr.marag.army.springboot.payload.CampTypeDTO;

import java.util.List;

public interface CampTypeService {
    CampTypeDTO addCampType(CampTypeDTO campTypeDTO);

    CampTypeDTO getCampType(Long typeId);

    List<CampTypeDTO> getAllCampTypes();

    CampTypeDTO updateCampType(CampTypeDTO campTypeDTO, Long typeId);

    void deleteCampType(Long typeId);

}
