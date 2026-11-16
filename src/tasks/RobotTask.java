package tasks;

import model.SupervisorCentral;

public class RobotTask implements Runnable {

    private int id;
    private int bateria;

    public RobotTask(int id, int bateria) {
        this.id = id;
        this.bateria = bateria;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    @Override
    public void run() {

//        while(!Thread.currentThread().isInterrupted()) {
//            try {
//                supervisor.verificaEstat(id);
//                String buit = cinta.extreuBat(id);
//                int num = soldadura.agafa(id);
//                Sleep(random);
//                soldadura.allibera(id, num);
//                soldadura.registreCentral(id);
//            } catch {
//                Thread.currentThread().interrupt();
//            }
//
//        }

    }

    // sync verificaEstat() {
    //     if(alertaActiva) {
    //          wait();
    // activa i desactivaAlerta des del main

}
