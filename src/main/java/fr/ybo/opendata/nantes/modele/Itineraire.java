package fr.ybo.opendata.nantes.modele;

import fr.ybo.opendata.nantes.sax.BaliseData;
import fr.ybo.opendata.nantes.sax.BaliseType;
import fr.ybo.opendata.nantes.sax.BaliseXml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represente un parcours.
 */
@BaliseData("Itineraire")
public class Itineraire {
    /**
     * Identifiant de l’itinéraire.
     */
    private String identifiant;
    /**
     * Temps de parcours en minutes.
     */
    private int temps;
    /**
     * Indicateur de validité.
     */
    private boolean valide;
    /**
     * Timestamp de mesure.
     */
    private Date lastUpdate;

    /**
     * @return {@link Itineraire#identifiant}.
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * @param identifiant {@link Itineraire#identifiant}.
     */
    @BaliseXml(name = "Identifiant")
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * @return {@link Itineraire#temps}.
     */
    public int getTemps() {
        return temps;
    }

    /**
     * @param temps {@link Itineraire#temps}.
     */
    @BaliseXml(name = "Temps", type = BaliseType.INTEGER)
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     * @return {@link Itineraire#valide}.
     */
    public boolean isValide() {
        return valide;
    }

    /**
     * @param valide {@link Itineraire#valide}.
     */
    @BaliseXml(name = "Validite", type = BaliseType.BOOLEAN)
    public void setValide(boolean valide) {
        this.valide = valide;
    }

    /**
     * @return {@link Itineraire#lastUpdate}.
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Format de lastUpdate.
     */
    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    /**
     * @param lastUpdate {@link Itineraire#lastUpdate}.
     * @throws ParseException format de date incorrect.
     */
    @BaliseXml(name = "Horodatage")
    public void setLastUpdate(String lastUpdate) throws ParseException {
        this.lastUpdate = SDF_DATE.parse(lastUpdate);
    }
}
