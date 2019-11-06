package de.dhbw.parprog;

import de.dhbw.parprog.processemu.Pipe;
import de.dhbw.parprog.processemu.ProcessEmu;
import org.apache.commons.lang.NotImplementedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class JavaPipe {
    /* Hinweis: BufferedReader besitzt eine readLine-Methode.
       Von InputStream zu Buffered Reader:
       BufferedReader in = new BufferedReader(new InputStreamReader(inputstream));
     */

    public static void main(String[] args) throws IOException {
        // Skizze: Pipe erzeugen
        Pipe pipe = new Pipe();
        // Skizze: "Prozess" erzeugen
        ProcessEmu.fork(pipe, new CalcTask());

        // TODO: Mit eigener Implementierung füllen
        throw new NotImplementedException();
    }
}
