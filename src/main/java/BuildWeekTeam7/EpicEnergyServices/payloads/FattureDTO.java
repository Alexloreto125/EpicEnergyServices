package BuildWeekTeam7.EpicEnergyServices.payloads;

import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record FattureDTO(
        @NotNull(message = "La data è obbligatoria")
                         LocalDate data,
                         @NotNull
                         @Min(0)
                         double importo,
                         @NotEmpty(message = "Lo stato è obbligatorio")
                         @Pattern(regexp = "IN_ELABORAZIONE|INVIATA|SCARTATA|EMESSA", message = "Inserisci uno stato valido tra IN_ELABORAZIONE, INVIATA, SCARTATA, EMESSA")
                         String stato,
                         @NotEmpty(message = "ATTENZIONE! La partitaIva è obbligatoria")
                         @Size(min = 11, max = 11)
                         String pIva
                         ) {
}
