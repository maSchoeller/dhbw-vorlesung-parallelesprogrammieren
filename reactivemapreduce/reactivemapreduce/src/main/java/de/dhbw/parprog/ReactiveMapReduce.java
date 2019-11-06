package de.dhbw.parprog;

import java.util.concurrent.Flow;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class ReactiveMapReduce {
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Person> publisher = new SubmissionPublisher<>();

        // Pipeline initialisieren
        // TODO: publisher.subscribe(...);
        publisher
        // Alle Personen abrufen
        Person p;
        while ((p = PersonArchive.getPerson()) != null) {
            publisher.submit(p);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Stream schließen
        publisher.close();
        // Auf Pool warten
        ForkJoinPool.commonPool().shutdown();
        ForkJoinPool.commonPool().awaitTermination(1, TimeUnit.SECONDS);
    }
}
