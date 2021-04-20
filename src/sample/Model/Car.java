package sample.Model;

import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import sample.Controller.Controller;
import sample.Main;

public class Car {

    /*** Variables  ***/
    private  int x;
    private int y;
    private int y_mov;
    private String nombreImage;
    private String name;

    /*** Variables JavaFX ***/
    ImageView imgC1;
    ImageView imgC2;
    Circle c;

    /*** Constructor ***/
    public Car ( String nombre, int x, int y){
        this.nombreImage = nombre;
        this.x = x;
        this.y= y;
    }


    /*** Methods to paint ***/
    public void pintar(GraphicsContext graficos){
        graficos.drawImage(Controller.imagenes.get(nombreImage),x,y);
    }

    /*** Methods to unpaint ***/
    public void  despintar(GraphicsContext graficos){
        graficos.clearRect(x,y,Controller.imagenes.get(nombreImage).getWidth(),Controller.imagenes.get(nombreImage).getHeight());
    }


    /*** Move cars to exit ***/
    public void mover(GraphicsContext graficos){
        graficos.clearRect(x,y,Controller.imagenes.get(nombreImage).getWidth(),Controller.imagenes.get(nombreImage).getHeight());
        Parking.exit = true;
        if(nombreImage.equals("car1")){
            imgC1 = new ImageView("images/ce.jpeg");
        }else{
            imgC1 = new ImageView("images/ce2.jpeg");
        }

        TranslateTransition tt = new TranslateTransition();

        if(Parking.enter){
            imgC1.setVisible(false);
            tt.pause();
        }else{
            imgC1.setX(590);
            imgC1.setY(411);
            Main.root.getChildren().add(imgC1);
            tt.setDuration(Duration.seconds(1.5));
            tt.setNode(imgC1);
            tt.setToY(-450);
            tt.play();

            tt.setOnFinished((e) -> {
                imgC1.setVisible(false);
                Parking.exit = false;
            });
        }


    }

    /*** Move cars to enter parking and paint position ***/
    public void moverEntrada(GraphicsContext graficos){

        Parking.enter = true;
        if(nombreImage.equals("car1")){
            imgC2 = new ImageView("images/ARE.jpeg");
        }else{
            imgC2 = new ImageView("images/AZE.jpeg");
        }

        TranslateTransition tt = new TranslateTransition();

        if(Parking.exit){
            tt.pause();
            imgC2.setVisible(false);
        }else{
            imgC2.setX(37);
            imgC2.setY(36);
            Main.root.getChildren().add(imgC2);
            tt.setDuration(Duration.seconds(1));
            tt.setNode(imgC2);
            tt.setToX(400);
            tt.play();

            tt.setOnFinished((e) -> {
                imgC2.setVisible(false);
                graficos.drawImage(Controller.imagenes.get(nombreImage),x,y);
                Parking.enter = false;
            });
        }


    }
}


