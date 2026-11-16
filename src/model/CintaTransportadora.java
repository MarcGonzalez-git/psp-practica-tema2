package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CintaTransportadora {

    private ReentrantLock lock = new ReentrantLock();
    private Condition cintaNoPlena = lock.newCondition();
    private Condition cintaNoBuida = lock.newCondition();

    public void gaurdarDada() throws InterruptedException {
        lock.lock();

        try {

            cintaNoPlena.await();

            cintaNoBuida.signal();

        } finally {

            lock.unlock();

        }

    }

}
