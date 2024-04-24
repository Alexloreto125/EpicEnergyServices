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
@AllArgsConstructor
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String via;
    private int civico;
    private String localita;
    private int cap;
    private String comune;

    @JsonIgnore
    @OneToMany(mappedBy = "sedeLegale", cascade = CascadeType.ALL)
    private List<Clienti> clientiSedeLegale;

    @JsonIgnore
    @OneToMany(mappedBy = "sedeOperativa", cascade = CascadeType.ALL)
    private List<Clienti> clientiSedeOperativa;
}
