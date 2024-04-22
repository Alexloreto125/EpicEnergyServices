package BuildWeekTeam7.EpicEnergyServices.repository;

import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Comune;
import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinciaDAO extends JpaRepository<Provincia, Long> {
    Comune findByNome(String nome);
}
