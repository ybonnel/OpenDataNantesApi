package fr.ybo.opendata.nantes.modele;

import fr.ybo.opendata.nantes.sax.BaliseData;
import fr.ybo.opendata.nantes.sax.BaliseType;
import fr.ybo.opendata.nantes.sax.BaliseXml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe InfoTrafic.
 */
@BaliseData("INFOTRAFIC")
public class InfoTrafic {

    /**
     * Code de l’info trafic.
     */
    private String code;
    /**
     * Intitulé de l’info trafic.
     */
    private String intitule;
    /**
     * Résumé.
     */
    private String resume;
    /**
     * Texte vocal.
     */
    private String texteVocal;
    /**
     * Date de début de perturbation.
     */
    private Date dateDebut;
    /**
     * Date de fin de perturbation.
     */
    private Date dateFin;
    /**
     * Perturbation terminee?
     */
    private boolean terminee;
    /**
     * Tronçons concernés.
     */
    private String troncons;

    /**
     * @return {@link InfoTrafic#code}.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code {@link InfoTrafic#code}.
     */
    @BaliseXml(name = "CODE")
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return {@link InfoTrafic#intitule}.
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * @param intitule {@link InfoTrafic#intitule}.
     */
    @BaliseXml(name = "INTITULE")
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    /**
     * @return {@link InfoTrafic#resume}.
     */
    public String getResume() {
        return resume;
    }

    /**
     * @param resume {@link InfoTrafic#resume}.
     */
    @BaliseXml(name = "RESUME")
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * @return {@link InfoTrafic#texteVocal}.
     */
    public String getTexteVocal() {
        return texteVocal;
    }

    /**
     * @param texteVocal {@link InfoTrafic#texteVocal}.
     */
    @BaliseXml(name = "TEXTE_VOCAL")
    public void setTexteVocal(String texteVocal) {
        this.texteVocal = texteVocal;
    }

    /**
     * @return {@link InfoTrafic#dateDebut}.
     */
    public Date getDateDebut() {
        return dateDebut;
    }

    /**
     * Format des dates.
     */
    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Format des heures.
     */
    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    /**
     * @param dateDebut {@link InfoTrafic#dateDebut}.
     * @throws ParseException problème lors du parse de la date.
     */
    @BaliseXml(name = "DATE_DEBUT")
    public void setDateDebut(String dateDebut) throws ParseException {
        this.dateDebut = SDF_DATE.parse(dateDebut);
    }

    /**
     * @return {@link InfoTrafic#dateFin}.
     */
    public Date getDateFin() {
        return dateFin;
    }

    /**
     * @param dateFin {@link InfoTrafic#dateFin}.
     * @throws ParseException problème lors du parse de la date.
     */
    @BaliseXml(name = "DATE_FIN")
    public void setDateFin(String dateFin) throws ParseException {
        this.dateFin = SDF_TIME.parse(dateFin + " 23:59");
    }

    /**
     * Parse de l'heure de début.
     * @param heureDebut heure de début.
     * @throws ParseException problème lors du parse de la date.
     */
    @BaliseXml(name = "HEURE_DEBUT")
    public void setHeureDebut(String heureDebut) throws ParseException {
        dateDebut = SDF_TIME.parse(SDF_DATE.format(dateDebut) + ' ' + heureDebut);
    }

    /**
     * Parse de l'heure de fin.
     * @param heureFin heure de fin.
     * @throws ParseException problème lors du parse de la date.
     */
    @BaliseXml(name = "HEURE_FIN")
    public void setHeureFin(String heureFin) throws ParseException {
        dateFin = SDF_TIME.parse(SDF_DATE.format(dateFin) + ' ' + heureFin);
    }

    /**
     * @return {@link InfoTrafic#terminee}.
     */
    public boolean isTerminee() {
        return terminee;
    }

    /**
     * @param terminee {@link InfoTrafic#terminee}.
     */
    @BaliseXml(name = "PERTURBATION_TERMINEE", type = BaliseType.BOOLEAN)
    public void setTerminee(boolean terminee) {
        this.terminee = terminee;
    }

    /**
     * @return {@link InfoTrafic#troncons}.
     */
    public String getTroncons() {
        return troncons;
    }

    /**
     * @param troncons {@link InfoTrafic#troncons}.
     */
    @BaliseXml(name = "TRONCONS")
    public void setTroncons(String troncons) {
        this.troncons = troncons;
    }
}
