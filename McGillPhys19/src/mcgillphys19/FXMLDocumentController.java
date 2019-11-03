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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Dylan
 */
public class FXMLDocumentController implements Initializable {

    // Class Variables
    private Random rand = new Random();

    // Shapes
    @FXML
    private Line line1;
    @FXML
    private Line line2;
    @FXML
    private Line line3;
    @FXML
    private Line line4;
    @FXML
    private Circle qbit1;
    @FXML
    private Circle qbit2;
    @FXML
    private Circle qbit3;
    @FXML
    private Circle qbit4;

    // FXML inputs
    @FXML
    private Box line1Operation1;
    @FXML
    private Box line1Operation2;
    @FXML
    private Box line1Operation3;
    @FXML
    private Box line1Operation4;
    @FXML
    private Box line2Operation1;
    @FXML
    private Box line2Operation2;
    @FXML
    private Box line2Operation3;
    @FXML
    private Box line2Operation4;
    @FXML
    private Box line3Operation1;
    @FXML
    private Box line3Operation2;
    @FXML
    private Box line3Operation3;
    @FXML
    private Box line3Operation4;
    @FXML
    private Box line4Operation1;
    @FXML
    private Box line4Operation2;
    @FXML
    private Box line4Operation3;
    @FXML
    private Box line4Operation4;
    @FXML
    private Label quBit1InputLabel;
    @FXML
    private Label quBit2InputLabel;
    @FXML
    private Label quBit3InputLabel;
    @FXML
    private Label quBit4InputLabel;
    @FXML
    private Label quBit1CircuitLabel;
    @FXML
    private Label quBit2CircuitLabel;
    @FXML
    private Label quBit3CircuitLabel;
    @FXML
    private Label quBit4CircuitLabel;
    @FXML
    private Label inputMessage;
    @FXML
    private Label circuitMessage;
    @FXML
    private Label outputMessage;
    @FXML
    private Label operationsLabel;
    @FXML
    private Label stateVectorMessage;

    @FXML
    private AnchorPane pane;
    @FXML
    private TextField input1;
    @FXML
    private TextField input2;
    @FXML
    private TextField input3;
    @FXML
    private TextField input4;
    @FXML
    private Button run;

    @FXML
    private Rectangle hadamard;
    @FXML
    private Rectangle measurer;
    @FXML
    private Rectangle swap;
    @FXML
    private Rectangle cnot;

    // Outputs
    @FXML
    private Group line1Area;
//    private Group line2Area;
//    private Group line3Area;
//    private Group line4Area;

    @FXML
    private void handleRunAction(ActionEvent event) {
        // Calculate the size of the arrows for the circles
        
    }


    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Shapes
        
        
        // Input methods
        input1.setOnKeyPressed((KeyEvent event) -> {
            if (input1.getText().length() > 0) {
                // Read the value entered
            }
        });
        
        input2.setOnKeyPressed((KeyEvent event) -> {
            if (input2.getText().length() > 0) {
                // Read the value entered
            }
        });
        
        input3.setOnKeyPressed((KeyEvent event) -> {
            if (input3.getText().length() > 0) {
                // Read the value entered
            }
        });
        
        input4.setOnKeyPressed((KeyEvent event) -> {
            if (input4.getText().length() > 0) {
                // Read the value entered
            }
        });

        double[] s1s = {0, 0, 1, 0};
        State s1 = new State(s1s);
        //s1.swap();
        System.out.println(Arrays.toString(s1.getState()));
    }
}
