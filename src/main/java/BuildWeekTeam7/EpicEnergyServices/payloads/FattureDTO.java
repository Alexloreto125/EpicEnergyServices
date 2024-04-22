package BuildWeekTeam7.EpicEnergyServices.payloads;

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

                         @NotEmpty(message = "Lo stato è obbligatorio")
                         @Pattern(regexp = "IN_ELABORAZIONE|INVIATA|SCARTATA|EMESSA", message = "Inserisci uno stato valido tra IN_ELABORAZIONE, INVIATA, SCARTATA, EMESSA")
                         String stato
                         ) {
}
