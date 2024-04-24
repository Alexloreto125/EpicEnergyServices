package BuildWeekTeam7.EpicEnergyServices.services;

import BuildWeekTeam7.EpicEnergyServices.entities.Clienti;
import BuildWeekTeam7.EpicEnergyServices.entities.User;
import BuildWeekTeam7.EpicEnergyServices.exceptions.NotFoundCliente;
import BuildWeekTeam7.EpicEnergyServices.payloads.NewClienteDTO;
import BuildWeekTeam7.EpicEnergyServices.repositories.ClienteDAO;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ClientiService {
    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private Cloudinary cloudinary;

    // Creiamo dei metodi per la gestione dei clienti

    // 1 - VIsualizziamo tutti i clienti
    public Page<Clienti> getAllClienti(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 20) pageSize = 20;
        Pageable pageable;
        switch (sortBy) {
            case "nome":
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by("ragioneSociale"));
                break;
            case "fatturatoAnnuale":
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by("fatturatoAnnuo"));
                break;
            case "dataInserimento":
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by("dataInserimento"));
                break;
            case "dataUltimoContatto":
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by("dataUltimoContatto"));
                ;
                break;
            default:
                pageable = PageRequest.of(pageNumber, pageSize, Sort.by("dataInserimento"));
        }
        return this.clienteDAO.findAll(pageable);
    }

    // 2 - Visualizziamo un cliente specifico
    public Clienti findClientiByPartitaIva(String partitaIva) {
        return this.clienteDAO.findById(partitaIva).orElseThrow(() -> new NotFoundCliente(partitaIva));
    }

    // 3 - Aggiungiamo un cliente
    public Clienti save(NewClienteDTO body) throws BadRequestException {
        this.clienteDAO.findById(body.partitaIva()).ifPresent(
                cliente -> {
                    try {
                        throw new BadRequestException("Il cliente con partita iva" + cliente.getPartitaIva() + " è già presente nei nostri database!");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        Clienti newCliente = new Clienti(body.partitaIva(), body.ragioneSociale(), body.tipoAzienda(), body.email(), body.telefono(), body.pec(), body.dataInserimento(), body.dataUltimoContatto(), body.fatturatoAnnuo(), body.sedeLegale(), body.sedeOperativa(), body.emailContatto(), body.nomeContatto(), body.cognomeContatto(), body.telefonoContatto(), body.logoAziendale());
        return clienteDAO.save(newCliente);
    }

    // 4 - Aggiorniamo un cliente
    public Clienti findByPartitaIvaAndUpdate(String partitaIva, Clienti modifiedUser) {
        Clienti found = this.findClientiByPartitaIva(partitaIva);
        found.setRagioneSociale(modifiedUser.getRagioneSociale());
        found.setTipoAzienda(modifiedUser.getTipoAzienda());
        found.setEmail(modifiedUser.getEmail());
        found.setTelefono(modifiedUser.getTelefono());
        found.setPec(modifiedUser.getPec());
        found.setDataInserimento(modifiedUser.getDataInserimento());
        found.setDataUltimoContatto(modifiedUser.getDataUltimoContatto());
        found.setFatturatoAnnuo(modifiedUser.getFatturatoAnnuo());
        found.setSedeLegale(modifiedUser.getSedeLegale());
        found.setSedeOperativa(modifiedUser.getSedeOperativa());
        found.setEmailContatto(modifiedUser.getEmailContatto());
        found.setNomeContatto(modifiedUser.getNomeContatto());
        found.setCognomeContatto(modifiedUser.getCognomeContatto());
        found.setTelefonoContatto(modifiedUser.getTelefonoContatto());
        found.setLogoAziendale(modifiedUser.getLogoAziendale());
        return this.clienteDAO.save(found);
    }

    // 5 - Eliminiamo un cliente
    public void findByPartitaIvaAndDelete(String partitaIva) {
        Clienti cliente = this.findClientiByPartitaIva(partitaIva);
        this.clienteDAO.delete(cliente);
    }


    // I VARI FILTRI

    // Filtra per fatturato annuale
    public List<Clienti> filterByFatturatoAnnuale(long fatturatoMin, long fatturatoMax) {
        return clienteDAO.findByFatturatoAnnuoGreaterThanEqualAndFatturatoAnnuoLessThanEqual(fatturatoMin, fatturatoMax);
    }

    // Filtra per data di inserimento
    public List<Clienti> filterByDataInserimento(LocalDate dataInizio, LocalDate dataFine) {
        return clienteDAO.findByDataInserimentoGreaterThanEqualAndDataInserimentoLessThanEqual(dataInizio, dataFine);
    }

    // Filtra per data di ultimo contatto
    public List<Clienti> filterByDataUltimoContatto(LocalDate dataInizio, LocalDate dataFine) {
        return clienteDAO.findByDataUltimoContattoGreaterThanEqualAndDataUltimoContattoLessThanEqual(dataInizio, dataFine);
    }

    // Filtra per parte del nome
    public List<Clienti> filterByNome(String nomeParziale) {
        return clienteDAO.findByRagioneSocialeContainingIgnoreCase(nomeParziale);
    }

    public Clienti uploadImage(MultipartFile img, String partitaIva) throws IOException {
        Clienti found = this.findClientiByPartitaIva(partitaIva);
        String url = (String) cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setLogoAziendale(url);
        return this.clienteDAO.save(found);
    }

}
