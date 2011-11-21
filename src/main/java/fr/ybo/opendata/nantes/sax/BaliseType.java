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

/**
 * Type de balise.
 */
public enum BaliseType {
    /**
     * Type chaine (converti un String en String).
     */
    STRING {
        @Override
        Object convertir(String data) {
            return data;
        }
    },
    /**
     * Type Boolean (converti un String en boolean).
     */
    BOOLEAN {
        @Override
        Object convertir(String data) {
            return "1".equals(data);
        }
    },
    /**
     * Type Integer (converti un String en int).
     */
    INTEGER {
        @Override
        Object convertir(String data) {
            return Integer.parseInt(data);
        }
    },
    /**
     * Type Double (converti un String en double).
     */
    DOUBLE {
        @Override
        Object convertir(String data) {
            return Double.parseDouble(data);
        }
    };

    /**
     * Converti data dans le type approprié.
     *
     * @param data donnée à convertir.
     * @return l'objet approprié.
     */
    abstract Object convertir(String data);
}
