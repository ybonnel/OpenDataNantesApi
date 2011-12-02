package fr.ybo.opendata.nantes.modele;

import fr.ybo.moteurcsv.annotation.BaliseCsv;
import fr.ybo.moteurcsv.annotation.FichierCsv;

/**
 * Classe représentant le mapping des équipements.
 */
@FichierCsv(value = "mappingEquip.csv", separateur = ";")
public class MappingEquipement {

    /**
     * Identifiant dans les APIs.
     */
    @BaliseCsv("Grp_identifiant")
    private String identifiant;
    /**
     * Identifiant dans le csv.
     */
    @BaliseCsv("IdObj")
    private String idObj;

    /**
     * @return {@link  MappingEquipement#identifiant}.
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * @return {@link MappingEquipement#idObj}.
     */
    public String getIdObj() {
        return idObj;
    }
}
