package de.dhbw.parprog;

class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();
        Account a = bank.createAccount();
        Account b = bank.createAccount();

        Thread t1 = new Thread(new TransferRunner(bank, a, b));

        Thread t2 = new Thread(new TransferRunner(bank, b, a));

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t2.start();
    }
}