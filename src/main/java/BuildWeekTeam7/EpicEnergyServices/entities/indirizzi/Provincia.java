package BuildWeekTeam7.EpicEnergyServices.entities.indirizzi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "province")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provincia;
    private String sigla;
    private String regione;



    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comune> comuni = new ArrayList<>();
}