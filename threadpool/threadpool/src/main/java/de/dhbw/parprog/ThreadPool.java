package de.dhbw.parprog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class ThreadPool {
	public int doCalculation() {
		List<Future<Integer>> list = new ArrayList<>();
		ExecutorService service = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			list.add(service.submit(() -> {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					//Nothing todo
				}
				return 42;
			}));
		}

		return list.stream()
			.mapToInt(f -> 
			{
				try 
				{ 
					return f.get();
				} catch(Exception ex) {
					return 0;
				}
			})
			.sum();

	}

	public static void main(String[] args) {
		ThreadPool test = new ThreadPool();
		System.out.println("Calculation started");
		int result = test.doCalculation();
		System.out.println("Result: " + result);
	}
}
