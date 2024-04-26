package BuildWeekTeam7.EpicEnergyServices.repositories;

import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Comune;
import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinciaDAO extends JpaRepository<Provincia, Long> {
    Provincia findByProvincia(String nome);

    Provincia findByRegione(String nomeProvincia);

//    List<Provincia> findAllProvincia();
//    Provincia findByNome(String nome);
}
