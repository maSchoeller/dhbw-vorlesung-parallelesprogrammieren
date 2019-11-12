package de.dhbw.parprog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class MapReduce {

    private static CompletableFuture<List<Person>> allePersonen;
    /**
     * Wandelt eine Liste von Futures in das Future einer Liste aller Ergebnisse um
     *
     * @param futures
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        CompletableFuture<Void> allDoneFuture = CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[futures.size()]));
        return allDoneFuture
                .thenApply(v -> futures.stream().map(future -> future.join()).collect(Collectors.<T>toList()));
    }

    public static CompletableFuture<CalcResult> doAnalysis() {

        CompletableFuture<Double> averageAge = allePersonen.thenApply((list) -> {
            return list.stream().mapToInt(p -> p.getAge()).average().getAsDouble();
        });

        CompletableFuture<Long> maleNames = allePersonen.thenApply((list) -> {
            return list.stream().filter(p -> (boolean)p.isMale()).count();
        });

        CompletableFuture<Integer> maxNameLength = allePersonen.thenApply((list) -> {
            return list.stream()
                        .mapToInt(p -> {
                            return p.getNachname().length();
                        })
                        .max()
                        .getAsInt();
        });

        CompletableFuture<CalcResult> averageAgeAndMales = averageAge.thenCombine(maleNames, (average, males) -> {
            return new CalcResult(average, 0, males);
        });
        CompletableFuture<CalcResult> result = averageAgeAndMales.thenCombine(maxNameLength, (ageMale, max) -> {
            return new CalcResult(ageMale.avgAge, max, ageMale.maleCount);
        });
        return result;

        
    }

    public static void main(String[] args) {
        List<CompletableFuture<Person>> personenCompFuture = new ArrayList<>();
        for (int i = 0; i < PersonArchive.getPersonenListSize(); i++) {
            CompletableFuture<Person> person = CompletableFuture.supplyAsync(() -> PersonArchive.getPerson());
            personenCompFuture.add(person);
        }

        allePersonen = sequence(personenCompFuture);
        
        List<Person> listeDerPersonen = new ArrayList<>();
        try {
            listeDerPersonen = allePersonen.get();
            for (Person person : listeDerPersonen) {
                System.out.println(person.toString());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        
        try {
            CalcResult finalResult = MapReduce.doAnalysis().get();
            System.out.println(finalResult.toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    
    }
}