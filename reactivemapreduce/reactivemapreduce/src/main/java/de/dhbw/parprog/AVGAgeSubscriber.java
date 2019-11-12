package de.dhbw.parprog;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * AVGAgeSubscriber
 */
public class AVGAgeSubscriber implements Subscriber<Person> {

    private Subscription subscription;
    private int sum = 0, count = 0;
    private double avgAlter = 0;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(Person item) {
        count++;
        sum += item.alter;
        avgAlter = sum/count;
        System.out.println("Aktuelle Durchschnittsalter: " + avgAlter);
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        // TODO Auto-generated method stub
        System.out.println("Test avg");
    }

    @Override
    public void onComplete() {
        System.out.println("Finale Durchschnittsalter: " + avgAlter);
    }

    
}