package de.dhbw.parprog;

import java.util.Arrays;
import java.util.List;


public class PersonArchive {
    private static List<Person> personen = Arrays.asList(
            new Person("Dirk", "Ostermann", 31, true),
            new Person("Marie", "Brandt", 55, false),
            new Person("Anja", "Baumgaertner", 72, false),
            new Person("Katharina", "Neustadt", 80, false),
            new Person("Felix", "Blau", 74, true),
            new Person("Maria", "Frueh", 49, false),
            new Person("Kevin", "Lemann", 28, true),
            new Person("Lucas", "Achen", 53, true),
            new Person("Bernd", "Schiffer", 35, true),
            new Person("Susanne", "Moeller", 43, false)
    );
    private static int personenPtr = 0;

    public static synchronized int getPersonenListSize() {
        return personen.size();
    }

    public static Person getPerson() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) { }
        synchronized (personen) {
            if (personenPtr < personen.size()) {
                Person result = personen.get(personenPtr);
                personenPtr++;
                return result;
            } else {
                return null;
            }
        }
    }
}
