package BuildWeekTeam7.EpicEnergyServices.repositories;

import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Comune;
import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComuneDAO extends JpaRepository<Comune, Long> {
    Comune findByComune(String nome);
   List<Comune> findByNomeProvincia(Provincia provincia);

}
