package fr.ybo.opendata.nantes.util;

import fr.ybo.moteurcsv.MoteurCsv;
import fr.ybo.moteurcsv.MoteurCsv.InsertObject;
import fr.ybo.opendata.nantes.modele.Equipement;
import fr.ybo.opendata.nantes.modele.Parking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
     * Fichier a chargé qui contient les données de Nantes Métropole sous licence Open Database License (ODbL).
     * <a href="http://data.nantes.fr/donnees/detail/?tx_icsoddatastore_pi1[page]=1&tx_icsoddatastore_pi1[uid]=45">Telechargement</a>
     */
    private static final String EQUIPEMENTS_PUBLICS_DEPLACEMENT_CSV = "/Equipements_publics_deplacement.csv";

    /**
     * Encoding du fichier fournis par Nantes Métropole.
     */
    private static final String CHARSET_NAME = "ISO-8859-15";

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
        Equipement equipement = getMapEquipements().get(parking.getIdObj());
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
    private Map<Integer, Equipement> mapEquipements;

    /**
     * @return {@link EquipementManager#mapEquipements}.
     */
    @SuppressWarnings("unchecked")
    public Map<Integer, Equipement> getMapEquipements() {
        if (mapEquipements == null) {
            mapEquipements = new HashMap<Integer, Equipement>();
            MoteurCsv moteurCsv = new MoteurCsv(Arrays.<Class<?>>asList(Equipement.class));
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        EquipementManager.class.getResourceAsStream(EQUIPEMENTS_PUBLICS_DEPLACEMENT_CSV), CHARSET_NAME));
                try {
                    moteurCsv.parseFileAndInsert(bufferedReader, Equipement.class,
                            new EquipementInsertObject(mapEquipements));
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (Exception exception) {
                        LOGGER.warning(exception.getMessage());
                    }
                }
            } catch (UnsupportedEncodingException exception) {
                LOGGER.warning(exception.getMessage());
            }

        }
        return mapEquipements;
    }
    /**
     * Gestion des {@link Equipement}.
     */
    private static final class EquipementInsertObject implements InsertObject<Equipement> {
        /**
         * Map des equipements.
         */
        private Map<Integer, Equipement> mapEquipements;

        /**
         * Constructeur.
         *
         * @param mapEquipements map des équipements.
         */
        private EquipementInsertObject(Map<Integer, Equipement> mapEquipements) {
            this.mapEquipements = mapEquipements;
        }

        @Override
        public void insertObject(Equipement objet) {
            mapEquipements.put(objet.getIdObj(), objet);
        }
    }
}
