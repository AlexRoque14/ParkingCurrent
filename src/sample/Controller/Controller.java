package sample.Controller;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sample.Model.Car;
import sample.Model.Parking;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Controller implements Observer {

    /*** Variables ***/
    public static HashMap<String, Image> imagenes;
    public static ObservableList<Car> data = FXCollections.observableArrayList();
    public static ArrayList<Car> listPosicion = new ArrayList<>(data);

    public static ObservableList<Car> data_exit = FXCollections.observableArrayList();
    public static ArrayList<Car> list_exit = new ArrayList<>(data_exit);


    /***Java Fx ***/
    private GraphicsContext graficos;
    public static AnimationTimer animation;
    private Timeline timer;

    /***Instances of classes ***/
    public Car car;
    public Parking park = new Parking();

    /***Instance JavaFX controls***/

    @FXML
    private ImageView img_go;

    @FXML
    private ImageView img_stop;

    @FXML
    public Canvas lienzo;

    @FXML
    private ImageView rct;


    /*** Btn to finalize***/
    @FXML
    void btnStop(MouseEvent event) {
    System.exit(0);
    }


    /*** Initialize the components ***/
    @FXML
    public void initializeComponents(MouseEvent event){
        initializeScene();
        uploadImages();
        initializePark();
    }

    /*** Initialize components to FX ***/
    public void initializeScene(){
        imagenes = new HashMap<String, Image>();
        graficos = lienzo.getGraphicsContext2D();
    }


    /*** Upload images ***/
    public void uploadImages(){
        imagenes.put("car1",   new Image("images/carr.jpeg"));
        imagenes.put("car2",   new Image("images/cars.jpeg"));
    }

    /*** Methods ***/
    public void initializePark(){
        /*** Create position of parking***/
        park.initializeParking();
        Random random = new Random(System.currentTimeMillis());

        /*** Launch threads ***/
        timer = new Timeline(
                new KeyFrame(Duration.millis(random.nextInt(400)+100),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                switch(random.nextInt(2)){
                                    case 0:
                                        new Thread(new Parking('1', Controller.this)).start();
                                        break;
                                    case 1:
                                        new Thread(new Parking('2', Controller.this)).start();
                                        break;
                                }
                            }
                        })
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();


        /*** Launch threads
        new Thread(new Parking('1', Controller.this)).start();
        new Thread(new Parking('2', Controller.this)).start();
         ***/
        /***Launch animation to paint ***/
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

    /*** Update the components  ***/
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
    public void pintar()  {
        Iterator iter = listPosicion.iterator();
        while(iter.hasNext()){
            car = (Car) iter.next();
            car.moverEntrada(graficos);
            iter.remove();
        }
    }


    /*** Unpaint the cars parked ***/
    public void despintar()  {
        Iterator iter = list_exit.iterator();
        while(iter.hasNext()){
            car = (Car) iter.next();
            //car.despintar(graficos);
            car.mover(graficos);
            iter.remove();
        }
    }


    /*** Method update (Observer) ***/
    @Override
    public void update(Observable o, Object arg) {
        switch ((String) arg){
            case "STOP":
                //setStopGo(1);
                break;
            case "GO":
                //setStopGo(0);
                break;
            case "1":
                car = new Car( "car1", 49, 288);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "2":
                car = new Car( "car2", 105, 288);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "3":
                car = new Car("car1", 150, 288);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "4":
                car = new Car( "car2", 201, 288);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "5":
                car = new Car( "car1", 49, 169);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "6":
                car = new Car( "car2", 100, 169);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "7":
                car = new Car( "car1", 150, 169);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "8":
                car = new Car( "car2", 200, 169);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "9":
                car = new Car( "car1", 48, 50);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "10":
                 car = new Car( "car2", 100, 50);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "11":
                car = new Car( "car1",289,300);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "12":
                car = new Car("car2",331,300);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "13":
                car = new Car( "car1",390,300);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "14":
                car = new Car( "car2",449,300);  //subi el eje y
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "15":
                car = new Car( "car1",295,165);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "16":
                car = new Car( "car2",348,165);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "17":
                car = new Car( "car1",395,165);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "18":
                car = new Car("car2",446,165);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "19":
                car = new Car ("car1",294,48);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            case "20":
                car = new Car( "car2",350,49);
                Platform.runLater(() -> listPosicion.add(car));
                //Platform.runLater(() -> car.moverEntrada(graficos));
                break;
            default:
                int x = Integer.parseInt((String) arg);
                int indice = x - 50;
                switch (String.valueOf(indice)){
                    case "1":
                        car = new Car("car1",49, 288);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "2":
                        car = new Car( "car2",105,288);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "3":
                        car = new Car( "car1",150,288);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "4":
                        car = new Car( "car2",201,288);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "5":
                        car = new Car( "car1",49,169);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "6":
                        car = new Car( "car2",100,169);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "7":
                        car = new Car( "car1",150,169);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "8":
                        car = new Car( "car2",200,169);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "9":
                        car = new Car( "car1",48,50);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "10":
                        car = new Car( "car2",100,50);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "11":
                        car = new Car( "car1",298,300);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "12":
                        car = new Car( "car2",331,300);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "13":
                        car = new Car( "car1",390,300);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "14":
                        car = new Car( "car2",449,300);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "15":
                        car = new Car( "car1",295,165);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "16":
                        car = new Car( "car2",398,165);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "17":
                        car = new Car( "car1",395,165);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "18":
                        car = new Car( "car2",446,165);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                    case "19":
                        car = new Car( "car1",294,49);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                    case "20":
                        car = new Car( "car2",350,49);
                        Platform.runLater(() -> list_exit.add(car));
                        //Platform.runLater(() -> car.mover(graficos));
                        break;
                }
        }

        /*** Thread sleep for pauses***/
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(6000) + 200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
