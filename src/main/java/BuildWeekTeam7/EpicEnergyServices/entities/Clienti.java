package BuildWeekTeam7.EpicEnergyServices.entities;

import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Indirizzo;
import BuildWeekTeam7.EpicEnergyServices.enums.TipoAzienda;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private TipoAzienda tipoAzienda;

    private String email;
    private long telefono;
    private String pec;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private long fatturatoAnnuo;

    @ManyToOne
   @JoinColumn(name = "sede_legale_id")
    private Indirizzo sedeLegale;

    @ManyToOne
   @JoinColumn(name = "sede_operativa_id")
    private Indirizzo sedeOperativa;

    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private long telefonoContatto;
    private String logoAziendale;

    @JsonIgnore
    @OneToMany(mappedBy = "clienti")
    private List<Fatture> fattureList;

    //Costruttore
    public Clienti(String partitaIva, String ragioneSociale, TipoAzienda TipoAzienda, String email, long telefono, String pec, LocalDate dataInserimento, LocalDate dataUltimoContatto, long fatturatoAnnuo, Indirizzo sedeLegale, Indirizzo sedeOperativa, String emailContatto, String nomeContatto, String cognomeContatto, long telefonoContatto, String logoAziendale) {
        this.partitaIva = partitaIva;
        this.ragioneSociale = ragioneSociale;
        this.tipoAzienda = TipoAzienda;
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

    public Clienti(String email) {
        this.email = email;
    }
}
