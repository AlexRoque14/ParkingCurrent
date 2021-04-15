package sample.Model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Car {

    /*** Variables  ***/
    Rectangle rect = new Rectangle();
    Rectangle id;

    /*** Constructor ***/
    public Car (Rectangle id){
        this.id = id;
    }

    /*** Setters and getters ***/
    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Rectangle getId() {
        return id;
    }

    public void setId(Rectangle id) {
        this.id = id;
    }


    /*** Methods to paint***/
    public void pintar(Rectangle rect){
        id.setStroke(Color.BLACK);
        id.setFill(Color.RED);
    }

    /*** Methods to unpaint ***/
    public void despintar(Rectangle rect){
        id.setStroke(Color.BLACK);
        id.setFill(Color.BLUE);
    }



}
