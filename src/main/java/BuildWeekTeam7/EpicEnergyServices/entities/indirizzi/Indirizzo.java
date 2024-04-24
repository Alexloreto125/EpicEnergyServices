package BuildWeekTeam7.EpicEnergyServices.entities.indirizzi;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="Indirizzo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Indirizzo {

    private String via;
    private int civico;
    private String localita;
    private int cap;
    private String comune;

    //@OneToMany
    //sede_legale

    //@OneToMany
    //sede_operativa

}
