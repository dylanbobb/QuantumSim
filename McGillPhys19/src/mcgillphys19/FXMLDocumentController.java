/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcgillphys19;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author Dylan
 */
public class FXMLDocumentController implements Initializable {

    Random rand = new Random();
    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double[] s1s = {1/Math.sqrt(2),0, 0,1/Math.sqrt(2)};
        State s1 = new State(s1s);
        s1.collapse(rand);
        System.out.println(Arrays.toString(s1.getState()));
    }
}