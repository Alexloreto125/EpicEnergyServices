package BuildWeekTeam7.EpicEnergyServices.controllers;

import BuildWeekTeam7.EpicEnergyServices.payloads.ImportazioneComuniDTO;
import BuildWeekTeam7.EpicEnergyServices.services.ImportazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/importazioni")
public class ImportazioneController {
    @Autowired
    private ImportazioneService importazioneService;

    @PostMapping("/comuni")
    public ImportazioneComuniDTO importaComuni() {
        try {
            importazioneService.importaComuni("comuni-italiani.csv");
            return new ImportazioneComuniDTO("Importazione completata con successo");

        } catch (IOException exception) {
//            exception.printStackTrace();
            return new ImportazioneComuniDTO(exception.getMessage());
        }
    }

    @PostMapping("/province")
    public ImportazioneComuniDTO importaProvince() {
        try {
            importazioneService.importaProvince("province-italiane.csv");
            return new ImportazioneComuniDTO("Importazione completata con successo");
        } catch (IOException exception) {
            exception.printStackTrace();
            return new ImportazioneComuniDTO("Errore durante l'importazione");
        }
    }
}
