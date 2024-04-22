package BuildWeekTeam7.EpicEnergyServices.repositories;
import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ClienteDAO extends JpaRepository<Clienti, String> {

    // Filtriamo tramite i metodi di JPA in base al fatturato annuo, che va da un minimo a un massimo
    List<Clienti> findByFatturatoAnnuoGreaterThanEqualAndFatturatoAnnuoLessThanEqual(long fatturatoMin, long fatturatoMax);

    List<Clienti> findByDataInserimentoGreaterThanEqualAndDataInserimentoLessThanEqual(LocalDate dataInizio, LocalDate dataFine);

    List<Clienti> findByDataUltimoContattoGreaterThanEqualAndDataUltimoContattoLessThanEqual(LocalDate dataInizio, LocalDate dataFine);

    List<Clienti> findByRagioneSocialeContainingIgnoreCase(String nomeParziale);
}
