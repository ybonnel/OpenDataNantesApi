package fr.ybo.opendata.nantes.modele;

import fr.ybo.opendata.nantes.sax.BaliseData;
import fr.ybo.opendata.nantes.sax.BaliseType;
import fr.ybo.opendata.nantes.sax.BaliseXml;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Classe Parking.
 */
@BaliseData("Groupe_Parking")
public class Parking implements Serializable {
    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Parking.class.getSimpleName());
    /**
     * Serial.
     */
    private static final long serialVersionUID = -2671435726234350378L;
    /**
     * Identifiant du parking.
     */
    private String identifiant;
    /**
     * Nom du parking.
     */
    private String nom;
    /**
     * Statut du parking.
     */
    private StatutParking statut;

    /**
     * Niveau de priorité du mode automatique.
     */
    private int priorite;
    /**
     * Nombre de places disponibles.
     */
    private int disponibles;
    /**
     * Nombre de places minimum devant déclencher l'affichage 'COMPLET'.
     */
    private int seuilComplet;
    /**
     * Nombre de places ouvertes au clients horaires.
     */
    private int placesTotales;

    /**
     * Timestamp de l'information (format <> de xs:date)
     * au delà de 15 min, l'information ne doit plus être considérée comme valide.
     */
    private Date lastUpdate;

    /**
     * Latitude.
     */
    private Double latitude;

    /**
     * Longitude.
     */
    private Double longitude;

    /**
     * @return {@link Parking#identifiant}.
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * @param identifiant {@link Parking#identifiant}.
     */
    @BaliseXml(name = "Grp_identifiant")
    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    /**
     * @return {@link Parking#nom}.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom {@link Parking#nom}.
     */
    @BaliseXml(name = "Grp_nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return {@link Parking#statut}.
     */
    public StatutParking getStatut() {
        return statut;
    }

    /**
     * @param statut {@link Parking#statut}.
     */
    @BaliseXml(name = "Grp_statut", type = BaliseType.INTEGER)
    public void setStatut(int statut) {
        this.statut = StatutParking.fromValue(statut);
    }

    /**
     * @return {@link Parking#priorite}.
     */
    public int getPriorite() {
        return priorite;
    }

    /**
     * @param priorite {@link Parking#priorite}.
     */
    @BaliseXml(name = "Grp_pri_aut", type = BaliseType.INTEGER)
    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    /**
     * @return {@link Parking#disponibles}.
     */
    public int getDisponibles() {
        return disponibles;
    }

    /**
     * @param disponibles {@link Parking#disponibles}.
     */
    @BaliseXml(name = "Grp_disponible", type = BaliseType.INTEGER)
    public void setDisponibles(int disponibles) {
        this.disponibles = disponibles;
    }

    /**
     * @return {@link Parking#seuilComplet}.
     */
    public int getSeuilComplet() {
        return seuilComplet;
    }

    /**
     * @param seuilComplet {@link Parking#seuilComplet}.
     */
    @BaliseXml(name = "Grp_complet", type = BaliseType.INTEGER)
    public void setSeuilComplet(int seuilComplet) {
        this.seuilComplet = seuilComplet;
    }

    /**
     * @return {@link Parking#placesTotales}.
     */
    public int getPlacesTotales() {
        return placesTotales;
    }

    /**
     * @param placesTotales {@link Parking#placesTotales}.
     */
    @BaliseXml(name = "Grp_exploitation", type = BaliseType.INTEGER)
    public void setPlacesTotales(int placesTotales) {
        this.placesTotales = placesTotales;
    }

    /**
     * @return {@link Parking#lastUpdate}.
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Format pour le champ Grp_horodatage.
     */
    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    /**
     * @param lastUpdate {@link Parking#lastUpdate}.
     */
    @BaliseXml(name = "Grp_horodatage")
    public void setLastUpdate(String lastUpdate) {
        try {
            this.lastUpdate = SDF_DATE.parse(lastUpdate);
        } catch (ParseException exception) {
            LOGGER.warning(exception.getMessage());
        }
    }

    /**
     * @return {@link Parking#latitude}.
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude {@link Parking#latitude}.
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return {@link Parking#longitude}.
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude {@link Parking#longitude}.
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
