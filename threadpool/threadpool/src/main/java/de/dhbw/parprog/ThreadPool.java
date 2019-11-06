package de.dhbw.parprog;

import org.apache.commons.lang.NotImplementedException;


public class ThreadPool {
	public int doCalculation() {
		// TODO: Über Threadpool verteilte Berechnung hier einfügen
		throw new NotImplementedException();
	}

	public static void main(String[] args) {
		ThreadPool test = new ThreadPool();
		System.out.println("Calculation started");
		int result = test.doCalculation();
		System.out.println("Result: " + result);
	}
}
