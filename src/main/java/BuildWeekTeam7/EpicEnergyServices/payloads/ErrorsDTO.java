package BuildWeekTeam7.EpicEnergyServices.payloads;

import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime dateMessage) {
}
