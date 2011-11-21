/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.ybo.opendata.nantes;


import fr.ybo.opendata.nantes.exceptions.ApiReseauException;
import fr.ybo.opendata.nantes.modele.Parking;
import fr.ybo.opendata.nantes.modele.StatutParking;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Test de la classe {@link OpenDataApi}.
 */
public class OpenDataApiTest {

    /**
     * {@link OpenDataApi}.
     */
    private OpenDataApi openDataApi;

    /**
     * Constructeur de openDataApi.
     */
    @Before
    public void setup() {
        openDataApi = new OpenDataApi("key");
    }

    /**
     * Test de la méthode {@link OpenDataApi#getParkings()}.
     * @throws ApiReseauException problème réseaux.
     * @throws ParseException problème.
     */
    @Test
    public void testGetParkings() throws ApiReseauException, ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        openDataApi.setConnecteur(new FileConnecteur("/getDisponibiliteParkingsPublics.xml"));

        List<Parking> parkings = openDataApi.getParkings();
        assertEquals(2, parkings.size());
        Parking parking1 = parkings.get(0);
        assertEquals("1", parking1.getIdentifiant());
        assertEquals("NEPTUNE", parking1.getNom());
        assertEquals(StatutParking.INVALIDE, parking1.getStatut());
        assertEquals(0, parking1.getPriorite());
        assertEquals(0, parking1.getDisponibles());
        assertEquals(0, parking1.getSeuilComplet());
        assertEquals(0, parking1.getPlacesTotales());
        assertEquals(formatDate.parse("21/11/2011 20:18:40"), parking1.getLastUpdate());

        Parking parking2 = parkings.get(1);
        assertEquals("2", parking2.getIdentifiant());
        assertEquals("DECRE-BOUFFAY", parking2.getNom());
        assertEquals(StatutParking.OUVERT, parking2.getStatut());
        assertEquals(0, parking2.getPriorite());
        assertEquals(DISPONIBLES, parking2.getDisponibles());
        assertEquals(2, parking2.getSeuilComplet());
        assertEquals(PLACES, parking2.getPlacesTotales());
        assertEquals(formatDate.parse("21/11/2011 20:18:40"), parking1.getLastUpdate());
    }

    /**
     * Places disponibles.
     */
    private static final int DISPONIBLES = 359;
    /**
     * Places totales.
     */
    private static final int PLACES = 552;

}
