package BuildWeekTeam7.EpicEnergyServices.controllers;

import BuildWeekTeam7.EpicEnergyServices.entities.Fatture;
import BuildWeekTeam7.EpicEnergyServices.entities.StatoFatture;
import BuildWeekTeam7.EpicEnergyServices.exceptions.BadRequestException;
import BuildWeekTeam7.EpicEnergyServices.payloads.FattureDTO;
import BuildWeekTeam7.EpicEnergyServices.services.FattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/fatture")
public class FattureController {

    @Autowired
    private FattureService fattureService;

    // GET
    @GetMapping
    private Page<Fatture> getAllInvoices(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "data") String sortBy) {
        return this.fattureService.getAllInvoices(page, size, sortBy);
    }

    // GET BY ID
    @GetMapping("/{fatturaId}")
    private Fatture getInvoices(@PathVariable long fatturaId) {
        return this.fattureService.getInvoicesById(fatturaId);
    }

    // GET BY CLIENTE
    @GetMapping("/cliente")
    private List<Fatture> getInvoicesByCliente(@RequestParam String cliente) {
        return this.fattureService.getInvoicesByCliente(cliente);
    }

    // GET BY STATE
    @GetMapping("/state")
    private List<Fatture> getInvoicesByState(@RequestParam StatoFatture state) {
        return this.fattureService.findByState(state);
    }

    // GET BY DATE
    @GetMapping("/date")
    private List<Fatture> getInvoicesByDate(@RequestParam LocalDate date) {
        return this.fattureService.findByDate(date);
    }

    // GET BY YEAR
    @GetMapping("/year")
    private List<Fatture> getInvoicesByYear(@RequestParam int year) {
        return this.fattureService.findByYear(year);
    }

    // GET BY RANGE IMPORT
    @GetMapping("/import")
    private List<Fatture> getInvoicesByRangeImport(@RequestParam double import1, double import2) {
        return this.fattureService.findByImport(import1, import2);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Fatture saveInvoices(@RequestBody @Validated FattureDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.fattureService.saveInvoices(payload);
    }


    @PutMapping("/{fatturaId}")
    private Fatture updateInvoices(@RequestBody @Validated FattureDTO payload, @PathVariable long fatturaId, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.fattureService.updateInvoices(payload, fatturaId);
    }

    @DeleteMapping("/{fatturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteInvoices(@PathVariable long fatturaId) {
        this.fattureService.deleteInvoices(fatturaId);
    }
}
