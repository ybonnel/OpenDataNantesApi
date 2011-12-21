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
package fr.ybo.opendata.nantes.modele;

/**
 * Enumération qui représente les différents thèmes disponibles
 * dans les listes des équipements publics de Nantes Métropole.
 *
 * @author Damien Raude-Morvan
 */
public enum Theme {

    UNKNOWN(-1, "UNKNOWN"),

    CULTURE(1, "CULTURE"),
    CULTE(2, "CULTE"),
    SERVICE_PUBLIC(3, "SERVICE PUBLIC"),

    ENSEIGNEMENT(5, "ENSEIGNEMENT"),
    SPORTS(6, "SPORTS"),

    VIE_PRATIQUE(8, "VIE PRATIQUE"),
    JUSTICE_SECURITE(9, "JUSTICE & SECURITE"),
    DEPLACEMENT(10, "DEPLACEMENT"),
    ACTION_SOCIALE(11, "ACTION SOCIALE"),
    VIE_SOCIALE(12, "VIE SOCIALE");

    private int id;
    private String label;

    Theme(final int id, final String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public int getId() {
        return this.id;
    }

    public static Theme fromId(final int id) {
        for (Theme b : Theme.values()) {
            if (id == b.id) {
                return b;
            }
        }
        return UNKNOWN;
    }
}
