package de.dhbw.parprog;

import de.dhbw.parprog.processemu.Pipe;
import de.dhbw.parprog.processemu.ProcessWithPipe;
import org.apache.commons.lang.NotImplementedException;

import java.io.*;


/*
   Hinweis: PrintStream besitzt eine println-Methode.
   Von OutputStream zu PrintStream:
   PrintStream printStream = new PrintStream(outputStream);
 */
public class CalcTask implements ProcessWithPipe {
    @Override
    public void main(final Pipe pipe) {
        // TODO: Mit eigener Implementierung füllen
        throw new NotImplementedException();
    }
}
