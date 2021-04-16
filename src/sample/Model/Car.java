package sample.Model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Controller.Controller;

public class Car {

    /*** Variables  ***/
    Rectangle rect = new Rectangle();
    Rectangle id;
    String nombreImage;

    /*** Constructor ***/

    public Car (Rectangle id , String nombre){
        this.nombreImage = nombre;
        this.id = id;
    }


    /*** Methods to paint***/
    public void pintar(GraphicsContext graficos){
        graficos.drawImage(Controller.imagenes.get(nombreImage), 180 , 150);
        id.setStroke(Color.BLACK);
        id.setFill(Color.RED);
    }

    /*** Methods to unpaint ***/
    public void despintar(GraphicsContext graficos){
        graficos.drawImage(Controller.imagenes.get(nombreImage), 180 , 150);
    }



}
