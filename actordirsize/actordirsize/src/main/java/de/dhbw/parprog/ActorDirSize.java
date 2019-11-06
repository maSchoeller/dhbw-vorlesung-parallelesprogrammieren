package de.dhbw.parprog;

import java.io.File;
import java.io.IOException;

import akka.actor.ActorSystem;
import org.apache.commons.lang.NotImplementedException;


public class ActorDirSize {
	public DirStats dirStats(ActorSystem system, File dir) throws IOException {
        // TODO: Mit Akka verteilte Berechnung hier einfügen
        throw new NotImplementedException();
	}

	public static void main(String[] args) throws IOException {
		if (args.length<1) {
			System.out.println("Benötigter Parameter: Startverzeichnis");
			System.exit(1);
		}
		File startDir = new File(args[0]);
		if (!startDir.isDirectory()) {
			System.out.println("Dies ist kein Verzeichnis!");
			System.exit(1);
		}
		
		ActorDirSize test = new ActorDirSize();
        ActorSystem system = ActorSystem.create("actors");
        try {
            DirStats result = test.dirStats(system, startDir);
            System.out.println(result.fileCount + " Dateien, " + result.totalSize + " Bytes.");
        } finally {
            system.terminate();
        }
	}
}
