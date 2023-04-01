package Controller;

import java.util.concurrent.Semaphore;

public class CalculosBanco extends Thread {

    private int id;
    private Semaphore semaforo;
    private int tempob;


    public CalculosBanco(Semaphore semaforo, int id){
        this.semaforo= semaforo;
        this.id= id;
    }

    @Override
    public void run() {
        calculos();
    }

    private void calculos() {
        int tempo= 0;
        int j= 0;
        if (id % 3 == 1){
            j= 2;
        }
        else {
            j= 1;
        }

        for (int i=j; i<=3; i++){
            if (id % 3 == 1){
                tempo= (int) (Math.random()* 801)+ 200;
                tempob= 1000;
            }
            else if (id % 3 == 2){
                tempo= (int) (Math.random()* 1001)+ 500;
                tempob= 1500;
            }
            else if (id % 3 == 0){
                tempo= (int) (Math.random()* 1001)+ 1000;
                tempob= 1500;
            }
            try {
                System.out.println("A conta #"+ id +" está calculando.");
                sleep(tempo);
                semaforo.acquire();
                System.out.println("A conta #"+ id +" está realizando transações no banco de dados.");
                banco();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }finally {
                System.out.println("A conta #"+ id +" terminou a transação com o banco de dados.");
                semaforo.release();
            }
        }
        System.out.println("A conta #"+ id +" terminou o processo.");
    }

    private void banco() {
        try {
            sleep(tempob);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
