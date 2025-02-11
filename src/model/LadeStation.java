package model;

import res.Strings;
import res.MeineKonstante;

/**             In dieser Klasse werden alle relevanten Daten einer Ladestation abgespeichert und verwendbar gemacht.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public class LadeStation
{
    private  final String betreiber;
    private String strasse;
    private String hausnummer;
    private int postleitzahl;
    private String ort;
    private String bundesland;
    private float breitengrad;
    private float laengengrad;
    private float anschlussleistung;


    /**         In diesem Konstruktor werden Objekte vom Typ "LadeStation" erstellt und die relevanten Attribute zugewiesen.
     * @pre     String is not empty.
     * @post    Relevante Attribute wurden zugewiesen.
     * @param   input Eine eingelesene Zeile der auszuwertenden CSV-Datei, die durch Simikola getrennt sind.
     */
    public LadeStation (String input)
    {
        String[] splits = input.split(Strings.SIMIKOLON);
        if(     //Bedingungen für korrekte Geokoordinaten.
                Float.parseFloat(splits[MeineKonstante.BREITENGRAD_INDEX]) > 0 &&
                Float.parseFloat(splits[MeineKonstante.BREITENGRAD_INDEX]) < 90 &&
                Float.parseFloat(splits[MeineKonstante.LAENGENGRAD_INDEX]) > 0 &&
                Float.parseFloat(splits[MeineKonstante.LAENGENGRAD_INDEX]) < 180

            )
        {
            this.betreiber = splits[MeineKonstante.BETREIBER_INDEX];
            this.strasse = splits[MeineKonstante.STRASSE_INDEX];
            this.hausnummer = splits[MeineKonstante.HAUSNUMMER_INDEX];
            this.postleitzahl = Integer.parseInt(splits[MeineKonstante.POSTLEITZAHL_INDEX]);
            this.ort = splits[MeineKonstante.ORT_INDEX];
            this.bundesland = splits[MeineKonstante.BUNDESLAND_INDEX];
            this.breitengrad = Float.parseFloat(splits[MeineKonstante.BREITENGRAD_INDEX]);
            this.laengengrad = Float.parseFloat(splits[MeineKonstante.LAENGENGRAD_INDEX]);
            this.anschlussleistung = Float.parseFloat(splits[MeineKonstante.ANSCHLUSSLEISTUNG_INDEX]);
        }else
        {   //Bei Fehlerhaften Geokoordinaten werden nur Ort und Betreiber richtig zugewiesen. Später kann mit der Überprüfung bspw. der Strasse geschaut werden, ob ein Fehler bei dieser LAdestation vorlag. Wenn einer Vorlag ist die Aussgae "getStrass()==null" wahr.
            this.betreiber = splits[MeineKonstante.BETREIBER_INDEX];
            this.ort = splits[MeineKonstante.ORT_INDEX];
        }
    }





    public String getBetreiber()
    {
        return betreiber;
    }

    public String getStrasse()
    {
        return strasse;
    }
    public int getPostleitzahl()
    {
        return postleitzahl;
    }

    public String getOrt()
    {
        return ort;
    }
    public float getBreitengrad()
    {
        return breitengrad;
    }
    public float getLaengengrad()
    {
        return laengengrad;
    }
    public Float getAnschlussleistung()
    {
        return anschlussleistung;
    }



    public String toString()
    {
        return String.valueOf(postleitzahl);
    }



}
