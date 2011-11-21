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
import fr.ybo.opendata.nantes.util.Connecteur;

import java.io.InputStream;

/**
 * Connecteur de type fichier pour les test U.
 *
 * @see Connecteur
 */
public class FileConnecteur implements Connecteur {

    /**
     * Le fichier à renvoyer.
     */
    private String file;

    /**
     * Constructeur.
     *
     * @param file {@link FileConnecteur#file}.
     */
    public FileConnecteur(String file) {
        this.file = file;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public InputStream openInputStream(String url) throws ApiReseauException {
        return FileConnecteur.class.getResourceAsStream(file);
    }
}
