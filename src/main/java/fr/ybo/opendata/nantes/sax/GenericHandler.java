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

import fr.ybo.opendata.nantes.exceptions.ApiException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler SAX générique pour les apis OpenData..
 *
 * @param <T> Objet représentant le xml.
 * @author ybonnel
 */
public class GenericHandler<T> extends ApiHandler<T> {

    /**
     * Balise data.
     */
    private String baliseData;

    /**
     * Map contenant la méthode à appelée pour chaque balise xml.
     */
    private Map<String, Method> mapBaliseMethod = new HashMap<String, Method>();
    /**
     * Map contenant le type associé à chaque balise xml.
     */
    private Map<String, BaliseType> mapBaliseType = new HashMap<String, BaliseType>();

    /**
     * Constructeur de l'objet représentant le xml.
     */
    private Constructor<T> constructor;

    /**
     * @param clazz Classe représentant le xml.
     */
    public GenericHandler(Class<T> clazz) {
        BaliseData annotationData = clazz.getAnnotation(BaliseData.class);
        if (annotationData == null) {
            throw new ApiException("Pas d'annotation BaliseData trouvée pour la classe " + clazz);
        }
        baliseData = annotationData.value();
        try {
            constructor = clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException exception) {
            throw new ApiException("Le constructeur n'a pas été trouvé", exception);
        }

        for (Method method : clazz.getDeclaredMethods()) {
            BaliseXml baliseXml = method.getAnnotation(BaliseXml.class);
            if (baliseXml != null) {
                if (mapBaliseMethod.containsKey(baliseXml.name())) {
                    throw new ApiException("Deux méthodes trouvées avec la même baliseXml");
                }
                mapBaliseMethod.put(baliseXml.name(), method);
                mapBaliseType.put(baliseXml.name(), baliseXml.type());
            }
        }
    }

    @Override
    protected String getBaliseData() {
        return baliseData;
    }

    @Override
    protected T getNewObjet() {
        try {
            return constructor.newInstance();
        } catch (InstantiationException e) {
            throw new ApiException("Problème lors de l'appel au constructeur", e);
        } catch (IllegalAccessException e) {
            throw new ApiException("Problème lors de l'appel au constructeur", e);
        } catch (InvocationTargetException e) {
            throw new ApiException("Problème lors de l'appel au constructeur", e);
        }
    }

    @Override
    protected void remplirObject(T currentObject, String baliseName, String contenuOfBalise) {
        if (contenuOfBalise.length() > 0 && mapBaliseMethod.containsKey(baliseName)) {
            try {
                if (Modifier.isStatic(mapBaliseMethod.get(baliseName).getModifiers()) || currentObject != null) {
                    mapBaliseMethod.get(baliseName)
                            .invoke(currentObject, mapBaliseType.get(baliseName).convertir(contenuOfBalise));
                }
            } catch (IllegalAccessException e) {
                throw new ApiException(
                        "Problème lors de l'appel à la méthode " + mapBaliseMethod.get(baliseName).getName(), e);
            } catch (InvocationTargetException e) {
                throw new ApiException(
                        "Problème lors de l'appel à la méthode " + mapBaliseMethod.get(baliseName).getName(), e);
            }
        }
    }
}
