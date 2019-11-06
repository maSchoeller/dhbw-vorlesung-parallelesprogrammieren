package de.dhbw.parprog;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.pattern.Patterns;
import akka.testkit.TestActorRef;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.fest.assertions.Assertions.assertThat;


@RunWith(JUnit4.class)
public class CalcTest {
    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create("actors");
    }

    @AfterClass
    public static void teardown() {
        Future<Terminated> theEnd = system.terminate();
        try {
            Await.ready(theEnd, Duration.apply(10, TimeUnit.SECONDS));
        } catch (InterruptedException | TimeoutException e) {
        }
    }

    @Test
    public void calculationReturnsCorrectResult() {
        ActorCalculation calc = new ActorCalculation();
        assertThat(calc.doCalculation()).isEqualTo(2310);
    }

    @Test
    public void actorReturnsCorrectResult() throws Exception {
        final Props props = CalcActor.getProps();
        final TestActorRef<CalcActor> ref = TestActorRef.create(system, props, "actorReturnsCorrectResultTest");
        final Future<Object> future = Patterns.ask(ref, new YourMessage(1), 3000);
        assertThat(future.isCompleted()).isTrue();
        assertThat(Await.result(future, Duration.Zero())).isEqualTo(42);
    }
}
