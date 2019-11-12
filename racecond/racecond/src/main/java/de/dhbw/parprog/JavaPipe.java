package de.dhbw.parprog;
import de.dhbw.parprog.processemu.Pipe;
import de.dhbw.parprog.processemu.ProcessEmu;
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
        Pipe pipe2 = new Pipe();

        // Skizze: "Prozess" erzeugen
        ProcessEmu.fork(pipe, new CalcTask());
        ProcessEmu.fork(pipe2, new CalcTask());

        BufferedReader in = new BufferedReader(new InputStreamReader(pipe.getIn()));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(pipe2.getIn()));

        try {
            int p1 = Integer.parseInt(in.readLine());
            int p2 = Integer.parseInt(in2.readLine());
            System.out.println(p1+p2);
            
        } catch (Exception e) {
            System.out.println("Somthing went wrong!");
            e.printStackTrace();
        }

        // Alternative
        // int count = 10;
        // int sum = 0;
        // List<BufferedReader> bufferList = new ArrayList<BufferedReader>();
        // for (int i = 0; i < count; i++) {
        //     Pipe pipe = new Pipe();
        //     BufferedReader buffer = new BufferedReader(new InputStreamReader(pipe.getIn()));
        //     bufferList.add(buffer);
        //     ProcessEmu.fork(pipe, new CalcTask(i));
        // }

        // for (int i = 0; i < count; i++) {
        //     String text = bufferList.get(i).readLine();
        //     System.out.println(text + " \t" + i);
        //     sum += Integer.parseInt(text);
        // }
        // System.out.println(sum);

    }
}
