package de.dhbw.parprog;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * AVGAgeSubscriber
 */
public class CountMaleSubscriber implements Subscriber<Person> {
    private Subscription subscription;
    private int count = 0;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Person item) {
        if (item.male) {
            count++;
        }
        System.out.println("Aktuelle Anzahl der Männer: " + count);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onComplete() {
        System.out.println("Finale Anzahl der Männer: " + count);
    }

    
}