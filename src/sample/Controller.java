package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    Parking park = new Parking();


    public void initializePark(){
        park.initializeParking();

        /***launch Threads ***/
        new Thread(new Parking('1', this)).start();
        new Thread(new Parking('1', this)).start();
        new Thread(new Parking('1', this)).start();
    }


    @FXML
    void initializeComponents(MouseEvent event) {
        initializePark();
    }

    @Override
    public void update(Observable o, Object arg) {
        switch ((String) arg){
            case "1":
                System.out.println("Auto estacionandose");
        }
    }
}
