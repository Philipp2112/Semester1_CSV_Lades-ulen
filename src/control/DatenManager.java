package control;

import control.ZeitMessung;
import model.Graph;
import model.LadeStation;
import res.Strings;
import util.MyUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**             In dieser Klasse können Daten eingelesen, sortiert und Graphen erstellt sowie ausgewertet werden.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public class DatenManager
{
    private int anzahlEitraege = 0;

    public int getAnzahlEitraege()
    {
        return anzahlEitraege;
    }

    /**         Diese Methode fügt Objekte der Klasse "LadeStation" zu einer ArrayList hinzu und löscht anschließend alle Ladestationen heraus, deren Geo-Koordinaten nicht möglich sind.
     *          Zusätzlich gibt die Methode die Bearbeitungszeit, sowie die Anzahl der eingelesenen Dateien aus.
     * @pre     Die CSV-Datei, die ausgelesen wird, ist mit Simikola getrennt und beinhaltet alle Attribute einer Ladestation.
     * @post    Die Ladestationen in der ArrayList haben valide Geo-Koordinaten.
     * @param   reader BufferReader der Daten aus einer Datei Zeilenweise einliest.
     * @return  ArrayList mit Objekten des Typs "LadeStation" und korrekten Geo-Koordinaten
     */
    public ArrayList<LadeStation> leseDatenZeilenweise(BufferedReader reader)
    {
        ZeitMessung.start();
        String line = null;
        ArrayList<LadeStation> arrayList = new ArrayList<>();
        try
        {
            while ((line = reader.readLine()) != null) //Bis der Reader keine weitere Zeile zum Auslesen hat.
            {
                arrayList.add(new LadeStation(line));
                anzahlEitraege++;
            }
        }
        catch (IOException e)
        {

        }
        for (int i = 0; i < arrayList.size(); i++)
        {
            if (arrayList.get(i).getStrasse() == null) //Wenn dieser Ausdruck wahr ist, lag ein Fehler in den Geo-Koordinaten vor. Dann wird dem Parameter Strasse kein Wert zugewiesen und er ist damit 0.
            {
                MyUtil.normalAusgeben(Strings.LADESTATION_PRE_ANBIETER + arrayList.get(i).getBetreiber() + Strings.LADESTATION_POST_ANBIETER);
                arrayList.remove(i);
                i--;
                anzahlEitraege--;
            }
        }
        MyUtil.normalAusgeben(Strings.ES_SIND + getAnzahlEitraege() + Strings.LADESTATIONEN_EINGETRAGEN); //Informationen über die fertige Liste werden ausgegeben.
        MyUtil.normalAusgeben(Strings.EINLESEN_ZEIT + ZeitMessung.berechneVergangeneZeitInMillisekunden() + Strings.EINHEIT_ZEITMESSUNG);
        return arrayList;
    }

    /**         Diese Methode sortiert eine ArrayList mit Objekten des Typs "LadeStation" zuerst aufsteigend nach der Postleitzahl.
     *          Bei zwei gleichen Postleitzahlen wird absteigend nach der Anschlussleitung einer Ladesäule sortiert.
     * @pre     Eine ArrayList wird mit mindestens 2 Objekten vom Typ "LadeStation" übergeben.
     * @post    Die ArrayList ist aufsteigend nach Postleitzahlen sortiert. Bei gleicher Postleitzahl wurde absteigend nach der Anschlussleitung sortiert.
     * @param   arrayListZuSortieren Die ArrayList, die sortiert werden soll.
     * @return  Die fertig sortierte ArrayList.
     */
    public ArrayList<LadeStation> sortieren (ArrayList<LadeStation> arrayListZuSortieren)
    {
        ZeitMessung.start();
        arrayListZuSortieren.sort((LadeStation o1, LadeStation o2) ->
        {
            if (o1.getPostleitzahl() == o2.getPostleitzahl()) //wenn die Postleitzahlen gleich sind, wird die Anschlussleistung als Argument genommen, sonst reicht die Postleitzahl.
            {
                return o2.getAnschlussleistung().compareTo(o1.getAnschlussleistung());
            } else
            {
                return Integer.valueOf(o1.getPostleitzahl()).compareTo(o2.getPostleitzahl());
            }
        });
        MyUtil.normalAusgeben(Strings.AUSGABE_LISTE_WURDE_SORTIERT);
        MyUtil.normalAusgeben(Strings.SORTIEREN_ZEIT + ZeitMessung.berechneVergangeneZeitInMillisekunden() + Strings.EINHEIT_ZEITMESSUNG);
        return arrayListZuSortieren;
    }

    /**         Diese Methode löscht Objekte des Typs "LadeStation" in der Epsilonumgebung einer Referenzstation, die vom User eingegeben wird. Eine Vergleichsstation, die sich innerhalb der Epsilonumgebung der Referenzstation befindet, wird gelöscht.
     * @pre     Eine Epsilonumgebung wurde übergeben, die eine ganze Zahl und größer als 0 ist.
     * @pre     Eine sortierte Liste wurde übergeben.
     * @post    Die sortierte Liste beinhaltet nur Objekte vom Typ "LadeStation", die mindestens eine Epsilonumgebung voneinander entfernt sind.
     * @param   sortierteListe Eine ArrayList die Objekte des Typs "LadeStation" enthält und nach der Postleitzahl aufsteigend sortiert ist.
     * @param   epsilonUmgebung Die Entfernung zwischen zwei Ladesäulen, die mindestens bestehen muss, damit die Vergleichsstation nicht gelöscht wird.
     * @return  Eien ArrayList, die sortiert ist und nur Objekte vom Typ "LadeStation" enthält, die mindestens eine Epsilonumgebung voneinander entfernt sind.
     */
    public ArrayList<LadeStation> loescheEintraegeInEpsilonUmgebung(ArrayList<LadeStation> sortierteListe, int epsilonUmgebung)
    {
        ZeitMessung.start();
        for (int i = 0; i < sortierteListe.size(); i++)//Es wird durch alle Einträge der Liste durchiteriert. Dadurch wird immer eine neue Ladestation zur Referenzstation.
        {
            for (int j = i+1; j < sortierteListe.size(); j++)//Es wird durch alle Einträge hinter der Referenzstationen iteriert.
            {
                if(MyUtil.haversine(sortierteListe.get(i).getBreitengrad(), sortierteListe.get(i).getLaengengrad(), sortierteListe.get(j).getBreitengrad(), sortierteListe.get(j).getLaengengrad()) < epsilonUmgebung) //Wenn die Entfernung der beiden Ladesäulen kleiner ist als die Epsilonumgebung, wird die Vergleichsstation herausgelöscht.
                {
                    sortierteListe.remove(j);
                    j--; //"j" muss zurückgesetzt werden, da durch das Rauslöschen, alle anderen Ladestationen nachrutschen. Sonst würde bei jeder Station die herausgelöscht wird, die nächste Ladestation nicht überprüft werden.
                }
            }
        }
        MyUtil.normalAusgeben(Strings.LOESCHEN_ZEIT + ZeitMessung.berechneVergangeneZeitInMillisekunden() + Strings.EINHEIT_ZEITMESSUNG);
        return sortierteListe;
    }

    /**
     *          Falls vorhanden, gibt diese Methode ein Objekt vom Typ "LadeStation" mit der Postleitzahl zurück, die ihr übergeben wird.
     * @pre     Ein Graph beinhaltet als Key ein Objekt des Typs "LadeStation" und als Value eine ArrayList mit Objekten des Typs "LadeStation".
     * @post    Es wird entweder ein Objekt des Typs "LadeStation" oder null übergeben.
     * @param   postleitzahl Die Postleitzahl, die das Objekt vom Typ "LadeStation" haben soll.
     * @param   graph Der Graph, in dem das Objekt vom Typ "LadeStation" gesucht werden soll.
     * @return  Falls vorhanden, das Objekt vom Typ "LadeStation" mit der eingegebenen Postleitzahl, sonst null.
     */
    public static LadeStation erhalteLadeStationVonPostleitzahl (int postleitzahl, Graph graph)
    {
        for (LadeStation ladestation : graph.getGraphKeys()) //Alle Ladestationen des Graphen werden durchsucht.
        {
            if (ladestation.getPostleitzahl() == postleitzahl) //Wenn die Postleitzahl der Ladestation gleich der gesuchten Postleitzahl ist, wurde eine passende Ladestation gefunden.
            {
                return ladestation;
            }
        }
        return null;
    }



}
