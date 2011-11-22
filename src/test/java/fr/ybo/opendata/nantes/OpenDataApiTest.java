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
import fr.ybo.opendata.nantes.modele.InfoTrafic;
import fr.ybo.opendata.nantes.modele.Itineraire;
import fr.ybo.opendata.nantes.modele.Parking;
import fr.ybo.opendata.nantes.modele.StatutParking;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

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
     *
     * @throws ApiReseauException problème réseaux.
     * @throws ParseException     problème.
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

    /**
     * Test de la méthode {@link OpenDataApi#getInfosTrafics()}.
     *
     * @throws ApiReseauException problème réseaux.
     * @throws ParseException     problème.
     */
    @Test
    public void testGetInfosTrafics() throws ApiReseauException, ParseException {
        openDataApi.setConnecteur(new FileConnecteur("/getInfoTraficTANPrevisionnel.xml"));

        List<InfoTrafic> infosTrafics = openDataApi.getInfosTrafics();
        assertEquals(2, infosTrafics.size());
        InfoTrafic infoTrafic1 = infosTrafics.get(0);
        assertEquals("1321603576591", infoTrafic1.getCode());
        assertEquals("Réfection chaussée rue Santos Dumont", infoTrafic1.getIntitule());
        assertEquals("En raison de travaux rue Santos Dumont la ligne 96 est déviée le 22 et 23 novembre 2011.",
                infoTrafic1.getResume());
        assertEquals(infoTrafic1.getResume(), infoTrafic1.getTexteVocal());
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        assertEquals(formatDate.parse("22/11/2011 00:00"), infoTrafic1.getDateDebut());
        assertEquals(formatDate.parse("23/11/2011 23:59"), infoTrafic1.getDateFin());
        assertFalse(infoTrafic1.isTerminee());
        assertEquals("[96/-/-/-]", infoTrafic1.getTroncons());

        InfoTrafic infoTrafic2 = infosTrafics.get(1);
        assertEquals(formatDate.parse("22/11/2011 10:30"), infoTrafic2.getDateDebut());
        assertEquals(formatDate.parse("22/11/2011 16:00"), infoTrafic2.getDateFin());
        assertTrue(infoTrafic2.isTerminee());
    }

    /**
     * Test de la méthode {@link OpenDataApi#getInfosTraficsTpsReel()}.
     *
     * @throws ApiReseauException problème réseaux.
     * @throws ParseException     problème.
     */
    @Test
    public void testGetInfosTraficsTpsReel() throws ApiReseauException, ParseException {
        openDataApi.setConnecteur(new FileConnecteur("/getInfoTraficTANTempsReel.xml"));

        List<InfoTrafic> infosTrafics = openDataApi.getInfosTrafics();
        assertEquals(2, infosTrafics.size());
        InfoTrafic infoTrafic1 = infosTrafics.get(0);
        assertEquals("1288273473818", infoTrafic1.getCode());
        assertEquals("Travaux Boulevard Marcel Paul", infoTrafic1.getIntitule());
        assertEquals("En raison de travaux sur le Bd Marcel Paul les lignes de bus 73, 84 et 93 "
                + "sont déviées dans les deux sens.", infoTrafic1.getResume());
        assertEquals(infoTrafic1.getResume(), infoTrafic1.getTexteVocal());
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        assertEquals(formatDate.parse("28/10/2010 00:00"), infoTrafic1.getDateDebut());
        assertNull(infoTrafic1.getDateFin());
        assertFalse(infoTrafic1.isTerminee());
        assertNull(infoTrafic1.getTroncons());

        InfoTrafic infoTrafic2 = infosTrafics.get(1);
        assertEquals(formatDate.parse("31/01/2011 10:00"), infoTrafic2.getDateDebut());
        assertEquals(formatDate.parse("31/12/2012 16:00"), infoTrafic2.getDateFin());
        assertTrue(infoTrafic2.isTerminee());
        assertEquals("[PR/1/-/-]", infoTrafic2.getTroncons());
    }

    /**
     * Test de la méthode {@link OpenDataApi#getTempsParcours()}.
     *
     * @throws ApiReseauException problème réseaux.
     * @throws ParseException     problème.
     */
    @Test
    public void testGetTempsParcours() throws ApiReseauException, ParseException {
            openDataApi.setConnecteur(new FileConnecteur("/getTempsParcours.xml"));

        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        List<Itineraire> itineraires = openDataApi.getTempsParcours();
        assertEquals(2, itineraires.size());
        assertEquals("011", itineraires.get(0).getIdentifiant());
        assertEquals(TEMPS_PARCOURS, itineraires.get(0).getTemps());
        assertTrue(itineraires.get(0).isValide());
        assertEquals(formatDate.parse("22/11/2011 10:57:06"), itineraires.get(0).getLastUpdate());

        assertFalse(itineraires.get(1).isValide());
    }

    /**
     * Temps de parcours.
     */
    private static final int TEMPS_PARCOURS = 11;

    /**
     * Test en passant vraiment pas la couche http.
     * @throws ApiReseauException problème réseaux.
     */
    public void testHttp() throws ApiReseauException {
        openDataApi = new OpenDataApi("QB1ANX3ARXFI3AS");
        List<InfoTrafic> infosTrafics = openDataApi.getInfosTrafics();
        assertNotNull(infosTrafics);
        assertFalse(infosTrafics.isEmpty());
    }

}
