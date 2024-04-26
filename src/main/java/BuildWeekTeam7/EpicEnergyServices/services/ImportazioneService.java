package BuildWeekTeam7.EpicEnergyServices.services;

import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Comune;
import BuildWeekTeam7.EpicEnergyServices.entities.indirizzi.Provincia;
import BuildWeekTeam7.EpicEnergyServices.repositories.ComuneDAO;
import BuildWeekTeam7.EpicEnergyServices.repositories.ProvinciaDAO;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImportazioneService {

    @Autowired
    private ComuneDAO comuneDAO;
    @Autowired
    private ProvinciaDAO provinciaDAO;


    //* Abbiamo l'elenco dei comuni in questo modo :
    //001;001;Agliè;Torino
    //002;002;Borgofranco d'Ivrea;Torino
    //003;003;Caluso;Torino


    @Transactional //* Metodo transizionale ovvero che il metodo viene eseguito all'interno di una transazione
    //! QUANDO ANDREMO A CHIAMARE IL METODO METTEREMO IL NOME DEL FILE TRA ()
    public void importaComuni(String file) throws IOException {


        Resource resource = new ClassPathResource("csv/" + file); //* resource è un'interfaccia che astrae l'accesso a un file mentre ClassPathResource (implementazione di resource) ci dà accesso al package CSV

        try (
                InputStream is = resource.getInputStream(); //* Converte un flusso di byte in caratteri
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {  //* Classe che legge i dati da un flusso di input carattere per carattere (utilizzato per file di testo)
            //! IN QUESTO CASO LEGGIAMO DAL CSV RIGA PER RIGA

            //* quindi convertiamo il file in caratteri e poi lo leggiamo
            Map<String, String> provinciaMancante = new HashMap<>();
            String errori = "";
            String testo;
            while ((testo = br.readLine()) != null) {     //* Questo ciclo legge ogni riga del file CSV finché non raggiungiamo la fine del file
                String[] parts = testo.split(";"); //*  Suddividiamo ogni riga in un array di stringhe, usando il punto e virgola come delimitatore. Ogni elemento dell'array parts conterrà una parte della riga del CSV.
                //* ES: 001;001;Agliè;Torino
                //*     0    1    2     3  --> ora abbiamo 4 stringhe con le posizioni dell'array[ dalla 0 alla 3]


                if (parts.length >= 4) {     //* Controlliamo la lunghezza che contenga almeno 4 parti  ( 001;001;Agliè;Torino)
                    String nomeProvincia = parts[3].trim(); //* Usiamo il trim per prendere in questo caso la sigla a posizione 4 eliminando spazi sia iniziali che finali
                    String nomeComune = parts[2].trim();


                    Provincia provincia = provinciaDAO.findByProvincia(nomeProvincia);
                    if (provincia == null) {
                        if (provinciaMancante.get(nomeProvincia)==null){
                        errori = errori + "provincia mancante: " + nomeProvincia+",";
                        provinciaMancante.put(nomeProvincia,nomeProvincia);
                        }
//                        Provincia provinciaRegione = provinciaDAO.findByRegione(nomeProvincia);
//                        if (provinciaRegione != null) {
//                            provincia.setRegione(nomeProvincia);
//                           provincia.setSigla(provinciaRegione.getSigla());
//                        }
//                        provinciaDAO.save(provincia);
                    } else {
                        Comune comune = new Comune(); //*Creiamo un comune vuoto
                        comune.setComune(nomeComune);
                        comune.setNomeProvincia(nomeProvincia);
                        comune.setProvincia(provincia);

                        //* Riempiamo i campi
                        comuneDAO.save(comune);                 //* Salviamo il comune
                    }
                }
            }
            if (!errori.isEmpty()) {
                throw new BadRequestException(errori);
            }

        }


    }

    @Transactional
    public void importaProvince(String file) throws IOException {
        Resource resource = new ClassPathResource("csv/" + file);

        try (InputStream is = resource.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String testo;

            while ((testo = br.readLine()) != null) {
                String[] parts = testo.split(";");

                if (parts.length >= 3) {
                    String regione = parts[2].trim();
                    String nomeProvincia = parts[1].trim();
                    String sigla = parts[0].trim();


                    Provincia provincia = new Provincia(nomeProvincia, sigla, regione);
                    provinciaDAO.save(provincia);


//                    provincia.setProvincia(nomeProvincia);
//                    provincia.setRegione(regione);
//                    provincia.setSigla(sigla);
                }
            }
        }
    }


}
