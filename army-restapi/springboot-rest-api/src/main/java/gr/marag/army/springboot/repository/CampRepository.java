package gr.marag.army.springboot.repository;

import gr.marag.army.springboot.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampRepository extends JpaRepository<Camp, Long> {

    List<Camp> findByCampTypeId(Long CampTypeId);
}
