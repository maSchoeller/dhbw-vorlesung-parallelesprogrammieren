package de.dhbw.parprog;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class Bank {
    ReadWriteLock sperre = new ReentrantReadWriteLock();
    
    /**
     * Erzeugt ein neues Konto mit initialem Kontostand 0.
     * @return das neue Konto
     */
	public synchronized Account createAccount() {
        return new Account(0);
    }

    /**
     * Ruft den aktuellen Kontostand ab
     * @param account Konto, dessen Kontostand bestimmt werden soll
     * @return der aktuelle Kontostand
     * @throws IllegalArgumentException bei ungültigen Parametern
     */
	public long getBalance(Account account) throws IllegalArgumentException {
        this.sperre.readLock().lock();
        try {
            return account.getAmount();
        } finally {
            this.sperre.readLock().unlock();
        }
    }

    /**
     * Einzahlen eines bestimmten Betrags
     * @param account das Konto, auf den der Betrag eingezahlt werden soll
     * @param amount der Betrag (muß >=0 sein)
     * @throws IllegalArgumentException bei ungültigen Parametern
     * @throws IllegalAccountStateException falls der Kontostand außerhalb des gültigen Wertebereichs geraten würde
     */
	public void deposit(Account account, long amount) throws IllegalAccountStateException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        long actAmount;

        this.sperre.readLock().lock();
        try {
            actAmount = getBalance(account);
        } finally {
            this.sperre.readLock().unlock();
        }

        this.sperre.writeLock().lock();
        try {
            if ((actAmount + amount) <= Account.UPPER_LIMIT) {
                account.verbuchen(amount);
            }else{
                throw new IllegalAccountStateException();
            }
        } finally {
            this.sperre.writeLock().unlock();
        }
    }

    /**
     * Abheben eines bestimmten Betrags
     * @param account das Konto, von dem der Betrag abgehoben werden soll
     * @param amount der Betrag (muß >=0 sein)
     * @throws IllegalArgumentException bei ungültigen Parametern
     * @throws IllegalAccountStateException falls der Kontostand außerhalb des gültigen Wertebereichs geraten würde
     */
	public void withdraw(Account account, long amount) throws IllegalAccountStateException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        long actAmount;

        this.sperre.readLock().lock();
        try {
            actAmount = getBalance(account);
        } finally {
            this.sperre.readLock().unlock();
        }

        this.sperre.writeLock().lock();
        try {
            if ((actAmount - amount) >= Account.LOWER_LIMIT) {
                account.abbuchen(amount);
            }else{
                throw new IllegalAccountStateException();
            }
        } finally {
            this.sperre.writeLock().unlock();
        }
    }

    /**
     * Überweisen eines Betrags von einem Konto auf ein anderes
     * @param fromAccount Konto, von dem abgebucht werden soll
     * @param toAccount Konto, auf das gutgeschrieben werden soll
     * @param amount der zu transferierende Betrag (muß >=0 sein)
     * @throws IllegalArgumentException bei ungültigen Parametern
     * @throws IllegalAccountStateException falls einer der Kontostände außerhalb des gültigen Wertebereichs geraten würde
     */
	public void transfer(Account fromAccount, Account toAccount, long amount) throws IllegalAccountStateException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        long actFromAmount, actToAmount;

        this.sperre.readLock().lock();
        try {
            actFromAmount = getBalance(fromAccount);
            actToAmount = getBalance(toAccount);
        } finally {
            this.sperre.readLock().unlock();
        }

        this.sperre.writeLock().lock();
        try {
            if (((actFromAmount - amount) >= Account.LOWER_LIMIT) && ((actToAmount + amount) <= Account.UPPER_LIMIT)) {
                fromAccount.abbuchen(amount);
                toAccount.verbuchen(amount);
            }else{
                throw new IllegalAccountStateException();
            }
        } finally {
            this.sperre.writeLock().unlock();
        }
    }
}
