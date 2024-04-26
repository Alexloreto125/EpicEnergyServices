package BuildWeekTeam7.EpicEnergyServices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fatture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long numero;

    private LocalDate data;

    private double importo;

    @ManyToOne
    @JoinColumn(name = "stato_id")
    private StatoFatture stato;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "clienti_pIva")
    private Clienti clienti;

    public Fatture(LocalDate data, double importo, StatoFatture stato, Clienti clienti) {
        this.data = data;
        this.importo = importo;
        this.stato = stato;
        this.clienti = clienti;
    }

}
