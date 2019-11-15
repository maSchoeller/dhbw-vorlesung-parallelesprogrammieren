package de.dhbw.parprog;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.routing.RoundRobinPool;



public class CalcActor extends AbstractActor {
    public static Props getProps() {
        return Props.create(CalcActor.class);
    }

    public CalcActor() {
        super();
        // getContext().actorOf(new RoundRobinPool(5).props(CalcActor.getProps()),"calculator");
    }

    @Override
    public Receive createReceive() {

        return receiveBuilder()
            .match(Request.class, r -> {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
                sender().tell(new Response(r.getNumber()*42), self());
            })
            .build();
    }
}
