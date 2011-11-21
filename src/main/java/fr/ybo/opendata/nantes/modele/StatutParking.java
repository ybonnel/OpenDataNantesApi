package fr.ybo.opendata.nantes.modele;

/**
 * Statut d'un parking.
 */
public enum StatutParking {
    /**
     * Invalide (comptage hors service).
     */
    INVALIDE(0),
    /**
     * Groupe parking fermé pour tous clients.
     */
    FERME(1),
    /**
     * Groupe parking fermé au client horaires et ouvert pour les abonnés
     * (exemple : un parking fermé aux clients horaires la nuit ou le dimanche).
     */
    ABONNES(2),
    /**
     * Groupe parking ouvert à tous les clients.
     * Le nombre de places correspond au nombre de places destinées aux clients horaires.
     */
    OUVERT(5);

    /**
     * Valeur dans l'API.
     */
    private int value;

    /**
     * Constructeur.
     *
     * @param value {@link StatutParking#value}.
     */
    StatutParking(int value) {
        this.value = value;
    }

    /**
     * Renvoie un enum en fonction de la valeur de l'API.
     *
     * @param value valeur.
     * @return l'enum.
     */
    public static StatutParking fromValue(int value) {
        for (StatutParking statutParking : values()) {
            if (statutParking.value == value) {
                return statutParking;
            }
        }
        return null;
    }
}
