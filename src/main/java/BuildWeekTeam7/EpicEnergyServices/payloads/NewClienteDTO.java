package BuildWeekTeam7.EpicEnergyServices.payloads;

import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Indirizzo;
import BuildWeekTeam7.EpicEnergyServices.enums.TipoAzienda;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewClienteDTO(
        @NotEmpty(message = "ATTENZIONE! La partitaIva è obbligatoria")
        @Size(min = 11, max = 11)
        String partitaIva,
        @NotEmpty(message = "ATTENZIONE! La ragioneSociale è obbligatoria")
        String ragioneSociale,
        @NotNull(message = "Il tipo di azienda è obbligatorio")
        TipoAzienda tipoAzienda,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotNull(message = "Il numero di telefono è obbligatorio")
        long telefono,
        @NotEmpty(message = "La Pec è obbligatoria")
        @Email(message = "La Pec inserita non è valida")
        String pec,
        @NotNull(message = "Inserisci la data di inserimento")
        LocalDate dataInserimento,
        @NotNull(message = "Inserisci la data dell'ultimo contatto")
        LocalDate dataUltimoContatto,
        @NotNull(message = "Il fatturato annuo è obbligatorio")
        long fatturatoAnnuo,
        @NotNull(message = "Inserisci la sede legale")
        Indirizzo sedeLegale,
        @NotNull(message = "Inserisci la sede operativa")
        Indirizzo sedeOperativa,
        @NotEmpty(message = "Inserisci l'email del contatto")
        @Email(message = "L'email del contatto non è valida")
        String emailContatto,
        @NotEmpty(message = "Inserisci il nome del contatto")
        String nomeContatto,
        @NotEmpty(message = "Inserisci il cognome del contatto")
        String cognomeContatto,
        @NotNull(message = "Inserisci il numero di telefono del contatto")
        long telefonoContatto,
        @NotEmpty(message = "Inserisci il logo aziendale")
        String logoAziendale

) {
}
