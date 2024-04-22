package BuildWeekTeam7.EpicEnergyServices.exceptions;

public class NotFoundCliente extends RuntimeException {
    public NotFoundCliente(String partitaIva) {
        super("Il cliente con partitaIva: " + partitaIva + " non è stato trovato!");
    }

}
