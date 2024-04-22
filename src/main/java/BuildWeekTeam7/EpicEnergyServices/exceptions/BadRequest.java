package BuildWeekTeam7.EpicEnergyServices.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequest extends RuntimeException{
    private List<ObjectError> errorsList;
    public BadRequest(String message){
        super(message);
    }

    public BadRequest(List<ObjectError> errorsList){
        super("Ci sono stati errori di validazione nel payload!");
        this.errorsList = errorsList;
    }
}
