package model;

import java.util.concurrent.Semaphore;

public class CentralSoldadura {

    Semaphore semafor = new Semaphore(3);
    Semaphore mutex = new Semaphore(1);
    private boolean[] estacions = new boolean[3];

    public int assignarEstacio(int id) throws InterruptedException {

        semafor.acquire();

        synchronized (estacions) {

            for (int i = 0; i < estacions.length; i++) {

                if (!estacions[i]) {

                    estacions[i] = true;
                    IO.println("Robot " + id + " ha ocupat l'estació " + i);
                    return i;

                }

            }

        }

        semafor.release();
        throw new IllegalStateException("No hi ha estacions lliures");

    }

    public void alliberarEstacio(int id, int numEstacio) {

        synchronized (estacions) {

            estacions[numEstacio] = false;
            IO.println("Robot " + id + " ha alliberat l'estació " + numEstacio);

        }

        semafor.release();

    }

    public void registrarCertificat(int id) throws InterruptedException {

        mutex.acquire();

        try {

            IO.println("Robot " + id + " ha registrat el certificat de qualitat");
            Thread.sleep(500);

        } finally {

            mutex.release();

        }

    }

    public int getEstacionsOcupades() {
        synchronized (estacions) {
            int ocupades = 0;

            for (boolean estacio : estacions) {
                if (estacio) {
                    ocupades++;
                }
            }

            return ocupades;
        }
    }

}
