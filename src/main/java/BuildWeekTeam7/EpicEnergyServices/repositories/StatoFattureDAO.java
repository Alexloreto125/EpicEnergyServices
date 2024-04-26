package BuildWeekTeam7.EpicEnergyServices.repositories;

import BuildWeekTeam7.EpicEnergyServices.entities.StatoFatture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatoFattureDAO extends JpaRepository<StatoFatture, Long> {
    StatoFatture findByNome(String nome);
}
