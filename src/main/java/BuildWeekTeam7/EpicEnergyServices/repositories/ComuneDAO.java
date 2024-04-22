package BuildWeekTeam7.EpicEnergyServices.repositories;

import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComuneDAO extends JpaRepository<Comune, Long> {
    Comune findByNome(String nome);

    Comune findBySiglaProvincia(String siglaProvincia);

}
