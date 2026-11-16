package tasks;

import model.CintaTransportadora;

public class AGVTask implements Runnable {

    private int id;
    private int comptador;
    private CintaTransportadora cinta;

    public AGVTask(int id, int comptador, CintaTransportadora cinta) {
        this.id = id;
        this.comptador = comptador;
        this.cinta = cinta;
    } 

    @Override
    public void run() {

        try {

            while(!Thread.currentThread().isInterrupted()) {

                cinta.afegirBateria(id, comptador);
                comptador++;
                Thread.sleep(500);

            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
