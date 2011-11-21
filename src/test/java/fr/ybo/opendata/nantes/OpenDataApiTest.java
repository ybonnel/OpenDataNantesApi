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


import org.junit.Before;

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

}
