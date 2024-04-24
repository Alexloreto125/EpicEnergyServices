package BuildWeekTeam7.EpicEnergyServices.payloads;

import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import BuildWeekTeam7.EpicEnergyServices.entities.StatoFatture;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record FattureDTO(
        @NotNull(message = "La data è obbligatoria")
                         LocalDate data,
                         @NotNull
                         @Min(0)
                         double importo,
                        @NotNull(message = "Lo stato è obbligatorio")
                        StatoFatture stato,
                         @NotNull(message = "Il cliente è obbligatorio")
                         String pIva
                         ) {
}
