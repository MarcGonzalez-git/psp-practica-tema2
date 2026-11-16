package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CintaTransportadora {

    private ReentrantLock lock = new ReentrantLock();
    private Condition cintaNoPlena = lock.newCondition();
    private Condition cintaNoBuida = lock.newCondition();
    private int bateries = 0;
    private int capacitat = 5;

    public ReentrantLock getLock() {
        return lock;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void afegirBateria(int id, int numBateria) throws InterruptedException {

        lock.lock();

        try {

            while(bateries >= capacitat) {
                cintaNoPlena.await();
            }

            bateries++;
            IO.println("AGV " + id + " ha afegit una bateria, queden " + bateries + " bateries");
            cintaNoBuida.signal();

        } finally {

            lock.unlock();

        }

    }

    public void eliminarBateria(int id) throws InterruptedException {

        lock.lock();

        try {

            while(bateries == 0) {
                cintaNoBuida.await();
            }

            bateries--;
            IO.println("Robot " + id + " ha afegit una bateries, queden " + bateries + " bateries");
            cintaNoPlena.signal();

        } finally {

            lock.unlock();

        }

    }

    public int getBateries() {
        
        lock.lock();

        try {

            return bateries;

        } finally {

            lock.unlock();

        }
    }

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
