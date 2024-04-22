package BuildWeekTeam7.EpicEnergyServices.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "clienti")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Clienti {

    //Attributi
    @Id
    private String partitaIva;

    private String ragioneSociale;
    private String email;
    private long telefono;
    private String pec;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private long fatturatoAnnuo;
    private String sedeLegale;
    private String sedeOperativa;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private long telefonoContatto;
    private String logoAziendale;

    //Costruttore
    public Clienti(String partitaIva, String ragioneSociale, String email, long telefono, String pec, LocalDate dataInserimento, LocalDate dataUltimoContatto, long fatturatoAnnuo, String sedeLegale, String sedeOperativa, String emailContatto, String nomeContatto, String cognomeContatto, long telefonoContatto, String logoAziendale) {
        this.partitaIva = partitaIva;
        this.ragioneSociale = ragioneSociale;
        this.email = email;
        this.telefono = telefono;
        this.pec = pec;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuo = fatturatoAnnuo;
        this.sedeLegale = sedeLegale;
        this.sedeOperativa = sedeOperativa;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;

    }
}
