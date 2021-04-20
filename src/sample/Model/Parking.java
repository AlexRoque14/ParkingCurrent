package sample.Model;

import javafx.scene.image.Image;
import sample.Controller.Controller;

import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Parking extends Observable implements  Runnable {

    /*** Initialize variables ***/
    public static int[] espacios_estacionamiento;
    public static int espacios_disponibles = 20 ;
    public static boolean status = true;

    public static boolean enter = false;
    public static boolean exit = false;
    public static boolean estacionados = false;
    public static boolean verbose = true;
    private char id;

    /*** Initialize Semaphores ***/
    Semaphore entrada_parking = new Semaphore(1);
    Semaphore salida_parking = new Semaphore(0);
    Semaphore door = new Semaphore(1);

    /*** Constructors ***/
    public Parking(){ }

    public Parking(char id , Observer objeto) {
        this.id = id;
        addObserver(objeto);
    }

    /*** Initialize methods ***/
    public void initializeParking(){
        System.out.println("Inicializando estacionamiento");
        this.espacios_estacionamiento = new int[espacios_disponibles];
        for (int i = 0; i < espacios_disponibles; i++) {
            espacios_estacionamiento[i] = 0;           //0 = libre , 1 = ocupado
        }
    }

    public void viewEspaces(){
        System.out.println("Espacios disponibles");
        for (int i = 0; i < 20; i++) {
            System.out.println("Espacio "+ i + ": " + this.espacios_estacionamiento[i]);
        }
    }

    /*** Method run (Observable) ***/
    @Override
    public void run() {
            switch (id){
                case '1':
                    try{
                        if(exit){
                            System.out.println("\nEsperando auto que esta saliendo...");
                            enter = false;
                        }else{
                            entrada_parking.acquire();          //libera la entrada
                            if(espacios_disponibles == 0 || espacios_disponibles < 15){
                                System.out.println("No hay espacios disponibles.");
                                enter = false;
                                exit = true;
                            }else{
                                int pos = 0;
                                enter = true;
                                verbose = true;
                                this.setChanged();
                                this.notifyObservers("GO");
                                do{
                                    pos = (int)(Math.random()*19 + 1);              //Posicion aleatoria entre 1 y 20
                                    if(espacios_estacionamiento[pos] == 0){
                                        System.out.println("\n *** Auto entrando al estacionamiento ... ***");
                                        System.out.println("Estacionando auto en el espacio: " + pos);
                                        espacios_estacionamiento[pos] = 1;
                                        espacios_disponibles -= 1;
                                        estacionados = true;
                                        verbose = false;
                                        System.out.println("Espacios: " + espacios_disponibles);
                                        entrada_parking.release();  //bloquea la entrada
                                        this.setChanged();
                                        this.notifyObservers(String.valueOf(pos));
                                    }
                                }while(verbose);
                                enter = false;
                            }
                        }

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    break;
                case '2':
                    try {
                        if(enter){
                            System.out.println("\n\tEsperando autos que estan entrando...");
                            exit = false;
                        }else{
                            if(estacionados && espacios_disponibles < 20){
                                salida_parking.release();       //libera la salida
                                exit = true;
                                verbose = true;
                                int pos = 0;
                                this.setChanged();
                                this.notifyObservers("STOP");
                                do{
                                    pos = (int)(Math.random()*19 + 1);
                                    if(espacios_estacionamiento[pos] == 1){
                                        System.out.println("\n\t ***  Auto saliendo del estacionamiento... ***");
                                        System.out.println("\t El auto del lugar " + pos + " ha salido.");
                                        espacios_estacionamiento[pos] = 0;
                                        espacios_disponibles += 1;
                                        System.out.println("\t Espacios: " + espacios_disponibles);
                                        salida_parking.acquire();            //bloquea la salida
                                        verbose = false;
                                        int aux = pos + 50;                  //Modifico la posicion para que vaya al default
                                        this.setChanged();
                                        this.notifyObservers(String.valueOf(aux));
                                    }
                                }while(verbose);
                                exit = false;
                            }
                            exit = false;
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
            /*** Thread sleep for pauses ***/
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(6000) + 200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
    }
}

