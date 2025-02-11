package view;

import control.DatenManager;
import model.*;
import res.Strings;
import util.MyUtil;
import java.io.*;
import java.util.ArrayList;

/**             In dieser Klasse wird das Programm gestartet.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public class Main
{
    /**         Diese Methode wird zum Starten des Programms genutzt.
     *@pre      Das Programm wurde gestartet.
     *@post     Das Programm wurde beendet.
     * @param   args Die Programmargumente.
     * @throws  IOException Eine IOException kann auftreten, wenn falsche Daten vom Nutzer eingegeben werden.
     * @throws  LadestationNichtInGraphException Sollte eine Postleitzahl eingegeben werden, die keiner Ladestation im Graphen zugeordnet ist, wird diese Exception geworfen.
     */
    public static void main (String[] args) throws IOException, LadestationNichtInGraphException
    {
        programmKomplett();
        /*test1();*/
        /*test2();*/
        /*test3();*/
        /*test4();*/
    }

    /**         Diese Methode dient zum Testen der Aufgaben des ersten Aufgabenteils.
     * @pre     Das Programm wurde gestartet.
     * @post    Das Programm wurde beendet.
     * @throws  FileNotFoundException Diese Exception wird geworfen, sollte keine Datei im angegebenen Pfad liegen.
     */
    private static void test1() throws FileNotFoundException
    {
        DatenManager datenManager = new DatenManager();
        ArrayList<LadeStation> ladeStations = datenManager.leseDatenZeilenweise(new BufferedReader(new FileReader(Strings.FILE_NAME)));
        MyUtil.gebeAnbieterUndPlzUndAnschlussleitungAus(ladeStations);
    }
    /**         Diese Methode dient zum Testen der Aufgaben des zweiten Aufgabenteils.
     *@pre      Das Programm wurde gestartet.
     *@post     Das Programm wurde beendet.
     * @throws  FileNotFoundException Diese Exception wird geworfen, sollte keine Datei im angegebenen Pfad liegen.
     */
    private static void test2() throws FileNotFoundException
    {
        DatenManager datenManager = new DatenManager();
        ArrayList<LadeStation> ladeStations = datenManager.leseDatenZeilenweise(new BufferedReader(new FileReader(Strings.FILE_NAME_ZUM_TESTEN)));
        MyUtil.gebeAnbieterUndPlzUndAnschlussleitungAus(ladeStations);
        datenManager.sortieren(ladeStations);
        MyUtil.gebeAnbieterUndPlzUndAnschlussleitungAus(ladeStations);
    }
    /**         Diese Methode dient zum Testen der Aufgaben des dritten Aufgabenteils.
     *@pre      Das Programm wurde gestartet.
     *@post     Das Programm wurde beendet.
     * @throws  FileNotFoundException Diese Exception wird geworfen, sollte keine Datei im angegebenen Pfad liegen.
     */
    private static void test3() throws IOException
    {
        DatenManager datenManager = new DatenManager();
        ArrayList<LadeStation> ladeStations = datenManager.leseDatenZeilenweise(new BufferedReader(new FileReader(Strings.FILE_NAME_ZUM_TESTEN)));
        datenManager.sortieren(ladeStations);
        datenManager.loescheEintraegeInEpsilonUmgebung(ladeStations, MyUtil.intEingabe());
        MyUtil.gebeAnbieterUndPlzUndAnschlussleitungAus(ladeStations);
    }
    /**         Diese Methode dient zum Testen der Aufgaben des vierten Aufgabenteils.
     *@pre      Das Programm wurde gestartet.
     *@post     Das Programm wurde beendet.
     * @throws  FileNotFoundException Diese Exception wird geworfen, sollte keine Datei im angegebenen Pfad liegen.
     */
    private static void test4() throws IOException
    {
        DatenManager datenManager = new DatenManager();
        ArrayList<LadeStation> ladeStations = datenManager.leseDatenZeilenweise(new BufferedReader(new FileReader(Strings.FILE_NAME_ZUM_TESTEN)));
        datenManager.sortieren(ladeStations);
        datenManager.loescheEintraegeInEpsilonUmgebung(ladeStations, MyUtil.intEingabe());
        Graph graph = new Graph(ladeStations);
        MyUtil.normalAusgeben(Graph.getStringOf(graph.erzeugeGarphen(MyUtil.intEingabe())));
    }


    /**         Diese Methode beinhaltet den kompletten Ablauf des Programms.
     *          Diese Methode dient zum Testen der Aufgaben des letzten Aufgabenteils.
     *@pre      Das Programm wurde gestartet.
     *@post     Das Programm wurde beendet.
     * @throws  FileNotFoundException Diese Exception wird geworfen, sollte keine Datei im angegebenen Pfad liegen.
     */
    private static void programmKomplett() throws IOException
    {
        DatenManager datenManager = new DatenManager();
        ArrayList<LadeStation> ladeStations = datenManager.leseDatenZeilenweise(new BufferedReader(new FileReader(Strings.FILE_NAME)));
        datenManager.sortieren (ladeStations);
        epsilonumgebung (ladeStations, datenManager);
        MyUtil.gebeAnbieterUndPlzUndAnschlussleitungAus(ladeStations);
        graphErstellenUndAuswerten(new Graph(ladeStations));
    }

    /**         Diese Methode erstellt einen Graphen aus allen Objekten des Typs "LadeStation", die zuvor eingelesen wurden.
     *          Als Key wird im Graph ein Objekt des Typs "LadeStation" sein und als Value eine ArrayList mit Objekten des Typs "LadeStation", die von der Key-Ladestation erreichbar sind. Ein Objekt des Typs "LadeStation" ist erreichbar, wenn die Entfernung kleiner oder gleich der Reichweite des Autos ist.
     *
     * @pre     Die Reichweite des Autos ist eine ganze Zahl, die größer ist als 0.
     *          Der Graph besteht aus einem Objekt des Typen "LadeStation" als Key und einer ArrayList mit Objekten des Typs "LadeStation" als Value.
     * @post    Ein Graph wurde erstellt.
     *          Es wurde überprüft, ob ein Weg von der Start-Postleitzahl zur Ziel-Postleitzahl existiert.
     *          Das Ergebnis der Überprüfung wurde dem Nutzer mitgeteilt.
     * @param   graph Ein Graph, der aus der ArrayList aller Objekte des Typs "LadeStation" besteht.
     */
    private static void graphErstellenUndAuswerten(Graph graph)
    {
        int reichweiteAuto;
        while(true)
        {
            try
            {
                MyUtil.normalAusgeben(Strings.REICHWEITE_FRAGE);
                reichweiteAuto = MyUtil.intEingabe();
                if(reichweiteAuto <=0)
                {
                    throw new NumberFormatException();
                }
                MyUtil.normalAusgeben(Graph.getStringOf(graph.erzeugeGarphen(reichweiteAuto)));
                break;
            } catch (NumberFormatException e)
            {
                MyUtil.fehlerAusgeben(Strings.REICHWEITE_FALSCH);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
        WegFinder wegFinder = new WegFinder();
        while (true)
        {
            try
            {
                MyUtil.normalAusgeben(Strings.START_PLZ_FRAGE);
                int startPLZ = MyUtil.intEingabe();
                MyUtil.normalAusgeben(Strings.END_PLZ_FRAGE);
                int endPLZ = MyUtil.intEingabe();
                wegFinder.findeWeg(graph, startPLZ, endPLZ, new ArrayList<>());
                break;
            } catch (LadestationNichtInGraphException | NumberFormatException e)
            {
                MyUtil.fehlerAusgeben(Strings.FALSCHE_PLZ_EINGABE);
                MyUtil.normalAusgeben( Strings.GUELTIGE_PLZ_SIND + Graph.getStringOf(graph.erzeugeGarphen(reichweiteAuto)));
            }  catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    /**         Diese Methode fragt den Benutzer nach einer Epsilonumgebung.
     *          Dann wird die loescheEintraegeInEpsilonUmgebung() in der Klasse DatenManager aufgerufen.
     * @pre     Es wird eine nicht leere ArrayList übergeben. Die einzugebene Epsilonumgebung ist eine ganze Zahl, größer als 0.
     * @post    Die ArrayList ladeStations besteht nur noch aus Objekten des Typs "LadeStation", die mindestens eine Epsilonumgebung voneinader entfernt sind.
     * @param   ladeStations ArrayList mit Objekten vom Typ "LadeStation".
     * @param   datenManager Objekt vom Typ DatenManager.
     */
    private static void epsilonumgebung(ArrayList<LadeStation> ladeStations, DatenManager datenManager)
    {
        while(true)
        {
            try
            {
                MyUtil.normalAusgeben(Strings.EPSILONUMGEBUNG_ABFRAGE);
                int epsilonumgebung = MyUtil.intEingabe();
                if(epsilonumgebung<=0)
                {
                    throw new NumberFormatException();
                }

                datenManager.loescheEintraegeInEpsilonUmgebung(ladeStations, epsilonumgebung);
                break;
            }catch (NumberFormatException e)
            {
                MyUtil.fehlerAusgeben(Strings.EPSILONUMGEBUNG_FALSCH);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}