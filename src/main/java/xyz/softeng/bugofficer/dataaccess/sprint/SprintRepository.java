package xyz.softeng.bugofficer.dataaccess.sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
