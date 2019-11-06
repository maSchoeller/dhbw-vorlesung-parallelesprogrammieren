package de.dhbw.parprog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.commons.lang.NotImplementedException;


public class DirSize {
    /**
     * Hilsfmethode: Wandelt eine Liste von Futures (desselben Typs) in ein einzelnes
     * Future einer Liste der Ergebnisse
     *
     * @param futures Liste der Futures
     * @param <T> Gemeinsamer Typ
     * @return Future der Ergebnisliste
     */
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture.thenApply(v ->
                futures.stream().
                        map(future -> future.join()).
                        collect(Collectors.<T>toList())
        );
    }


    public CompletableFuture<DirStats> dirStats(File dir) {
        return  CompletableFuture.supplyAsync(() -> {
            DirStats stats = new DirStats();
            List<CompletableFuture<DirStats>> ret = new ArrayList<>();
            File[] contents = dir.listFiles();
            if (contents!=null) {
                for (File f: contents) {
                    if (f.isDirectory()) {
                        if (!f.getName().equals(".")) {
                            // Behandlung Unterverzeichnis
                            ret.add(dirStats(f));
                        }
                    } else {
                        // Behandlung Datei - f.length() ergibt Dateigröße
                        stats.fileCount++;
                        stats.totalSize += f.length();
                    }
                }
            }
            ret.add(CompletableFuture.completedFuture(stats));
            return sequence(ret);
        }).thenApply( p -> {
            DirStats st = new DirStats();
            try {
                for (DirStats s : p.get()) {
                    st.totalSize += s.totalSize;
                    st.fileCount += s.fileCount;
                }
            }
            catch (Exception e){
            }
            return  st;
        });
    }

	public static void main(String[] args) throws Exception {
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
		DirStats result = test.dirStats(startDir).get();
		System.out.println(result.fileCount + " Dateien, " + result.totalSize + " Bytes.");
	}
}
