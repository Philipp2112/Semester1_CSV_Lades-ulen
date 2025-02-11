package util;

import model.LadeStation;
import res.Strings;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**             In dieser Klasse werden Ein- und Ausgaben auf der Konsole gesteuert und verwaltet.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public class MyUtil
{
    /**         Diese Methode gibt den Anbieter, die Postleitzahl und die Anschlussleistung einer ArrayList von Ladestationen aus.
     * @pre     Die ArrayList beinhaltet Objekte des Typs "LadeStation" und ist nicht leer.
     * @post    Die Daten wurden über die Konsole an den Nutzer ausgegeben.
     * @param   daten ArrayList mit Ladestationen.
     */
    public static void gebeAnbieterUndPlzUndAnschlussleitungAus(ArrayList<LadeStation> daten)
    {
        for (int i = 0; i < daten.size(); i++)
        {
            MyUtil.normalAusgeben((i+1) + Strings.DOPPELPUNKT + daten.get(i).getBetreiber() + Strings.SIMIKOLON + Strings.LEERZEICHEN + Strings.PLZ + daten.get(i).getPostleitzahl() + Strings.SIMIKOLON + Strings.LEERZEICHEN + Strings.ANSCHLUSSLEISTUNG + daten.get(i).getAnschlussleistung() + Strings.EINHEIT_ANACHLUSSLEISTUNG);
        }
    }

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**         Diese Methode forder vom Benutzer eine Eingabe einer ganzen Zahl und gibt diese wieder.
     * @pre     Der eingegebene Wert ist eine ganze Zahl.
     * @post    Die eingegebene Zeile wurde ausgelesen und zurückgegeben.
     * @return  Der eingegebene Zahlenwert des Benutzers.
     * @throws  IOException
     */
    public static int intEingabe() throws IOException
    {

        return Integer.parseInt(reader.readLine());
    }

    /**
     * Diese Methode gibt ein Objekt aus.
     * @pre     Das eingegebene Objekt kann ausgegeben werden.
     * @post    Die Daten wurden über die Konsole an den Nutzer ausgegeben.
     * @param   ausgebeObjekt Das übergebene Objekt, das ausgegeben werden soll.
     */
    public static void normalAusgeben(Object ausgebeObjekt)
    {
        System.out.println(ausgebeObjekt);
    }

    /**
     * Diese Methode gibt ein Objekt als Fehlermeldung aus.
     * @pre     Das eingegebene Objekt kann ausgegeben werden.
     * @post    Die Daten wurden über die Konsole als Fehlermeldung an den Nutzer ausgegeben.
     * @param   ausgebeObjekt Das übergebene Objekt, das als Fehlermeldung ausgegeben werden soll.
     */
    public static void fehlerAusgeben (Object ausgebeObjekt)
    {
        System.err.println(ausgebeObjekt);
    }


    /**
     * Diese Methode ermittelt den Abstand zwischen zwei Punkten auf einer Kugel anhand von Geokoordinaten.
     * Die Methode stammt von der Internetseite https://de.acervolima.com/haversine-formel-zum-ermitteln-des-abstands-zwischen-zwei-punkten-auf-einer-kugel/.
     * @author  PRAKHAR7
     * @param   BreitengradEins Breitengrad des ersten Ortes.
     * @param   LaengengradEins Längengrad des ersten Ortes.
     * @param   BreitengradZwei Breitengrad des zweiten Ortes.
     * @param   LaengengradZwei Längengrad des ersten Ortes.
     * @return  Abstand zwischen den beiden Orten in Kilometern.
     */
    public static double haversine(double BreitengradEins, double LaengengradEins,
                                   double BreitengradZwei, double LaengengradZwei)
    {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(BreitengradZwei - BreitengradEins);
        double dLon = Math.toRadians(LaengengradZwei - LaengengradEins);

        // convert to radians
        BreitengradEins = Math.toRadians(BreitengradEins);
        BreitengradZwei = Math.toRadians(BreitengradZwei);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(BreitengradEins) *
                        Math.cos(BreitengradZwei);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }


}
