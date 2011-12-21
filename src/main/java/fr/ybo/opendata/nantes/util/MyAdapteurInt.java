package fr.ybo.opendata.nantes.util;

import fr.ybo.moteurcsv.adapter.AdapterCsv;
import fr.ybo.moteurcsv.adapter.AdapterDouble;
import fr.ybo.opendata.nantes.modele.Categorie;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Adapteur pour les doubles avec une ",".
 */
public class MyAdapteurInt implements AdapterCsv<Integer> {

    @Override
    public Integer parse(String chaine) {
        NumberFormat nf = NumberFormat.getIntegerInstance(Locale.FRENCH);
        int value = -1;
        try {
            value = nf.parse(chaine).intValue();
        } catch (ParseException e) {
        }
        return value;
    }

    @Override
    public String toString(Integer objet) {
        return objet.toString();
    }
}
