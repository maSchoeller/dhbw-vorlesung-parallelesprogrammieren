package de.dhbw.parprog;

import akka.actor.*;
import akka.pattern.*;
import akka.routing.RoundRobinPool;
import scala.compat.java8.FutureConverters;
import scala.concurrent.*;
import scala.concurrent.Future;
import scala.concurrent.duration.*;

import java.util.*;
import java.util.concurrent.*;


public class ActorCalculation {
    public int doCalculation() {
        ActorSystem system = ActorSystem.create("myactors");


        ActorRef calcActor = system.actorOf(new RoundRobinPool(5).props(CalcActor.getProps()), "calculator");
        List<Future<Object>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(Patterns.ask(calcActor, new Request(1), 3000));
        }

        for (Future<Object> future : results) {
            System.out.println(future.isCompleted());
        }
        int ret = results.stream().mapToInt(r ->
        {
            try {
                
                return ((Response)Await.result(r,Duration.create(2000, TimeUnit.SECONDS))).GetResult();
            } catch (Exception e) {
                return 0;    
            }
        }).sum();

        Future<Terminated> theEnd = system.terminate();
        try {
            Await.ready(theEnd, Duration.apply(10, TimeUnit.SECONDS));
        } catch (InterruptedException | TimeoutException e) {
        }
        return ret;
    }

	public static void main(String[] args) {
        ActorCalculation calc = new ActorCalculation();
        System.out.println("Important calculation - with actors");

        System.out.println("The result is " + calc.doCalculation());
    }
}
