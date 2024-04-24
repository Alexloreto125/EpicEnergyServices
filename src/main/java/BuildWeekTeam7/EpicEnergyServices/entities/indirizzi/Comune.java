package BuildWeekTeam7.EpicEnergyServices.entities.indirizzi;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Comuni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comune;

    @Column(name = "provincia")
    private String nomeProvincia;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

}
