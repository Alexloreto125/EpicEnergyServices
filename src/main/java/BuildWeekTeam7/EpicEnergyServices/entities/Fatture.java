package BuildWeekTeam7.EpicEnergyServices.entities;

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

    private String stato;

    @ManyToOne
    @JoinColumn(name = "clienti_pIva")
    private Clienti clienti;

    public Fatture(LocalDate data, double importo, String stato) {
        this.data = data;
        this.importo = importo;
        this.stato = stato;
    }

}
