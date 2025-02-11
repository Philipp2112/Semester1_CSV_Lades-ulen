package model;

import control.ZeitMessung;
import res.Strings;
import util.MyUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**             In dieser Klasse wird ein Graph erstellt und verwaltet.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public class Graph
{
    private final ArrayList<LadeStation> arrayListMitLadeStationen;
    private HashMap<LadeStation, ArrayList<LadeStation>> knoten = new HashMap<>();

    /**             Dieser Konstruktor weist die übergebene ArrayList der internen zu.
     * @pre         Eine ArrayList mit Objekten vom Typ "LadeStation" wurde übergeben.
     * @post        Die interne ArrayList hat einen Verweis auf die übergebene ArrayList mit Objekten vom Typ "LadeStation".
     * @param arrayListMitLadeStationen  Die übergebene ArrayList mit Objekten vom Typ "LadeStation".
     */
    public Graph(ArrayList<LadeStation> arrayListMitLadeStationen)
    {
        this.arrayListMitLadeStationen = arrayListMitLadeStationen;
    }

    /**         Diese Methode erzeugt einen Graphen mit einem Objekt des Typs "LadeStation" als Key und einer ArrayList mit Objekten des Typs "LadeStation" als Value.
     *          Die Epsilonumgebung beschriebt den Mindestabstand zwischen zwei Ladesäulen, sodass sie gelöscht werden, wenn sie zu nah aneinander stehen.
     * @pre     Eine Epsilonumgebung wurde übergeben.
     * @post    Ein Graph wurde erstellt und beinhaltet nur Objekte vom Typ "LadeStation" die mindestens eine Epsilonumgebung voneinander entfernt sind.
     * @param   reichweiteAuto Der Mindestabstand, der zwischen zwei Ladesäulen sein muss, damit eine der beiden nicht gelöscht wird.
     * @return  Eine HashMap mit einem Objekt des Typs "LadeStation" als Key und einer ArrayList mit Objekten des Typs "LadeStation" als Value.
     */
    public HashMap<LadeStation, ArrayList<LadeStation>> erzeugeGarphen (int reichweiteAuto)
    {
        ZeitMessung.start();
        HashMap<LadeStation, ArrayList<LadeStation>> graph = new HashMap<>();
        for (int i = 0; i < arrayListMitLadeStationen.size(); i++) //Diese Schleife ist zum Setzen der Keys.
        {
            ArrayList<LadeStation> vorruebergehendeArrayList = new ArrayList<>();
            for (int j = 0; j < arrayListMitLadeStationen.size(); j++) //Diese Schleife ist zum Setzen der Values.
            {
                if(j==i) //Es muss verhindert werden, dass Key und Value die gleiche Ladestation ist.
                {
                    continue;
                }

                if (reichweiteAuto > MyUtil.haversine(arrayListMitLadeStationen.get(i).getBreitengrad(), arrayListMitLadeStationen.get(i).getLaengengrad(), arrayListMitLadeStationen.get(j).getBreitengrad(), arrayListMitLadeStationen.get(j).getLaengengrad()))
                {
                    vorruebergehendeArrayList.add(arrayListMitLadeStationen.get(j));
                }
            }
            graph.put(arrayListMitLadeStationen.get(i), vorruebergehendeArrayList);

        }
        this.setKnoten(graph);
        MyUtil.normalAusgeben(Strings.GRAPH_ERSTELLEN + ZeitMessung.berechneVergangeneZeitInMillisekunden() + Strings.EINHEIT_ZEITMESSUNG);
        return graph;
    }

    /**         Diese Methode gibt einen String für eine schöne Ausgabe einer HashMap zurück.
     * @pre     Eine HashMap mit einem Objekt des Typs "LadeStation" als Key und einer ArrayList mit Objekten des Typs "LadeStation" als Value wurde übergeben.
     * @post    Ein String steht zur Ausgabe bereit.
     * @param   ladeStationArrayListHashMap Die HashMap die ausgegeben werden soll.
     * @return  En String der eine HashMap mit einem Objekt des Typs "LadeStation" als Key und einer ArrayList mit Objekten des Typs "LadeStation" als Value schön anzeigt.
     */
    public static String getStringOf(HashMap<LadeStation, ArrayList<LadeStation>> ladeStationArrayListHashMap)  {
        if (ladeStationArrayListHashMap == null)    {
            return "";
        }
        StringBuilder b = new StringBuilder();
        b.append(Strings.LISTE_ALLER_VERBINDUNGEN);

        Set<Map.Entry<LadeStation, ArrayList<LadeStation>>> set = ladeStationArrayListHashMap.entrySet();
        for (Map.Entry entry : set)  {
            Object key = entry.getKey();
            Object value = entry.getValue();

            b.append(Strings.ABSATZ);
            b.append(key);
            b.append(Strings.PFEIL);
            b.append(value);
        }
        return b.toString();
    }

    public HashMap<LadeStation, ArrayList<LadeStation>> getGraph()
    {
        return knoten;
    }

    /**         Diese Methode gibt alle Keys eines Graphen wieder.
     * @pre     Eine HashMap mit Keys wurde dem Set mit Objekten des Typs "LadeStation" namens "knoten" zugewiesen.
     * @post    Alle Keys des Graphen wurden zurückgegeben.
     * @return  Alle Keys des Graphen.
     */
    public Set<LadeStation> getGraphKeys()
    {
        return knoten.keySet();
    }

    public void setKnoten(HashMap<LadeStation, ArrayList<LadeStation>> knoten)
    {
        this.knoten = knoten;
    }
}
