package BuildWeekTeam7.EpicEnergyServices.services;

import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import BuildWeekTeam7.EpicEnergyServices.entities.StatoFatture;
import BuildWeekTeam7.EpicEnergyServices.exceptions.NotFoundException;
import BuildWeekTeam7.EpicEnergyServices.repositories.StatoFattureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatoFattureService {
    @Autowired
    private StatoFattureDAO statoFattureDAO;


    public List<String> getAllStatoFatture() {
        return this.statoFattureDAO.findAll().stream().map(StatoFatture::getNome).collect(Collectors.toList());
    }

    public StatoFatture getStatoFattureById(long id) {
        return this.statoFattureDAO.findById(id).orElseThrow(() -> new NotFoundException("Stato fattura con id: " + id + " non trovato"));
    }

    public StatoFatture saveStatoFatture(StatoFatture statoFatture) {
        return this.statoFattureDAO.save(statoFatture);
    }

    public StatoFatture getStatoFattureByNome(String nome) {
        return this.statoFattureDAO.findByNome(nome);
    }

    public void findBynameAndDelite(String name) {
        StatoFatture stato = this.getStatoFattureByNome(name);
        this.statoFattureDAO.delete(stato);
    }
}
