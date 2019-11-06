package de.dhbw.parprog;

import org.apache.commons.lang.NotImplementedException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;


public class MapReduce {
    /**
     * Wandelt eine Liste von Futures in das Future einer Liste aller Ergebnisse um
     *
     * @param futures
     * @param <T>
     * @return
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

    public CompletableFuture<CalcResult> doAnalysis() {
        // Diese Methode mit eigener Implementierung füllen
        throw new NotImplementedException();
    }
}
