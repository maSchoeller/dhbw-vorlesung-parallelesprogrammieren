package de.dhbw.parprog;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {
    public static long LOWER_LIMIT = 0;
    public static long UPPER_LIMIT = 100000;

    ReentrantReadWriteLock lock;
    private long balance = 0;

    public Account() {
        lock = new ReentrantReadWriteLock();
    }

    // TODO: Mit eigener Implementierung füllen


    public  void update(long value) throws IllegalAccountStateException {

        Lock localLock = lock.readLock();
        localLock.lock();
        try{
            if ((balance + value) < LOWER_LIMIT || balance + value > UPPER_LIMIT) {
                throw new IllegalAccountStateException();
            }
        }
        finally {
            localLock.unlock();
        }


        Lock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            balance += value;
        }
        finally {
            writeLock.unlock();
        }
    }


    public long getBalance() {
        Lock localLock = lock.readLock();
        localLock.lock();
        try{
            return  this.balance;
        }
        finally {
             localLock.unlock();
        }
    }

}
