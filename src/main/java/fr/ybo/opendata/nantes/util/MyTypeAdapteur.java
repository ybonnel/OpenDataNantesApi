/*
 * Copyright 2011 Damien Raude-Morvan <drazzib@drazzib.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.ybo.opendata.nantes.util;

import fr.ybo.moteurcsv.adapter.AdapterCsv;
import fr.ybo.opendata.nantes.modele.Type;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Chargement du Type à partir de l'identifiant numérique dans
 * le fichier CSV.
 *
 * @author Damien Raude-Morvan
 */
public class MyTypeAdapteur implements AdapterCsv<Type> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Type parse(String chaine) {
        NumberFormat nf = NumberFormat.getIntegerInstance(Locale.FRENCH);
        int value = -1;
        try {
            value = nf.parse(chaine).intValue();
        } catch (ParseException e) {
        }
        return Type.fromId(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(Type objet) {
        return String.valueOf(objet.getId());
    }
}
