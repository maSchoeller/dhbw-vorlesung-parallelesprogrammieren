package de.dhbw.parprog;

import de.dhbw.parprog.processemu.Pipe;
import de.dhbw.parprog.processemu.ProcessWithPipe;

import java.io.*;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/*
   Hinweis: PrintStream besitzt eine println-Methode.
   Von OutputStream zu PrintStream:
   PrintStream printStream = new PrintStream(outputStream);
 */
public class CalcTask implements ProcessWithPipe {
    int num = 0;
    CountDownLatch allFinish;

    public CalcTask(int number, CountDownLatch allFinish){
        this.num = number;
        this.allFinish = allFinish;
    }
    @Override
    public void main(final Pipe pipe) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PrintStream printStream = new PrintStream(pipe.getOut());
        Random r = new Random();
        int ret = num * r.nextInt(10);
        printStream.println(ret);
        System.out.println("Beende Tread " + num);
        allFinish.countDown();
    }
}
