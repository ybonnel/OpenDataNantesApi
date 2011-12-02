package fr.ybo.opendata.nantes.util;

import fr.ybo.moteurcsv.MoteurCsv;
import fr.ybo.moteurcsv.MoteurCsv.InsertObject;
import fr.ybo.opendata.nantes.modele.Equipement;
import fr.ybo.opendata.nantes.modele.MappingEquipement;
import fr.ybo.opendata.nantes.modele.Parking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Manager des équipements.
 */
public class EquipementManager {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(EquipementManager.class.getSimpleName());

    /**
     * Instance du singletton.
     */
    private static EquipementManager instance;

    /**
     * @return l'instance du singletton.
     */
    public static synchronized EquipementManager getInstance() {
        if (instance == null) {
            instance = new EquipementManager();
        }
        return instance;
    }

    /**
     * Méthode permettant de completter les parkings.
     *
     * @param parkings les parkings à compléter.
     */
    public void completeParkings(List<Parking> parkings) {
        for (Parking parking : parkings) {
            completeParking(parking);
        }
    }

    /**
     * Méthode permettant de completer un parking.
     *
     * @param parking parking à completer.
     */
    private void completeParking(Parking parking) {
        Equipement equipement = getMapEquipements().get(parking.getIdentifiant());
        if (equipement == null) {
            LOGGER.warning("Pas d'equipements trouvés pour le parking " + parking.getIdentifiant());
        } else {
            parking.setLatitude(equipement.getLatitude());
            parking.setLongitude(equipement.getLongitude());
        }
    }

    /**
     * Map des equipements.
     */
    private Map<String, Equipement> mapEquipements;

    /**
     * @return {@link EquipementManager#mapEquipements}.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Equipement> getMapEquipements() {
        if (mapEquipements == null) {
            mapEquipements = new HashMap<String, Equipement>();
            MoteurCsv moteurCsv = new MoteurCsv(Arrays.asList(MappingEquipement.class, Equipement.class));
            Map<String, List<String>> mapIds = new HashMap<String, List<String>>();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(EquipementManager.class.getResourceAsStream("/mappingEquip.csv")));
            try {
                moteurCsv.parseFileAndInsert(bufferedReader, MappingEquipement.class,
                        new MappingEquipementInsertObject(mapIds));
            } finally {
                try {
                    bufferedReader.close();
                } catch (Exception exception) {
                    LOGGER.warning(exception.getMessage());
                }
            }

            bufferedReader = new BufferedReader(new InputStreamReader(
                    EquipementManager.class.getResourceAsStream("/Equipements_publics_deplacement.csv")));
            try {
                moteurCsv.parseFileAndInsert(bufferedReader, Equipement.class,
                        new EquipementInsertObject(mapIds, mapEquipements));
            } finally {
                try {
                    bufferedReader.close();
                } catch (Exception exception) {
                    LOGGER.warning(exception.getMessage());
                }
            }
        }
        return mapEquipements;
    }

    /**
     * Gestion des {@link MappingEquipement}.
     */
    private static final class MappingEquipementInsertObject implements InsertObject<MappingEquipement> {
        /**
         * Map des ids à remplir.
         */
        private Map<String, List<String>> mapIds;

        /**
         * Contructeur.
         *
         * @param mapIds map des ids à remplir.
         */
        private MappingEquipementInsertObject(Map<String, List<String>> mapIds) {
            this.mapIds = mapIds;
        }

        @Override
        public void insertObject(MappingEquipement objet) {
            if (!mapIds.containsKey(objet.getIdObj())) {
                mapIds.put(objet.getIdObj(), new ArrayList<String>());
            }
            mapIds.get(objet.getIdObj()).add(objet.getIdentifiant());
        }
    }

    /**
     * Gestion des {@link Equipement}.
     */
    private static final class EquipementInsertObject implements InsertObject<Equipement> {
        /**
         * Map des ids à remplir.
         */
        private final Map<String, List<String>> mapIds;
        /**
         * Map des equipements.
         */
        private Map<String, Equipement> mapEquipements;

        /**
         * Constructeur.
         *
         * @param mapIds         map des ids.
         * @param mapEquipements map des équipements.
         */
        private EquipementInsertObject(Map<String, List<String>> mapIds, Map<String, Equipement> mapEquipements) {
            this.mapIds = mapIds;
            this.mapEquipements = mapEquipements;
        }

        @Override
        public void insertObject(Equipement objet) {
            if (mapIds.containsKey(objet.getIdObj())) {
                for (String identifiant : mapIds.get(objet.getIdObj())) {
                    mapEquipements.put(identifiant, objet);
                }
            }
        }
    }
}
