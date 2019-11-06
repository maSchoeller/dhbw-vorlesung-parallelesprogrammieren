package de.dhbw.parprog;

import akka.actor.AbstractActor;
import akka.actor.Props;
import scala.NotImplementedError;


public class CalcActor extends AbstractActor {
    public static Props getProps() {
        return Props.create(CalcActor.class);
    }

    @Override
    public Receive createReceive() {
        // TODO: Eigene Implementierung hier einfügen
        throw new NotImplementedError();
    }
}
