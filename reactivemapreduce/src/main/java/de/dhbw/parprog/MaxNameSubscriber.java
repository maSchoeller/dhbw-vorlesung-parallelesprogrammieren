package de.dhbw.parprog;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * AVGAgeSubscriber
 */
public class MaxNameSubscriber implements Subscriber<Person> {
    private Subscription subscription;
    private int maxSize = 0;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Person item) {
        if (this.maxSize < item.name.length()) {
            this.maxSize = item.name.length();
        }
        System.out.println("Aktuell der längste Name: " + maxSize);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onComplete() {
        System.out.println("Final der längste Name: " + maxSize);
    }

    
}