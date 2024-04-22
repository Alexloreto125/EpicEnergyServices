package BuildWeekTeam7.EpicEnergyServices.repositories;

import BuildWeekTeam7.EpicEnergyServices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
}
