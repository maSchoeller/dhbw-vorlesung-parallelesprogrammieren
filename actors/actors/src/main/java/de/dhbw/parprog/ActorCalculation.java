package de.dhbw.parprog;

import akka.actor.ActorSystem;
import akka.actor.Terminated;
import scala.NotImplementedError;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class ActorCalculation {
    public int doCalculation() {
        ActorSystem system = ActorSystem.create("actors");

        // TODO: Eigene Implementierung hier einfügen
        throw new NotImplementedError();

        Future<Terminated> theEnd = system.terminate();
        try {
            Await.ready(theEnd, Duration.apply(10, TimeUnit.SECONDS));
        } catch (InterruptedException | TimeoutException e) {
        }
    }

	public static void main(String[] args) {
        ActorCalculation calc = new ActorCalculation();
        System.out.println("Important calculation - with actors");

        System.out.println("The result is " + calc.doCalculation());
    }
}
