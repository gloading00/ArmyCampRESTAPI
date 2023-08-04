package gr.marag.army.springboot.service.impl;

import gr.marag.army.springboot.entity.CampType;
import gr.marag.army.springboot.exception.ResourceNotFoundException;
import gr.marag.army.springboot.payload.CampTypeDTO;
import gr.marag.army.springboot.repository.CampTypeRepository;
import gr.marag.army.springboot.service.CampTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampTypeServiceImpl implements CampTypeService {

    private CampTypeRepository campTypeRepository;
    private ModelMapper modelMapper;

    public CampTypeServiceImpl(CampTypeRepository campTypeRepository, ModelMapper modelMapper) {
        this.campTypeRepository = campTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CampTypeDTO addCampType(CampTypeDTO campTypeDTO) {
        CampType campType = modelMapper.map(campTypeDTO, CampType.class);
        CampType savedCampType = campTypeRepository.save(campType);
        return modelMapper.map(savedCampType, CampTypeDTO.class);
    }

    @Override
    public CampTypeDTO getCampType(Long typeId) {

        CampType campType = campTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("CampType", "id", typeId));

        return modelMapper.map(campType, CampTypeDTO.class);
    }

    @Override
    public List<CampTypeDTO> getAllCampTypes() {

        List<CampType> campTypes = campTypeRepository.findAll();

        return campTypes.stream().map((campType) -> modelMapper.map(campType, CampTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CampTypeDTO updateCampType(CampTypeDTO campTypeDTO, Long typeId) {

        CampType campType = campTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("CampType", "id", typeId));

        campType.setName(campTypeDTO.getName());
        campType.setDescription(campTypeDTO.getDescription());
        campType.setId(typeId);

        CampType updatedCampType = campTypeRepository.save(campType);

        return modelMapper.map(updatedCampType, CampTypeDTO.class);
    }

    @Override
    public void deleteCampType(Long typeId) {

        CampType campType = campTypeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("CampType", "id", typeId));

        campTypeRepository.delete(campType);
    }
}
