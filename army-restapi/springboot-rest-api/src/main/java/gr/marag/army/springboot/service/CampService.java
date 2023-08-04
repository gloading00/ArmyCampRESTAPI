package gr.marag.army.springboot.service;


import gr.marag.army.springboot.entity.Camp;
import gr.marag.army.springboot.payload.CampDTO;
import gr.marag.army.springboot.payload.CampResponse;

import java.util.List;

public interface CampService {
    CampDTO createCamp(CampDTO campDTO);

    CampResponse getAllCamps(int pageNo, int pageSize, String sortBy, String sortDir);

    CampDTO getCampById(long id);

    CampDTO updateCamp(CampDTO campDTO, long id);

    void deleteCampById(long id);

    List<CampDTO> getCampsByType(Long typeId);

}
