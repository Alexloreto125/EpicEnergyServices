package BuildWeekTeam7.EpicEnergyServices.controllers;

import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import BuildWeekTeam7.EpicEnergyServices.exceptions.BadRequest;
import BuildWeekTeam7.EpicEnergyServices.payloads.NewClienteDTO;
import BuildWeekTeam7.EpicEnergyServices.payloads.RispostaNewClienteDTO;
import BuildWeekTeam7.EpicEnergyServices.service.ClientiService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClientiService clientiService;

    // 1 - VIsualizziamo tutti i clienti (ordinando in base al parametro inserito)
    @GetMapping
    public Page<Clienti> getAllClienti(@RequestParam(defaultValue = "0") int pageNumber,
                                       @RequestParam(defaultValue = "10") int pageSize,
                                       @RequestParam(defaultValue = "dataInserimento") String sortBy) {
        return this.clientiService.getAllClienti(pageNumber, pageSize, sortBy);
    }


    // 2 - Visualizziamo un cliente specifico
    @GetMapping("/{partitaIva}")
    public Clienti findById(@PathVariable String partitaIva){
        return this.clientiService.findClientiByPartitaIva(partitaIva);
    }


    // 3 - Aggiungiamo un cliente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RispostaNewClienteDTO save(@RequestBody @Validated NewClienteDTO body, BindingResult validation) throws BadRequestException {
        if(validation.hasErrors()) {
            throw new BadRequest(validation.getAllErrors());
        }
        return new RispostaNewClienteDTO(this.clientiService.save(body).getPartitaIva());
    }


     // 4 - Modifichaimo un cliente
    @PutMapping("/modifica/{partitaIva}")
    public Clienti findByIdAndUpdate(@PathVariable String partitaIva, @RequestBody Clienti body){
        return this.clientiService.findByPartitaIvaAndUpdate(partitaIva, body);
    }


    // 5 - Eliminiamo un cliente
    @DeleteMapping("/{partitaIva}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable String partitaIva){
        this.clientiService.findByPartitaIvaAndDelete(partitaIva);
    }


    // Filtra i clienti in base al fatturato annuale
    @GetMapping("/filter/fatturato")
    public ResponseEntity<List<Clienti>> filterByFatturatoAnnuale(
            @RequestParam(name = "min", required = false) Long fatturatoMin,
            @RequestParam(name = "max", required = false) Long fatturatoMax) {
        List<Clienti> clientiFiltrati = clientiService.filterByFatturatoAnnuale(fatturatoMin, fatturatoMax);
        return new ResponseEntity<>(clientiFiltrati, HttpStatus.OK);
    }

    // Filtra i clienti in base alla data di inserimento
    @GetMapping("/filter/data-inserimento")
    public ResponseEntity<List<Clienti>> filterByDataInserimento(
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInizio,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFine) {
        List<Clienti> clientiFiltrati = clientiService.filterByDataInserimento(dataInizio, dataFine);
        return new ResponseEntity<>(clientiFiltrati, HttpStatus.OK);
    }

    // Filtra i clienti in base alla data di ultimo contatto
    @GetMapping("/filter/data-ultimo-contatto")
    public ResponseEntity<List<Clienti>> filterByDataUltimoContatto(
            @RequestParam(name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInizio,
            @RequestParam(name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFine) {
        List<Clienti> clientiFiltrati = clientiService.filterByDataUltimoContatto(dataInizio, dataFine);
        return new ResponseEntity<>(clientiFiltrati, HttpStatus.OK);
    }

    // Filtra i clienti in base a una parte del nome
    @GetMapping("/filter/nome")
    public ResponseEntity<List<Clienti>> filterByNome(@RequestParam(name = "q") String nomeParziale) {
        List<Clienti> clientiFiltrati = clientiService.filterByNome(nomeParziale);
        return new ResponseEntity<>(clientiFiltrati, HttpStatus.OK);
    }

}
