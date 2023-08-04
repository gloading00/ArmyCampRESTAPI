package gr.marag.army.springboot.repository;

import gr.marag.army.springboot.entity.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SoldierRepository extends JpaRepository<Soldier, Long> {
    List<Soldier> findByCampId(long campId);
}
