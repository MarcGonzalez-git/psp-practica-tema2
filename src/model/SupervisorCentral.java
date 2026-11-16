package model;

import java.util.concurrent.Semaphore;

public class SupervisorCentral {

    boolean superescalfament;

    Semaphore mutex = new Semaphore(1);

    public synchronized void pausaActivitat() throws InterruptedException {
        while(superescalfament) {
            wait();
        }
    }

}
