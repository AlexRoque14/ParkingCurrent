package sample;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Semaphore;

public class Parking extends Observable implements  Runnable {

    int[] espacios_estacionamiento;
    int espacios_disponibles = 20 ;
    public static boolean status = true;
    private char id;

    Semaphore mutex = new Semaphore(1);

    public Parking() {

    }

    public Parking(char id, Observer obj) {
        this.id = id;
        addObserver(obj);
    }


    public void initializeParking(){
        System.out.println("Inicializando estacionamiento");
        this.espacios_estacionamiento = new int[espacios_disponibles];
        for (int i = 0; i < espacios_disponibles; i++) {
            espacios_estacionamiento[i] = 0;           //0 = libre , 1 = ocupado
        }
    }

    @Override
    public void run() {
        while(status){
            switch (this.id){//El auto quiera entrar al parking
                case '1':
                    try {
                        mutex.acquire();
                        if(espacios_disponibles == 0){
                            System.out.println("No hay espacios disponibles.");
                            status = false;
                        }else{//si hay espacios
                            System.out.println(this.espacios_estacionamiento[1]);
                        }
                        mutex.release();
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
