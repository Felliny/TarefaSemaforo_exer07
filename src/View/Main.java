package View;

import Controller.CalculosBanco;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaforo= new Semaphore(1);
        int id= 1;


        for (int i=1; i<=21; i++){
            Thread processo= new CalculosBanco(semaforo, id);
            processo.start();
            id++;
        }
    }
}
