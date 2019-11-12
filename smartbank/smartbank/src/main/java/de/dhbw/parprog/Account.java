package de.dhbw.parprog;


public class Account {
    public static long LOWER_LIMIT = 0;
    public static long UPPER_LIMIT = 100000;
    private long amount;

    public Account(long amount){
        this.amount = amount;
    }
    public long getAmount(){
        return this.amount;
    }

    public void verbuchen(long addAmount){
        this.amount += addAmount;
    }
    public void abbuchen(long addAmount){
        this.amount -= addAmount;
    }
}
