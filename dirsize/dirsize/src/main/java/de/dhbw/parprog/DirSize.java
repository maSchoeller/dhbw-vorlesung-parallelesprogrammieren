package de.dhbw.parprog;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.NotImplementedException;


public class DirSize {
	public DirStats dirStats(File dir) throws IOException {
		// TODO: Über work-stealing Threadpool verteilte Berechnung hier einfügen
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
		
		DirSize test = new DirSize();
		DirStats result = test.dirStats(startDir);
		System.out.println(result.fileCount + " Dateien, " + result.totalSize + " Bytes.");
	}
}
