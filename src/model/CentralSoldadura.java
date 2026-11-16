package model;

import java.util.concurrent.Semaphore;

public class CentralSoldadura {

    Semaphore semafor = new Semaphore(3);
    Semaphore mutex = new Semaphore(1);

    public void assignarEstacio(int id) throws InterruptedException {

        semafor.acquire();

    }

}
