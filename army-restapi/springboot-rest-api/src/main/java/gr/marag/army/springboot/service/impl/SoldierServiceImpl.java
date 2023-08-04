package gr.marag.army.springboot.service.impl;

import gr.marag.army.springboot.entity.Camp;
import gr.marag.army.springboot.entity.Soldier;
import gr.marag.army.springboot.exception.ArmyAPIException;
import gr.marag.army.springboot.exception.ResourceNotFoundException;
import gr.marag.army.springboot.payload.SoldierDTO;
import gr.marag.army.springboot.repository.CampRepository;
import gr.marag.army.springboot.repository.SoldierRepository;
import gr.marag.army.springboot.service.SoldierService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SoldierServiceImpl implements SoldierService {

    private SoldierRepository soldierRepository;
    private CampRepository campRepository;

    private ModelMapper mapper;


    public SoldierServiceImpl(SoldierRepository soldierRepository, CampRepository campRepository, ModelMapper mapper) {
        this.soldierRepository = soldierRepository;
        this.campRepository = campRepository;
        this.mapper = mapper;
    }

    @Override
    public SoldierDTO createSoldier(long campId, SoldierDTO soldierDTO) {
        Soldier soldier = mapToEntity(soldierDTO);

        Camp camp = campRepository.findById(campId).orElseThrow(
                () -> new ResourceNotFoundException("Camp", "Id", campId));
        soldier.setCamp(camp);

        Soldier newSoldier = soldierRepository.save(soldier);

        return mapToDTO(newSoldier);
    }

    @Override
    public List<SoldierDTO> getSoldiersByCampId(long campId) {
        List<Soldier> soldiers = soldierRepository.findByCampId(campId);

        return soldiers.stream().map(soldier -> mapToDTO(soldier)).collect(Collectors.toList());

    }

    @Override
    public SoldierDTO getSoldierById(Long campId, Long soldierId) {
        Camp camp = campRepository.findById(campId).orElseThrow(
                ()-> new ResourceNotFoundException("Camp", "Id", campId));

        Soldier soldier = soldierRepository.findById(soldierId).orElseThrow(
                ()-> new ResourceNotFoundException("Soldier", "Id", soldierId));
        if(!soldier.getCamp().getId().equals(camp.getId())){
            throw new ArmyAPIException(HttpStatus.BAD_REQUEST, "Soldier does not belong to the camp");
        }

        return mapToDTO(soldier);
    }

    @Override
    public SoldierDTO updateSoldier(Long campId, long soldierId, SoldierDTO soldierRequest) {
        Camp camp = campRepository.findById(campId).orElseThrow(
                ()-> new ResourceNotFoundException("Camp", "Id", campId));

        Soldier soldier = soldierRepository.findById(soldierId).orElseThrow(
                ()-> new ResourceNotFoundException("Soldier", "Id", soldierId));

        if(!soldier.getCamp().getId().equals(camp.getId())){
            throw new ArmyAPIException(HttpStatus.BAD_REQUEST, "Soldier does not belong to the camp");
        }

        soldier.setFirstName(soldierRequest.getFirstName());
        soldier.setLastName(soldierRequest.getLastName());
        soldier.setAge(soldierRequest.getAge());
        soldier.setBodyStatus(soldierRequest.getBodyStatus());

        Soldier updatedSoldier = soldierRepository.save(soldier);
        return mapToDTO(updatedSoldier);




    }

    @Override
    public void deleteSoldier(Long campId, Long soldierId) {
        Camp camp = campRepository.findById(campId).orElseThrow(
                ()-> new ResourceNotFoundException("Camp", "Id", campId));

        Soldier soldier = soldierRepository.findById(soldierId).orElseThrow(
                ()-> new ResourceNotFoundException("Soldier", "Id", soldierId));

        if(!soldier.getCamp().getId().equals(camp.getId())){
            throw new ArmyAPIException(HttpStatus.BAD_REQUEST, "Soldier does not belong to the camp");
        }
        soldierRepository.delete(soldier);
    }


    private Soldier mapToEntity(SoldierDTO soldierDTO){
        Soldier soldier = mapper.map(soldierDTO, Soldier.class);
        return soldier;
    }
    private SoldierDTO mapToDTO(Soldier soldier){
        SoldierDTO soldierDTO = mapper.map(soldier, SoldierDTO.class);
        return soldierDTO;
    }
}
