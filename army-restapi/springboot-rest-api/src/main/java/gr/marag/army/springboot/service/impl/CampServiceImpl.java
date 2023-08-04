package gr.marag.army.springboot.service.impl;


import gr.marag.army.springboot.entity.Camp;
import gr.marag.army.springboot.entity.CampType;
import gr.marag.army.springboot.exception.ResourceNotFoundException;
import gr.marag.army.springboot.payload.CampDTO;
import gr.marag.army.springboot.payload.CampResponse;
import gr.marag.army.springboot.repository.CampRepository;
import gr.marag.army.springboot.repository.CampTypeRepository;
import gr.marag.army.springboot.service.CampService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CampServiceImpl implements CampService {

    private CampRepository campRepository;

    private ModelMapper mapper;

    private CampTypeRepository typeRepository;

    public CampServiceImpl(CampRepository campRepository, ModelMapper mapper,
                           CampTypeRepository typeRepository) {
        this.campRepository = campRepository;
        this.mapper = mapper;
        this.typeRepository = typeRepository;
    }

    @Override
    public CampDTO createCamp(CampDTO campDTO) {

        CampType type = typeRepository.findById(campDTO.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Camp", "id", campDTO.getTypeId()));

        Camp camp = mapToEntity(campDTO);
        camp.setCampType(type);
        Camp newCamp = campRepository.save(camp);

        CampDTO campResponse = mapToDTO(newCamp);
        return campResponse;

    }
    @Override
    public CampResponse getAllCamps(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Camp> camps = campRepository.findAll(pageable);

        List<Camp> listOfCamps = camps.getContent();

        List<CampDTO> content = listOfCamps.stream().map(camp -> mapToDTO(camp)).collect(Collectors.toList());

        CampResponse campResponse = new CampResponse();
        campResponse.setContent(content);
        campResponse.setPageNo(camps.getNumber());
        campResponse.setPageSize(camps.getSize());
        campResponse.setTotalElements(camps.getTotalElements());
        campResponse.setTotalPages(camps.getTotalPages());
        campResponse.setLast(camps.isLast());

        return campResponse;

    }

    @Override
    public CampDTO getCampById(long id) {
        Camp camp = campRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Camp", "id", id));
        return mapToDTO(camp);
    }

    @Override
    public CampDTO updateCamp(CampDTO campDTO, long id) {
        Camp camp = campRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        CampType type = typeRepository.findById(campDTO.getTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", campDTO.getTypeId()));


        camp.setName(campDTO.getName());
        camp.setDescription(campDTO.getDescription());
        camp.setLocation(campDTO.getLocation());
        camp.setCampType(type);
        Camp updatedCamp = campRepository.save(camp);
        return mapToDTO(updatedCamp);
    }

    @Override
    public void deleteCampById(long id) {
        Camp camp = campRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        campRepository.delete(camp);

    }

    @Override
    public List<CampDTO> getCampsByType(Long typeId) {
        CampType campType = typeRepository.findById(typeId)
                .orElseThrow(() -> new ResourceNotFoundException("CampType", "id", typeId));

        List<Camp> camps = campRepository.findByCampTypeId(typeId);

        return camps.stream().map((camp) -> mapToDTO(camp))
                .collect(Collectors.toList());
    }

    private Camp mapToEntity(CampDTO campDTO){
        Camp camp = mapper.map(campDTO, Camp.class);
        return camp;
    }
    private CampDTO mapToDTO(Camp camp){
        CampDTO campDTO = mapper.map(camp, CampDTO.class);
        return campDTO;
    }
}
