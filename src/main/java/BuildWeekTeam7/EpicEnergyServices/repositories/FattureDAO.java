package BuildWeekTeam7.EpicEnergyServices.repositories;

import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import BuildWeekTeam7.EpicEnergyServices.entities.Fatture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FattureDAO extends JpaRepository<Fatture, Long> {
    List<Fatture> findByClienti (Clienti clienti);
    List<Fatture> findByStato (String stato);
    List<Fatture> findByData (LocalDate data);
    @Query("SELECT f FROM Fatture f WHERE f.importo BETWEEN :importoMin AND :importoMax")
    List<Fatture> filterByImporto (@Param("importoMin") double prezzoMin, @Param("importoMax") double importoMax);

}
