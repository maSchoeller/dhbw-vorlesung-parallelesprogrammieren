package de.dhbw.parprog;

import de.dhbw.parprog.processemu.Pipe;
import de.dhbw.parprog.processemu.ProcessWithPipe;

import java.io.*;


/*
   Hinweis: PrintStream besitzt eine println-Methode.
   Von OutputStream zu PrintStream:
   PrintStream printStream = new PrintStream(outputStream);
 */
public class CalcTask implements ProcessWithPipe {
    @Override
    public void main(final Pipe pipe) {
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            //Nothing Todo
        }
        PrintStream printStream = new PrintStream(pipe.getOut());
        printStream.println("42");
    }
}
