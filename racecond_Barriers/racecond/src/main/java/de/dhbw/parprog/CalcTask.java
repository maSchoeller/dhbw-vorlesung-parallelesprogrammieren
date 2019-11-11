package de.dhbw.parprog;

import de.dhbw.parprog.processemu.Pipe;
import de.dhbw.parprog.processemu.ProcessWithPipe;

import java.io.*;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
   Hinweis: PrintStream besitzt eine println-Methode.
   Von OutputStream zu PrintStream:
   PrintStream printStream = new PrintStream(outputStream);
 */
public class CalcTask implements ProcessWithPipe {
    int num = 0;
    CyclicBarrier barrier;

    public CalcTask(int number, CyclicBarrier barrier) {
        this.num = number;
        this.barrier = barrier;
    }

    @Override
    public void main(final Pipe pipe) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintStream printStream = new PrintStream(pipe.getOut());
        Random r = new Random();
        int ret = num * r.nextInt(10);
        printStream.println(ret);
        System.out.println("Warte Tread " + num);
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
