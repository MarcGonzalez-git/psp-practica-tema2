package view;

import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;

public class DashboardView implements Runnable {

    private CentralSoldadura soldadura;
    private CintaTransportadora cinta;
    private SupervisorCentral supervisor;
    private Thread[] robots;

    public DashboardView(CentralSoldadura soldadura, CintaTransportadora cinta, SupervisorCentral supervisor, Thread[] robots) {
        this.soldadura = soldadura;
        this.cinta = cinta;
        this.supervisor = supervisor;
        this.robots = robots;
    }

    @Override
    public void run() {

        try {

            while (!Thread.currentThread().isInterrupted()) {

                IO.println("\n---------- DASHBOARD ----------");

                IO.println("Estacions ocupades: " + soldadura.getEstacionsOcupades());
                IO.println("Bateries disponibles: " + cinta.getBateries() + " de " + cinta.getCapacitat());

                if (supervisor.isSuperescalfada()) {
                    IO.println("Estat: ALERTA");
                } else {
                    IO.println("Estat: NORMAL");
                }

                for (int i = 0; i < robots.length; i++) {
                    IO.println("Robot " + (i + 1) + ": " + robots[i].getState());
                }

                IO.println("-------------------------------\n");

                Thread.sleep(2000);

            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}