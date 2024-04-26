package BuildWeekTeam7.EpicEnergyServices.entities.indirizzi;


import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name ="Indirizzo")
@Getter
@Setter
@NoArgsConstructor
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String via;
    private int civico;
    private String localita;
    private int cap;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comune_id")
    private Comune comune;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @JsonIgnore
    @OneToMany(mappedBy = "sedeLegale", cascade = CascadeType.ALL)
    private List<Clienti> clientiSedeLegale;

    @JsonIgnore
    @OneToMany(mappedBy = "sedeOperativa", cascade = CascadeType.ALL)
    private List<Clienti> clientiSedeOperativa;

    public Indirizzo(String via, int civico, String localita, int cap, Comune comune, Provincia provincia) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }
}
