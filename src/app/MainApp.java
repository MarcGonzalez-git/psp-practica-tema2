package app;

import model.CentralSoldadura;
import model.CintaTransportadora;
import model.SupervisorCentral;
import tasks.AGVTask;
import tasks.RobotTask;
import view.DashboardView;

public class MainApp {

    public static void main(String[] args) {

        // Instanciam SC: Supervisor, Cinta, Soldadura
        SupervisorCentral supervisor = new SupervisorCentral();
        CintaTransportadora cinta = new CintaTransportadora();
        CentralSoldadura soldadura = new CentralSoldadura();

        // Cream els fils dels AGV i Robots
        Thread[] agvs = new Thread[2];
        Thread[] robots = new Thread[5];

        for (int i = 0; i < agvs.length; i++) {
            agvs[i] = new Thread(new AGVTask(i + 1, 0, cinta));
        }

        for (int i = 0; i < robots.length; i++) {
            robots[i] = new Thread(new RobotTask(i + 1, 100, supervisor, cinta, soldadura));
        }

        // Instanciam Dashboard
        Thread filDashboard = new Thread(new DashboardView(soldadura, cinta, supervisor, robots));
        filDashboard.setDaemon(true);

        // Iniciam amb For AGV i Robots
        for (Thread agv : agvs) {
            agv.start();
        }

        for (Thread robot : robots) {
            robot.start();
        }

        filDashboard.start();

        try {

            Thread.sleep(3000);

            supervisor.activaAlerta();

            Thread.sleep(2000);

            supervisor.desactivaAlerta();

            Thread.sleep(3000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {

            // Aturam tots els fils
            for (Thread agv : agvs) {
                agv.interrupt();
            }

            for (Thread robot : robots) {
                robot.interrupt();
            }

            filDashboard.interrupt();

        }

        // Esperam que acabin tots els fils
        try {

            for (Thread agv : agvs) {
                agv.join();
            }

            for (Thread robot : robots) {
                robot.join();
            }

            filDashboard.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}