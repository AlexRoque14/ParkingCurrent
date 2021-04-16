package sample.Model;

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
    private String noti;

    /*** Instance classes ***/
    Controller cont ;

    /*** Initialize Semaphores ***/
    Semaphore entrada_parking = new Semaphore(0);
    Semaphore salida_parking = new Semaphore(0);


    /*** Constructors ***/
    public Parking(){

    }


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
        while(status){
            switch (id){
                case '1':
                    try{
                        if(exit){
                            System.out.println("\nEsperando auto que esta saliendo.");
                            this.setChanged();
                            this.notifyObservers("STOP");
                            enter = false;
                        }else{
                            entrada_parking.release();          //libera la entrada
                            if(espacios_disponibles == 0){
                                System.out.println("No hay espacios disponibles.");
                            }else{
                                int pos = 0;
                                enter = true;
                                verbose = true;
                                this.setChanged();
                                this.notifyObservers("GO");
                                do{
                                    System.out.println("\n *** Auto entrando al estacionamiento ... ***");
                                    pos = (int)(Math.random()*19 + 1);              //Posicion aleatoria entre 1 y 20
                                    if(espacios_estacionamiento[pos] == 0){
                                        System.out.println("Estacionando auto en el espacio: " + pos);
                                        espacios_estacionamiento[pos] = 1;
                                        espacios_disponibles -= 1;
                                        estacionados = true;
                                        verbose = false;
                                        System.out.println("Espacios: " + espacios_disponibles);
                                        entrada_parking.acquire();  //bloquea la entrada
                                        noti = String.valueOf(pos);
                                        this.setChanged();
                                        this.notifyObservers(noti);
                                        break;
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
                            this.setChanged();
                            this.notifyObservers("GO");
                            exit = false;
                        }else{
                            salida_parking.release();       //libera la salida
                            exit = true;
                            verbose = true;
                            if(estacionados){
                                int pos = 0;
                                System.out.println("\n\t ***  Auto saliendo del estacionamiento... ***");
                                this.setChanged();
                                this.notifyObservers("STOP");
                                do{
                                    pos = (int)(Math.random()*19 + 1);
                                    if(espacios_estacionamiento[pos] == 1){
                                        System.out.println("\t El auto del lugar " + pos + " ha salido.");
                                        espacios_estacionamiento[pos] = 0;
                                        espacios_disponibles += 1;
                                        System.out.println("\t Espacios: " + espacios_disponibles);
                                        salida_parking.acquire();            //bloquea la salida
                                        exit = false;
                                        verbose = false;

                                        int aux = pos + 50;                  //Modifico la posicion para que vaya al default
                                        noti = String.valueOf(aux);
                                        this.setChanged();
                                        this.notifyObservers(noti);
                                    }
                                }while(verbose);
                            }else{
                                exit = false;
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }

            /*** Thread sleep for pauses ***/
            try {
                Thread.sleep(ThreadLocalRandom.current().nextLong(9000) + 200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

