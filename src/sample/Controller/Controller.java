package sample.Controller;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import sample.Model.Car;
import sample.Model.Parking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadLocalRandom;

public class Controller implements Observer {

    /*** Variables ***/
    public static AnimationTimer animation;

    public static ObservableList<Car> data = FXCollections.observableArrayList();
    public static ArrayList<Car> listPosicion = new ArrayList<>(data);

    public static ObservableList<Car> data_exit = FXCollections.observableArrayList();
    public static ArrayList<Car> list_exit = new ArrayList<>(data_exit);


    /***Java Fx ***/
    private GraphicsContext graficos;
    private Rectangle rect;

    /***Instances of classes ***/
    public Car car;
    public Parking park = new Parking();

    /***Instance JavaFX controls***/
    @FXML
    private Button stop;

    @FXML
    private Rectangle E1;

    @FXML
    private Rectangle E2;

    @FXML
    private Rectangle E3;

    @FXML
    private Rectangle E4;

    @FXML
    private Rectangle E6;

    @FXML
    private Rectangle E7;

    @FXML
    private Rectangle E8;

    @FXML
    private Rectangle E9;

    @FXML
    private Rectangle E5;

    @FXML
    private Rectangle E10;

    @FXML
    private Rectangle E11;

    @FXML
    private Rectangle E12;

    @FXML
    private Rectangle E13;

    @FXML
    private Rectangle E14;

    @FXML
    private Rectangle E16;

    @FXML
    private Rectangle E17;

    @FXML
    private Rectangle E18;

    @FXML
    private Rectangle E19;

    @FXML
    private Rectangle E15;

    @FXML
    private Rectangle E20;

    @FXML
    void btnStop(MouseEvent event) {
    System.exit(0);
    }

    @FXML
    private ImageView img_go;

    @FXML
    private ImageView img_stop;


    /*** Methods ***/
    public void initializePark(){
        park.initializeParking();

        /***launch Threads ***/
        new Thread(new Parking('1', this)).start();
        new Thread(new Parking('2', this)).start();

        cicloJuego();
    }

    /*** Visualize the image 'stop' or 'go'. 1 if true , another number is false***/
    public void setStopGo(int  verb){
        if(verb == 1){
            img_stop.setVisible(true);
            img_go.setVisible(false);
        }else{
            img_go.setVisible(true);
            img_stop.setVisible(false);
        }
    }


    /*** Initialize the components ***/
    @FXML
    void initializeComponents(MouseEvent event) {
        initializePark();
    }

    /*** Update the components ***/
    public void cicloJuego(){
        animation = new AnimationTimer() {
            @Override
            public void handle(long actually_time) {
                pintar();
                despintar();
            }
        };
        animation.start();
    }

    /*** Paint the cars parked ***/
    public void pintar(){
        Iterator iter = listPosicion.iterator();
        while(iter.hasNext()){
            car = (Car) iter.next();
            car.pintar(rect);
            iter.remove();
        }
    }

    /*** Unpaint the cars parked ***/
    public void despintar(){
        Iterator iter = list_exit.iterator();
        while(iter.hasNext()){
            car = (Car) iter.next();
            car.despintar(rect);
            iter.remove();
        }
    }

    /*** Method update (Observer) ***/
    @Override
    public void update(Observable o, Object arg) {
        Car car;
        switch ((String) arg){
            case "STOP":
                setStopGo(1);
                break;
            case "GO":
                setStopGo(0);
                break;
            case "1":
                car = new Car(E1);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "2":
                car = new Car(E2);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "3":
                car = new Car(E3);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "4":
                car = new Car(E4);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "5":
                car = new Car(E5);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "6":
                car = new Car(E6);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "7":
                car = new Car(E7);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "8":
                car = new Car(E8);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "9":
                car = new Car(E9);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "10":
                car = new Car(E10);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "11":
                car = new Car(E11);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "12":
                car = new Car(E12);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "13":
                car = new Car(E13);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "14":
                car = new Car(E14);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "15":
                car = new Car(E15);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "16":
                car = new Car(E16);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "17":
                car = new Car(E17);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "18":
                car = new Car(E18);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "19":
                car = new Car(E19);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            case "20":
                car = new Car(E20);
                Platform.runLater(() -> listPosicion.add(car));
                break;
            default:
                int x = Integer.parseInt((String) arg);
                int indice = x - 50;
                String name = 'E' + String.valueOf(indice);
                switch (String.valueOf(indice)){
                    case "1":
                        car = new Car(E1);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "2":
                        car = new Car(E2);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "3":
                        car = new Car(E3);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "4":
                        car = new Car(E4);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "5":
                        car = new Car(E5);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "6":
                        car = new Car(E6);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "7":
                        car = new Car(E7);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "8":
                        car = new Car(E8);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "9":
                        car = new Car(E9);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "10":
                        car = new Car(E10);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "11":
                        car = new Car(E11);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "12":
                        car = new Car(E12);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "13":
                        car = new Car(E13);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "14":
                        car = new Car(E14);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "15":
                        car = new Car(E15);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "16":
                        car = new Car(E16);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "17":
                        car = new Car(E17);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "18":
                        car = new Car(E18);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "19":
                        car = new Car(E19);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                    case "20":
                        car = new Car(E20);
                        Platform.runLater(() -> list_exit.add(car));
                        break;
                }
        }

        /*** Thread sleep for pauses***/
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(9000) + 200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
