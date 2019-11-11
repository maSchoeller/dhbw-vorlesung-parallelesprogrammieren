package de.dhbw.parprog;

import de.dhbw.parprog.processemu.Pipe;
import de.dhbw.parprog.processemu.ProcessEmu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;


public class JavaPipe {
    /* Hinweis: BufferedReader besitzt eine readLine-Methode.
       Von InputStream zu Buffered Reader:
       BufferedReader in = new BufferedReader(new InputStreamReader(inputstream)); 
     */

    
    public static void main(String[] args) throws IOException {
        int count = 10;
        int sum = 0;
        CyclicBarrier barrier = new CyclicBarrier(count + 1, () -> {
            System.out.println("Juhu Alle sind fertig");
        });
        List<BufferedReader> bufferList = new ArrayList<BufferedReader>();
        for (int i = 0; i < count; i++) {
            Pipe pipe = new Pipe();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(pipe.getIn()));
            bufferList.add(buffer);
            ProcessEmu.fork(pipe, new CalcTask(i, barrier));
        }

        try {
            System.out.println("Warte auf Threads");
            barrier.await();
        } catch (Exception e) {
            //TODO: handle exception
        }

        for (int i = 0; i < count; i++) {
            String text = bufferList.get(i).readLine();
            System.out.println(text + " \t" + i);
            sum += Integer.parseInt(text);
        }
        System.out.println(sum);
    }
}
