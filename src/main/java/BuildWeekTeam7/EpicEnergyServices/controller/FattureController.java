package BuildWeekTeam7.EpicEnergyServices.controller;

import BuildWeekTeam7.EpicEnergyServices.exception.BadRequestException;
import BuildWeekTeam7.EpicEnergyServices.entities.Fatture;
import BuildWeekTeam7.EpicEnergyServices.payloads.FattureDTO;
import BuildWeekTeam7.EpicEnergyServices.service.FattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/fatture")
public class FattureController {

    @Autowired
    private FattureService fattureService;

    @GetMapping
    private Page<Fatture> getAllInvoices(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size, @RequestParam (defaultValue = "data") String sortBy){
        return this.fattureService.getAllInvoices(page, size, sortBy);
    }

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    private Fatture saveInvoices (@RequestBody FattureDTO payload, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.fattureService.saveInvoices(payload);
    }

    @GetMapping ("/{fatturaId}")
    private Fatture getInvoices (@PathVariable long fatturaId){
        return this.fattureService.getInvoicesById(fatturaId);
    }

    @PutMapping ("/{fatturaId}")
    private Fatture updateInvoices (@RequestBody FattureDTO payload, @PathVariable long fatturaId){
        return this.fattureService.updateInvoices(payload, fatturaId);
    }

    @DeleteMapping ("/{fatturaId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    private void deleteInvoices (@PathVariable long fatturaId){
        this.fattureService.deleteInvoices(fatturaId);
    }
}
