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

import fr.ybo.opendata.nantes.exceptions.ApiException;
import fr.ybo.opendata.nantes.exceptions.ApiReseauException;
import fr.ybo.opendata.nantes.modele.Answer;
import fr.ybo.opendata.nantes.modele.InfoTrafic;
import fr.ybo.opendata.nantes.modele.Parking;
import fr.ybo.opendata.nantes.sax.ApiHandler;
import fr.ybo.opendata.nantes.sax.GenericHandler;
import fr.ybo.opendata.nantes.util.Connecteur;
import fr.ybo.opendata.nantes.util.HttpConnecteur;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Classe d'accés aux API OpenData de nantes.
 *
 * @author ybonnel
 */
public class OpenDataApi {

    /**
     * URL d'accés au API OpenDataApi.
     */
    private static final String URL = "http://data.nantes.fr/api/";

    /**
     * Version.
     */
    private static final String VERSION = "1.0";

    /**
     * Connecteur.
     */
    private Connecteur connecteur;

    /**
     * @param connecteur {@link OpenDataApi#connecteur}.
     */
    protected void setConnecteur(Connecteur connecteur) {
        this.connecteur = connecteur;
    }

    /**
     * Constructeur.
     *
     * @param apiKey clé fournie par le site.
     */
    public OpenDataApi(String apiKey) {
        key = apiKey;
        connecteur = new HttpConnecteur();
    }

    /**
     * Clé de l'api.
     */
    private final String key;

    /**
     * Commande pour récupérer les parkings.
     */
    private static final String CMD_PARKINGS = "getDisponibiliteParkingsPublics";

    /**
     * Cette commande permet de récupérer les informations concernant la disponibilité des parkings publics de
     * l'agglomération nantaise.
     *
     * @return la liste des parkings.
     * @throws ApiReseauException problème réseaux.
     */
    public List<Parking> getParkings() throws ApiReseauException {
        return appelApi(getUrl(CMD_PARKINGS), new GenericHandler<Parking>(Parking.class));
    }

    /**
     * Commande pour récupérer les infoTrafics.
     */
    private static final String CMD_INFOS_TRAFICS = "getInfoTraficTANPrevisionnel";

    /**
     * Cette commande permet de récupérer l'info trafic prévisionnel des bus et tramway de la SEMITAN.
     *
     * @return la liste des infos trafics.
     * @throws ApiReseauException problème réseaux.
     */
    public List<InfoTrafic> getInfosTrafics() throws ApiReseauException {
        return appelApi(getUrl(CMD_INFOS_TRAFICS), new GenericHandler<InfoTrafic>(InfoTrafic.class));
    }

    /**
     * @param <T>     type d'objet OpenDataApi.
     * @param url     url.
     * @param handler handler.
     * @return liste d'objets OpenDataApi.
     * @throws ApiReseauException en cas d'erreur réseau.
     */
    private <T> List<T> appelApi(String url, ApiHandler<T> handler) throws ApiReseauException {
        Answer<T> answer;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            InputStream inputStream = connecteur.openInputStream(url);
            try {
                parser.parse(inputStream, handler);
                answer = handler.getAnswer();
            } finally {
                try {
                    inputStream.close();
                } catch (Exception ignore) {
                }
            }
        } catch (IOException ioException) {
            throw new ApiReseauException(ioException);
        } catch (SAXException saxException) {
            throw new ApiReseauException(saxException);
        } catch (ParserConfigurationException exception) {
            throw new ApiException("Erreur lors de l'appel à l'API OpenData", exception);
        }
        if (answer == null || answer.getStatus() == null || !"0".equals(answer.getStatus().getCode())) {
            throw new ApiReseauException();
        }
        return answer.getData();
    }

    /**
     * Permet de récupérer l'URL d'accés aux API OpenData en fonction de la
     * commande à exécuter.
     *
     * @param commande commande à exécuter.
     * @return l'url.
     */
    private String getUrl(String commande) {
        StringBuilder stringBuilder = new StringBuilder(URL);
        stringBuilder.append(commande).append('/');
        stringBuilder.append(VERSION).append('/');
        stringBuilder.append(key);
        return stringBuilder.toString();
    }

}
