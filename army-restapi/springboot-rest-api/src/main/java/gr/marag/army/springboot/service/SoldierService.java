package gr.marag.army.springboot.service;

import gr.marag.army.springboot.entity.Soldier;
import gr.marag.army.springboot.payload.SoldierDTO;

import java.util.List;

public interface SoldierService {
    SoldierDTO createSoldier(long campId, SoldierDTO soldierDTO);

    List<SoldierDTO> getSoldiersByCampId(long campId);

    SoldierDTO getSoldierById(Long campId, Long soldierId);

    SoldierDTO updateSoldier(Long campId, long soldierId, SoldierDTO soldierRequest);

    void deleteSoldier(Long campId, Long soldierId);
}
