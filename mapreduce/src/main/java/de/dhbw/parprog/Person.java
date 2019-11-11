package de.dhbw.parprog;

public class Person {
    public final String name, vorname;
    public final int alter;
    public final boolean male;

    public Person(String vorname, String name, int alter, boolean male) {
        this.name = name;
        this.vorname = vorname;
        this.alter = alter;
        this.male = male;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", alter=" + alter +
                ", male=" + male +
                '}';
    }

	public Integer getAge() {
		return this.alter;
	}

	public String getNachname() {
		return this.name;
	}

	public Object isMale() {
		return (this.male == true);
	}
}
