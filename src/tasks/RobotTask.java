package tasks;

import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;

public class RobotTask implements Runnable {

    private int id;
    private int bateria;
    private SupervisorCentral supervisor;
    private CintaTransportadora cinta;
    private CentralSoldadura soldadura;

    public RobotTask(int id, int bateria, SupervisorCentral supervisor, CintaTransportadora cinta, CentralSoldadura soldadura) {
        this.id = id;
        this.bateria = bateria;
        this.supervisor = supervisor;
        this.cinta = cinta;
        this.soldadura = soldadura;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    @Override
    public void run() {

        try {

            while (!Thread.currentThread().isInterrupted()) {

                supervisor.verificaEstat(id);
                cinta.eliminarBateria(id);
                supervisor.verificaEstat(id);
                int numEstacio = soldadura.assignarEstacio(id);

                try {

                    supervisor.verificaEstat(id);
                    IO.println("Robot " + id + " està soldant el cotxe");
                    Thread.sleep(1000);

                } finally {

                    soldadura.alliberarEstacio(id, numEstacio);

                }

                supervisor.verificaEstat(id);
                soldadura.registrarCertificat(id);
                IO.println("Robot " + id + " ha acabat un cotxe");
                Thread.sleep(500);

            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        }

    }

}
