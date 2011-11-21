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
package fr.ybo.opendata.nantes.sax;

import fr.ybo.opendata.nantes.modele.Answer;
import fr.ybo.opendata.nantes.modele.StatusApi;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Handler pour les appels à l'API.
 *
 * @author ybonnel
 */
public abstract class ApiHandler<T> extends DefaultHandler {

    /**
     * Nom de la balise answer.
     */
    private static final String ANSWER = "answer";

    /**
     * Nom de la balise status.
     */
    private static final String STATUS = "status";
    /**
     * Nom de la balise code.
     */
    private static final String CODE = "code";
    /**
     * Nom de la balise message.
     */
    private static final String MESSAGE = "message";

    /**
     * Réponse de l'API getdistrict.
     */
    private Answer<T> answer;

    /**
     * Objet OpenDataApi courant.
     */
    private T currentObjet;

    /**
     * StringBuilder servant au parsing xml.
     */
    private StringBuilder contenu;

    @Override
    public void characters(char[] cars, int start, int length) throws SAXException {
        super.characters(cars, start, length);
        contenu.append(cars, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (answer != null) {
            if (qName.equals(getBaliseData())) {
                answer.getData().add(currentObjet);
            } else {
                if (contenu != null) {
                    remplirObject(currentObjet, qName, contenu.toString());
                }
            }
            if (contenu != null) {
                contenu.setLength(0);
            }
        }
    }

    /**
     * Getter.
     *
     * @return réponse contenant le détail.
     */
    public Answer<T> getAnswer() {
        return answer;
    }

    /**
     * Méthode à implémenter donnant le nom de la balise englobante.
     *
     * @return le nom de la balise englobante.
     */
    protected abstract String getBaliseData();

    /**
     * Méthode à implémenter créant un nouvel objet OpenDataApi.
     *
     * @return nouvel objet OpenDataApi.
     */
    protected abstract T getNewObjet();

    /**
     * Méthode à implémenter remplissant le contenu d'un objet OpenDataApi.
     *
     * @param currentObject   objet courant.
     * @param baliseName      nom de la balise.
     * @param contenuOfBalise contenu de la balise.
     */
    protected abstract void remplirObject(T currentObject, String baliseName, String contenuOfBalise);

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        contenu = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals(ANSWER)) {
            answer = new Answer<T>();
        } else if (qName.equals(STATUS) && answer.getStatus() == null) {
            answer.setStatus(new StatusApi());
            answer.getStatus().setCode(attributes.getValue(attributes.getIndex(CODE)));
            answer.getStatus().setMessage(attributes.getValue(attributes.getIndex(MESSAGE)));
        } else if (qName.equals(getBaliseData())) {
            currentObjet = getNewObjet();
        }
        contenu.setLength(0);
    }

}
