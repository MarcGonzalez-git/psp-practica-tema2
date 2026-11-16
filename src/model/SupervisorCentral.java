package model;

public class SupervisorCentral {

    boolean superescalfament = false;

    public synchronized void verificaEstat(int id) throws InterruptedException {

        while(superescalfament) {

            IO.println("Superescalfament en procés");

            wait();

        }
        
    }

    public synchronized void activaAlerta() {

        superescalfament = true;
        IO.println("Superescalfament activat");

    }

    public synchronized void desactivaAlerta() {

        superescalfament = false;
        IO.println("Superescalfament desactivat");

        notifyAll();

    }

    public synchronized boolean isSuperescalfada() {
        return superescalfament;
    }

}
