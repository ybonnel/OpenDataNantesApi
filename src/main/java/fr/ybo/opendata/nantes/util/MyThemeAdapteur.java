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
import fr.ybo.opendata.nantes.modele.Theme;

/**
 * Chargement du Theme à partir de l'identifiant numérique dans
 * le fichier CSV.
 *
 * @author Damien Raude-Morvan
 */
public class MyThemeAdapteur implements AdapterCsv<Theme> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Theme parse(String chaine) {
        int value = Integer.valueOf(chaine);
        return Theme.fromId(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(Theme objet) {
        return String.valueOf(objet.getId());
    }
}
