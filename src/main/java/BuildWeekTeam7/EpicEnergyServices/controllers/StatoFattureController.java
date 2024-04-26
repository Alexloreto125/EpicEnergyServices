package BuildWeekTeam7.EpicEnergyServices.controllers;

import BuildWeekTeam7.EpicEnergyServices.entities.Fatture;
import BuildWeekTeam7.EpicEnergyServices.entities.StatoFatture;
import BuildWeekTeam7.EpicEnergyServices.services.StatoFattureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statofatturee")
public class StatoFattureController {
    @Autowired
    private StatoFattureService statoFattureService;

    // GET
    @GetMapping
    private List<String> getAllStatoFatture() {
        return this.statoFattureService.getAllStatoFatture();
    }

    // GET BY ID
    @GetMapping("/{statofatturaId}")
    private StatoFatture getStatoFattureById(@PathVariable long statofatturaId) {
        return this.statoFattureService.getStatoFattureById(statofatturaId);
    }


    // GET BY NAME
    @GetMapping("/name")
    private StatoFatture getStatoFattureByName(@RequestParam String name) {
        return this.statoFattureService.getStatoFattureByNome(name);
    }


    // POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private StatoFatture saveStatoFatture(@RequestBody StatoFatture statoFatture) {
        return this.statoFattureService.saveStatoFatture(statoFatture);
    }


    // DELETE
    @DeleteMapping("/{statofatturaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteStatoFatture(@PathVariable String statofatturaId) {
        this.statoFattureService.findBynameAndDelite(statofatturaId);
    }

}
