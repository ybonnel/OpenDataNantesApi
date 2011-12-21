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
 * Enumération qui représente les différents sous-types disponibles
 * dans les listes des équipements publics de Nantes Métropole.
 * Un type est spécifique à une catégorie particulière.
 *
 * @author Damien Raude-Morvan
 * @see Categorie
 */
public enum Type {

    UNKNOWN(-1, "UNKNOWN"),

    // Theme == CULTURE

    CHATEAU(10101, "Château"),
    MUSEE(10102, "Musée"),

    BIBLIOTHEQUE(10301, "Bibliothèque"),
    LUDOTHEQUE(10302, "Ludothèque"),
    MEDIATHEQUE(10303, "Médiathèque"),

    // Theme == SERVICE PUBLIC

    MAIRIE(30101, "Mairie - Hotel de Ville"),
    MAIRIE_ANNEXE(30102, "Mairie annexe"),
    MISSION_QUART(30103, "Mission de quartier"),
    SERVICE_MUNICIPAL(30104, "Service municipal"),
    ASSOCIE_MAIRIE(30105, "Structure associée mairie"),

    SIEGE(30201, "Siège"),
    SERVICE_COM(30202, "Service communautaire"),
    POLE_PROX(30203, "Pôle de proximité"),
    ASSOCIE_NM(30204, "Structure associée NM"),

    // Theme == VIE PRATIQUE

    HABITAT_AIRE_ACCUEIL(80101, "Aire d'accueil"),
    HABITAT_HLM(80102, "Organisme HLM"),

    PARC_CIMETIERE(80201, "Cimetière"),
    PARC_JARDIN(80202, "Jardins familiaux"),
    PARC(80203, "Parc"),

    ENV_DECHET(80301, "Déchet"),
    ENV_PEDAGAGIE(80302, "Pédagogie"),

    SERVICE_TRANSPORT(80401, "Agence de transport"),
    SERVICE_EDFGDF(80502, "EDF-GDF"),
    SERVICE_POSTE(80603, "La Poste"),
    SERVICE_MARCHE(80704, "Marché"),

    // Theme == JUSTICE & SECURITE

    POLICE_NAT(90401, "Police nationale"),
    POLICE_MUN(90401, "Police municipale"),

    // Theme == DEPLACEMENT

    AERIEN(100401, "Aérien"),
    FLUVIAL(100402, "Fluvial"),
    RAIL(100403, "Rail"),
    ROUTE(100404, "Route"),

    // Theme == ACTION_SOCIALE

    FORMATION(110101, "Formation"),
    ACCUEIL(110102, "Accueil"),
    HEBERGEMENT(110103, "Hébergement"),

    ACCUEIL_INSERTION(110201, "Accueil insertion"),
    HEBERGEMENT_INSERTION(110202, "Hébergement insertion"),
    RESTAURATION(110203, "Restauration"),
    ESS(110204, "Economie Sociale et Familiale"),
    INSERTION_PROF(110205, "Insertion professionnelle"),

    CENTRE_MEDIC(110301, "Centres Médico Sociaux"),
    CHU(110302, "Hôpital-Clinique"),
    AUTRE(110303, "Autre"),

    // Theme == VIE SOCIALE

    CRECHE(120101, "Multi-accueil (Crèche)"),
    RAM(120102, "R.A.M."),

    CENTRE_LOISIR(120201, "Centre de loisirs"),
    FOYER(120202, "Foyer"),
    LOCAUX_JEUNES(120203, "Locaux jeunes"),

    B_U(120301, "Bibliothèque universitaire"),
    RESIDENCE_U(120302, "Résidence universitaire"),
    RESTO_O(120303, "Restaurant universitaire"),
    ETUDIANT_DIVERS(120304, "Divers"),

    PA_ACCEUIL(120401, "Accueil personne agée"),
    PA_LOGEMENT(120402, "Logement"),
    PA_MAISON_RETRAITE(120403, "Maison de retraite"),
    PA_RESIDENCE_SERVICE(120404, "Résidence service"),
    PA_RESTAURANT(120405, "Restaurant club"),
    PA_SOINS(120406, "Unité de soins"),

    ASSOC_MAISON(120501, "Maison d'associations"),
    ASSOC_POLE(120502, "Pôle associatif"),
    ASSOC_SALLE(120503, "Salle associative"),
    ASSOC_MAISON_QUARTIER(120504, "Maison de quartier"),
    ASSOC_CENTRE_SOCIO(120505, "Centre socioculturel")

    ;

    private int id;
    private String label;

    Type(final int id, final String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public int getId() {
        return this.id;
    }

    public static Type fromId(final int id) {
        for (Type b : Type.values()) {
            if (id == b.id) {
                return b;
            }
        }
        return UNKNOWN;
    }
}
