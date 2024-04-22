package BuildWeekTeam7.EpicEnergyServices.services;

import BuildWeekTeam7.EpicEnergyServices.entities.Fatture;
import BuildWeekTeam7.EpicEnergyServices.exception.NotFoundException;
import BuildWeekTeam7.EpicEnergyServices.payloads.FattureDTO;
import BuildWeekTeam7.EpicEnergyServices.repositories.FattureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FattureService {

    @Autowired
    private FattureDAO fattureDAO;

    public Page<Fatture> getAllInvoices(int page, int size, String sortBy) {
        if (size > 30) size = 30;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        List<Fatture> a = findByState("IN_ELABORAZIONE");
        System.out.println(a);
        return this.fattureDAO.findAll(pageable);

    }

    public Fatture getInvoicesById(long id) {
        return this.fattureDAO.findById(id).orElseThrow(() -> new NotFoundException("La fattura con il numero: " + id + " non è stata trovata"));
    }

    public Fatture saveInvoices(FattureDTO payload) {
        Fatture fatture = new Fatture(payload.data(), payload.importo(), payload.stato());
        return this.fattureDAO.save(fatture);
    }

    public Fatture updateInvoices(FattureDTO payload, long id) {
        Fatture fatture = this.fattureDAO.findById(id).orElseThrow(() -> new NotFoundException("La fattura con il numero: " + id + " non è stata trovata"));
        fatture.setData(payload.data());
        fatture.setImporto(payload.importo());
        fatture.setStato(payload.stato());
        return this.fattureDAO.save(fatture);
    }

    public void deleteInvoices(long id) {
        Fatture fatture = this.fattureDAO.findById(id).orElseThrow(() -> new NotFoundException("La fattura con il numero: " + id + " non è stata trovata"));
        this.fattureDAO.delete(fatture);
    }

    public List<Fatture> findByState(String stato) {
        return this.fattureDAO.findByStato(stato);
    }


    public List<Fatture> findByDate(LocalDate data) {
        return this.fattureDAO.findByData(data);
    }

    public List<Fatture> findByImport(double importo1, double importo2) {
        return this.fattureDAO.filterByImporto(importo1, importo2);
    }

    public List<Fatture> findByYear(int year) {
        return this.fattureDAO.findAll().stream().filter(fatture -> fatture.getData().getYear() == year).collect(Collectors.toList());
    }


}
