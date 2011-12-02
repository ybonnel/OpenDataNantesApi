package fr.ybo.opendata.nantes.modele;

import fr.ybo.moteurcsv.adapter.AdapterDouble;
import fr.ybo.moteurcsv.annotation.BaliseCsv;
import fr.ybo.moteurcsv.annotation.FichierCsv;
import fr.ybo.opendata.nantes.util.MyAdapteurDouble;

/**
 * Classe représentant un équipement.
 */
@FichierCsv(value = "Equipements_publics_deplacement.csv", separateur = ";")
public class Equipement {
    /**
     * Identifiant de l'objet.
     */
    @BaliseCsv("_IDOBJ")
    private String idObj;
    /**
     * Latitude.
     */
    @BaliseCsv(value = "LAT_WGS84", adapter = MyAdapteurDouble.class)
    private Double latitude;
    /**
     * Longitude.
     */
    @BaliseCsv(value = "LONG_WGS84", adapter = MyAdapteurDouble.class)
    private Double longitude;

    /**
     * @return {@link Equipement#idObj}.
     */
    public String getIdObj() {
        return idObj;
    }

    /**
     * @return {@link Equipement#latitude}.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @return {@link Equipement#longitude}.
     */
    public Double getLongitude() {
        return longitude;
    }
}
