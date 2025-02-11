package res;


/**             In diesem Interface befinden sich Literale, damit der Code übersichtlich bleibt.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public interface Strings
{
    String SIMIKOLON = ";";
    String EINHEIT_ZEITMESSUNG = " Millisekunden";
    String EINHEIT_ANACHLUSSLEISTUNG = " Kilowatt";
    String LEERZEICHEN = " ";
    String LADESTATION_PRE_ANBIETER = "Eine Ladestation von '";
    String LADESTATION_POST_ANBIETER = "' wurde nicht hinzugefuegt, da die Geokoordinaten fehlerhaft sind.";
    String FILE_NAME = "./src/res/LadeStationen.csv";
    String FILE_NAME_ZUM_TESTEN = "./src/res/LadeStationenZumTesten.csv";
    String START_PLZ_FRAGE ="Bitte die Startpostleitzahl eingeben";
    String END_PLZ_FRAGE = "Bitte die Zielpostleitzahl eingeben";
    String FALSCHE_PLZ_EINGABE = "Es lag eine fehlerhafte Eingabe vor. Die eingegebenen Postleitzahlen müssen in der folgenden Liste auftauchen.";
    String GUELTIGE_PLZ_SIND = "Gültige Postleitzahlen sind: ";
    String REICHWEITE_FALSCH = "Die Reichweite des Autos muss eine Zahl in Kilometer sein! Bitte nicht die Einheit angeben!";
    String REICHWEITE_FRAGE  = "Welche Reichweite hat das Auto?";
    String EPSILONUMGEBUNG_FALSCH = "Die Epsilonumgebung muss eine ganze Zahl sein, die größer asl 0 ist!";
    String EPSILONUMGEBUNG_ABFRAGE = "Eingabe für Epsilonumgebung";
    String LISTE_ALLER_VERBINDUNGEN = "Hier ist die Liste aller Verbindungen zwischen Ladestationen:";
    String PFEIL = " --> ";
    String ABSATZ = "\n";
    String WEG_VORHANDEN_PRE = "Ein Weg von ";
    String DOPPELPUNKT = ": ";
    String PLZ = "Postleitzahl: ";
    String ANSCHLUSSLEISTUNG = "Anschlussleistung: ";
    String SORTIEREN_ZEIT = "Das Sortieren der Ladestationen dauerte: ";
    String EINLESEN_ZEIT = "Das Einlesen und Überprüfen der Daten dauerte: ";
    String LOESCHEN_ZEIT = "Das Löschen redudanter Daten dauerte: ";
    String GRAPH_ERSTELLEN = "Das Erstellen des Graphen dauerte: ";
    String WEG_FINDER = "Die Suche nach einem Weg dauerte: ";
    String WEG_VORHANDEN_NACH = " nach ";
    String WEG_VORHANDEN_POST = " ist vorhanden.";
    String WEG_NICHT_VORHANDEN_POST = " ist nicht vorhanden.";
    String ES_SIND = "Es sind ";
    String LADESTATIONEN_EINGETRAGEN = " Ladestationen mit validen Argumenten in der Liste.";
    String AUSGABE_LISTE_WURDE_SORTIERT = "Die Liste wurde nach Postleitzahlen aufsteigend sortiert. Bei gleicher Postleitzahl ist die Ladestation mit der höheren Leistung zuerst in der Liste.";
}
