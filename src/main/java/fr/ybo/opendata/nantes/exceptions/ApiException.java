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
package fr.ybo.opendata.nantes.exceptions;

/**
 * Exception sur les traitements associés aux API OpenDataApi.
 *
 * @author ybonnel
 */
public class ApiException extends RuntimeException {

    /**
     * Serial.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur avec message et exception.
     *
     * @param message message.
     * @param cause   exception.
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructeur avec message.
     *
     * @param message message.
     */
    public ApiException(String message) {
        super(message);
    }
}
