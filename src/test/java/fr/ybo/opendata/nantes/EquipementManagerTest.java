package fr.ybo.opendata.nantes;

import fr.ybo.opendata.nantes.modele.Equipement;
import fr.ybo.opendata.nantes.modele.Theme;
import fr.ybo.opendata.nantes.modele.Categorie;
import fr.ybo.opendata.nantes.util.EquipementManager;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class EquipementManagerTest {

    /**
     * Vérification de la lecture des attributs d'un parking
     */
    @Test
    public void testParkingLoading() {
        Map<String, Equipement> map = EquipementManager.getInstance().getMapEquipements();
        Equipement parkingFeydeau = map.get("3549");
        assertEquals("Parking enclos Feydeau", parkingFeydeau.getNom());
        assertEquals(Theme.DEPLACEMENT, parkingFeydeau.getTheme());
        assertEquals(Categorie.PARKING, parkingFeydeau.getCategorie());
        assertEquals(null, parkingFeydeau.getType());
        assertEquals(47.211179655323, parkingFeydeau.getLatitude());
        assertEquals(-1.558438729504, parkingFeydeau.getLongitude());
    }
}
