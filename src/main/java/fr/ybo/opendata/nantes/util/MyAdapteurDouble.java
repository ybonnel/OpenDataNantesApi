package fr.ybo.opendata.nantes.util;

import fr.ybo.moteurcsv.adapter.AdapterDouble;

/**
 * Adapteur pour les doubles avec une ",".
 */
public class MyAdapteurDouble extends AdapterDouble {

    @Override
    public Double parse(String chaine) {
        return super.parse(chaine.replace(',', '.'));
    }

    @Override
    public String toString(Double objet) {
        return super.toString(objet).replace('.', ',');
    }
}
