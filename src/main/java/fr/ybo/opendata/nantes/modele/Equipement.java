package fr.ybo.opendata.nantes.modele;

import fr.ybo.moteurcsv.adapter.AdapterDouble;
import fr.ybo.moteurcsv.annotation.BaliseCsv;
import fr.ybo.moteurcsv.annotation.FichierCsv;
import fr.ybo.opendata.nantes.util.MyAdapteurDouble;
import fr.ybo.opendata.nantes.util.MyCategorieAdapteur;
import fr.ybo.opendata.nantes.util.MyThemeAdapteur;
import fr.ybo.opendata.nantes.util.MyTypeAdapteur;

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
     * Nom.
     */
    @BaliseCsv(value = "NOM_COMPLET")
    private String nom;

    /**
     * Theme.
     */
    @BaliseCsv(value = "THEME", adapter = MyThemeAdapteur.class)
    private Theme theme;

    /**
     * Categorie.
     */
    @BaliseCsv(value = "CATEGORIE", adapter = MyCategorieAdapteur.class)
    private Categorie categorie;

    /**
     * Type.
     */
    @BaliseCsv(value = "TYPE", adapter = MyTypeAdapteur.class)
    private Type type;
    /**
     * Commune.
     */
    @BaliseCsv("COMMUNE")
    private String commune;

    /**
     * Adresse postale.
     */
    @BaliseCsv("ADRESSE")
    private String adresse;

    /**
     * Code postal.
     */
    @BaliseCsv("CODE_POSTAL")
    private String codePostal;

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

    /**
     * @return {@link Equipement#nom}.
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return {@link Equipement#theme}.
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * @return {@link Equipement#categorie}.
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * @return {@link Equipement#type}.
     */
    public Type getType() {
        return type;
    }

    /**
     * @return {@link Equipement#commune}.
     */
    public String getCommune() {
        return commune;
    }

    /**
     * @return {@link Equipement#adresse}.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @return {@link Equipement#codePostal}.
     */
    public String getCodePostal() {
        return codePostal;
    }

}
