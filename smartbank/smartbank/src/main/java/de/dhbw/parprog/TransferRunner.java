package de.dhbw.parprog;
public class TransferRunner implements Runnable {

    private Bank bank;
    private Account a, b;

    public TransferRunner(Bank bank, Account a, Account b) {
        this.a = a;
        this.b = b;
        this.bank = bank;
    }

    @Override
    public void run() {
        System.out.println("Account A: " + bank.getBalance(a) + " \nAccount B. " + bank.getBalance(b));
        bank.transfer(a, b, 200);
        System.out.println("Account A: " + bank.getBalance(a) + " \nAccount B. " + bank.getBalance(b));
    }
}