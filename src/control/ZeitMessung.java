package control;

import res.MeineKonstante;

/**             In dieser Klasse wird die Zeitmessung der einzelnen Methoden gesteuert.
 * @author      Philipp Hennken
 * @version     18.0.2
 */
public class ZeitMessung
{
    private static long startTime = System.nanoTime();

    /**         Diese Methode startet die Zeitmessung. Dafür wird die aktuelle Zeit in Nanosekunden als Startzeit gesetzt.
     * @pre     //TODO
     * @post    Die Zeitmessung wurde gestartet.
     */
    public static void start()
    {
        startTime = System.nanoTime();
    }

    /**         Diese Methode berechnet die Zeit, die seit dem Start der Zeitmessung vergangen ist.
     *          Dafür wird die Differenz der jetzigen Zeit und der Zeit zum Startzeitpunkt genutzt.
     *          Die Zahl wird durch 1000 dividiert, damit die Zeit in Millisekunden angegeben werden kann.
     * @pre     Die Zeitmessung muss bereits gestartet haben.
     * @post    Die vergangene Zeit wurde dem Benutzer ausgegeben.
     * @return  Die Zeit die seit dem Start der Zeitmessung vergangen ist in Millisekunden.
     */
    public static long berechneVergangeneZeitInMillisekunden()
    {
        return (System.nanoTime() - startTime)/ MeineKonstante.NANO_TO_MILLI;
    }
}
