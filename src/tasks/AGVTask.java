package tasks;

import model.CintaTransportadora;

public class AGVTask implements Runnable {

    private int id;
    private int comptador;
    private CintaTransportadora cinta;

    @Override
    public void run() {

//        try {
//            while(!Thread.currentThread().isInterrupted()) {
//                cinta.afegirBateria(id, comptador);
//                comptador++;
//                Thread.sleep(500);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch {
//            Thread.currentThread().interrupt();
//        }

    }

}
