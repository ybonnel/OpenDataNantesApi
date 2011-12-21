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
 * Enumération qui représente les différents catégories
 * des équipements publics de Nantes Métropole.
 * Une catégorie est spécifique à un thème particulier.
 *
 * @author Damien Raude-Morvan
 * @see Theme
 */
public enum Categorie {

    UNKNOWN(-1, "UNKNOWN"),

    // Theme == CULTE

    CATHOLIQUE(201, "Catholique"),
    MUSULMAN(202, "Musulman"),
    JUIF(203, "Juif"),
    PROTESTANT(204, "Protestant"),
    ORTHODOXE(205, "Orthodoxe"),
    BOUDHISTE(206, "Bouddhiste"),

    // Theme == SERVICE PUBLIC

    MAIRIE(301, "Mairie"),
    NANTES_METROPOLE(302, "Nantes Métropole"),
    CG_44(303, "Conseil Général"),
    CR_PDL(304, "Conseil Régional"),
    ETAT(305, "Services de l'Etat"),
    ETB_PUBLIC(306, "Organisme public"),
    AMBASSADE(307, "Consulat et Ambassade"),
    EMPLOI(308, "Emploi"),
    SOCIAL(309, "Social"),

    // Theme == ENSEIGNEMENT

    MATERNELLE(501, "Maternelle"),
    ELEMENTAIRE(502, "Elémentaire"),
    PRIMAIRE(503, "Primaire"),
    COLLEGE(504, "Collège"),
    LYCEE(505, "Lycée"),
    SUPERIEUR(506, "Supérieur"),
    CENTRE_FORMATION(507, "Centre de formation"),
    CENTRE_RECHERCHE(508, "Centre de recherche"),

    // Theme == SPORT

    CENTRE_SPORT(601, "Centre sportif"),
    CIRCUIT_PLEIN_AIR(602, "Circuit de plein air"),
    GYMNASE(603, "Gymnase"),
    PISCINE(604, "Piscine"),
    PLAINE_JEUX(605, "Plaine de jeux"),
    STADE(606, "Stade"),
    TENNIS(607, "Tennis"),
    AUTRE_EQUIP(608, "Autre équipement"),
    ESPACE_SPORTIF(609, "Espace Sportif de Proximité"),

    // Theme == VIE PRATIQUE

    HABITAT(801, "Habitat"),
    PARC(802, "Parc"),
    ENVIRONNEMENT(803, "Environnement"),
    SERVICE(804, "Service"),

    // Theme == JUSTICE & SECURITE

    JUSTICE(901, "Justice"),
    GENDARME(902, "Gendarmerie"),
    POMPIER(903, "Pompiers"),
    POLICE(904, "Police"),
    ARMEE(905, "Armée"),

    // Theme == DEPLACEMENT

    PARKING(1001, "Parking"),
    P_PLUS_R(1002, "Parking relais"),
    BICLOO(1003, "Station Bicloo"),
    POI_TRANSPORT(1004, "Transport en commun"),

    // Theme == ACTION SOCIALE

    HANDICAP(1101, "Handicap"),
    INSERTION(1102, "Insertion"),
    SANTE(1103, "Santé"),


    // Theme == VIE SOCIALE

    PETITE_EFANCE(1201, "Petite enfance"),
    JEUNESSE(1202, "Jeunesse"),
    ETUDIANT(1203, "Etudiant"),
    PERSONNE_AGEE(1204, "Personne agée"),
    ASSOCIATION(1205, "Vie associative"),

    ;

    private int id;
    private String label;

    Categorie(final int id, final String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public int getId() {
        return this.id;
    }

    public static Categorie fromId(final int id) {
        for (Categorie b : Categorie.values()) {
            if (id == b.id) {
                return b;
            }
        }
        return UNKNOWN;
    }
}
