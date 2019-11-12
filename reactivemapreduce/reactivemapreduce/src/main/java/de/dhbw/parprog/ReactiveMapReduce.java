package de.dhbw.parprog;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class ReactiveMapReduce {
    
    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<Person> publisher = new SubmissionPublisher<>();
        publisher.subscribe(new AVGAgeSubscriber());
        publisher.subscribe(new CountMaleSubscriber());
        publisher.subscribe(new MaxNameSubscriber());

        // Pipeline initialisieren
        // TODO: publisher.subscribe(...);

        // Alle Personen abrufen
        Person p;
        while ((p = PersonArchive.getPerson()) != null) {
            publisher.submit(p);
            
        }
        // Stream schließen
        publisher.close();
        // Auf Pool warten
        ForkJoinPool.commonPool().shutdown();
        ForkJoinPool.commonPool().awaitTermination(5, TimeUnit.SECONDS);
    }
}
