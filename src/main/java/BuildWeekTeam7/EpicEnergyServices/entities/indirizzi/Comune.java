package BuildWeekTeam7.EpicEnergyServices.entities.indirizzi;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Comuni")
@Getter
@Setter
@NoArgsConstructor
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nomeComune;

    @Column(name = "provincia")
    private String nomeProvincia;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    @OneToMany(mappedBy = "comune", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Indirizzo> indirizzi = new ArrayList<>();

    public Comune(String nomeComune, String nomeProvincia) {
        this.nomeComune = nomeComune;
        this.nomeProvincia = nomeProvincia;
    }

}
