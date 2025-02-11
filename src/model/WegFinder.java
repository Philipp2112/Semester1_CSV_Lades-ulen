
package model;

import control.DatenManager;
import control.ZeitMessung;
import res.MeineKonstante;
import res.Strings;
import util.MyUtil;

import java.util.ArrayList;

/**             In dieser Klasse wird nach einem möglichen Weg zwischen zwei Objekten des Typs "LadeStation" gesucht.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public class WegFinder
{
    private Graph graph = null;
    private int startPLZ = 0;
    private int endPLZ =0;


    /**
     *          Diese Methode findet einen Weg von einer Ladestation in dem Ort, mit der ersten eingegebenen Postleitzahl zu einer Ladestation in dem Ort mit der anderen eingegebenen Postleitzahl.
     *          Die Suche des Weges basiert auf einer Tiefensuche.
     * @pre     Ein Graph beinhaltet Objekte des Typs "LadeStation" als Keys und Values.
     * @post    Das Programm wurde beendet, nachdem der Nutzer über die Existenz beziehungsweise Nichtexistenz eines Weges informiert wurde.
     * @param   graph In diesem Graphen wird nach den Haltestellen für den Weg zwischen den Orten gesucht.
     * @param   startPLZ  Die Postleitzahl, von dem Ort, wo der Weg beginnen soll.
     * @param   endPLZ Die Postleitzahl, von dem Ort, wo der Weg enden soll.
     */
    public void findeWeg(Graph graph, int startPLZ, int endPLZ, ArrayList<LadeStation> besuchteLadeStationen) throws LadestationNichtInGraphException
    {
        ZeitMessung.start();
        this.startPLZ = startPLZ;
        this.endPLZ = endPLZ;
        this.graph = graph;
        testeObStartUndZielPostleitzahlImGraphenSind();
        LadeStation startLadeStation = DatenManager.erhalteLadeStationVonPostleitzahl(startPLZ, graph);
        for (LadeStation ladeStation:graph.getGraph().get(startLadeStation))
        {
            if (! besuchteLadeStationen.contains(ladeStation))
            {
                if (ladeStation.getPostleitzahl() == endPLZ)
                {
                    wegIstVorhanden();
                }else
                {
                    besuchteLadeStationen.add(startLadeStation);
                    findeWeg(graph, ladeStation.getPostleitzahl(), endPLZ, besuchteLadeStationen);
                }
            }
        }
        keinWegVorhanden();

    }


    /**         Diese Methode gibt aus, dass ein Weg vorhanden ist.
     *          Außerdem gibt die Methode den Startort sowie den Zielort aus.
     * @pre     Ein Weg vom Startort zum Zielort ist vorhanden.
     * @post    Dem Nutzer wurde angezeigt, dass ein Weg vorhanden ist. Das Programm wird beendet.
     */
    private void wegIstVorhanden()
    {
        MyUtil.normalAusgeben(Strings.WEG_VORHANDEN_PRE + DatenManager.erhalteLadeStationVonPostleitzahl(startPLZ, graph).getOrt() + Strings.WEG_VORHANDEN_NACH + DatenManager.erhalteLadeStationVonPostleitzahl(endPLZ, graph).getOrt() + Strings.WEG_VORHANDEN_POST);
        MyUtil.normalAusgeben(Strings.WEG_FINDER + ZeitMessung.berechneVergangeneZeitInMillisekunden() + Strings.EINHEIT_ZEITMESSUNG);
        System.exit(MeineKonstante.ZERO);
    }

    /**         Diese Methode überprüft, ob ein Objekt vom Typ "LadeStation" die Postleitzahl vom Startort und ein Objekt vom Typ "LadeStation" die Postleitzahl vom Zielort hat.
     * @pre     Startpostleitzahl und Zielpostleitzahl wurden angegeben.
     * @post    Wenn keine Exception geworfen wird, passiert nichts.
     *          Wenn eine Exception geworfen wird, kann diese gefangen werden.
     * @throws  LadestationNichtInGraphException wird geworfen, wenn eine oder beide Postleitzahlen nicht zugeordnet werden können.
     */
    private void testeObStartUndZielPostleitzahlImGraphenSind() throws LadestationNichtInGraphException
    {
        if (! (graph.getGraphKeys().contains(DatenManager.erhalteLadeStationVonPostleitzahl(startPLZ, graph)) && graph.getGraphKeys().contains(DatenManager.erhalteLadeStationVonPostleitzahl(endPLZ, graph))))
        {
            throw new LadestationNichtInGraphException();
        }
    }

    /**         Diese Methode gibt an, wenn kein Weg im Graphen zwischen Startort und Zielort vorhanden ist.
     * @pre     Es ist kein Weg vom Startort zum Zielort vorhanden.
     * @post    Der Nutzer wird informiert, dass es kein Weg gibt und das Programm wird beendet.
     */
    private void keinWegVorhanden()
    {
        MyUtil.fehlerAusgeben(Strings.WEG_VORHANDEN_PRE + DatenManager.erhalteLadeStationVonPostleitzahl(startPLZ, graph).getOrt() + Strings.WEG_VORHANDEN_NACH + DatenManager.erhalteLadeStationVonPostleitzahl(endPLZ, graph).getOrt() + Strings.WEG_NICHT_VORHANDEN_POST);
        System.exit(MeineKonstante.ZERO);
    }


}

