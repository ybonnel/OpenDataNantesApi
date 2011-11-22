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

package fr.ybo.opendata.nantes.util;


import fr.ybo.opendata.nantes.exceptions.ApiReseauException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Connecteur HTTP.
 */
public class HttpConnecteur implements Connecteur {
    /**
     * Timeout de connexion.
     */
    private static final int CONNECT_TIMEOUT = 10000;
    /**
     * Timeout de lecture.
     */
    private static final int READ_TIMEOUT = 20000;
    @Override
    public InputStream openInputStream(String url) throws ApiReseauException {
        try {
            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setReadTimeout(READ_TIMEOUT);
			connection.addRequestProperty("Accept", "application/xml");
            return connection.getInputStream();
        } catch (IOException socketException) {
            throw new ApiReseauException(socketException);
        }
    }
}
