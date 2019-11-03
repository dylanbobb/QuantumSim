/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcgillphys19;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
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
    private State state;
    boolean is_cnot_or_swap = false;
    private String current_op;
    private ArrayList<String> line1_ops = new ArrayList(3);
    private ArrayList<String> line2_ops = new ArrayList(3);
    private ArrayList<String> line3_ops = new ArrayList(3);
    private ArrayList<String> line4_ops = new ArrayList(3);
    
    

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
    private Rectangle line1Operation1;
    @FXML
    private Rectangle line1Operation2;
    @FXML
    private Rectangle line1Operation3;
    @FXML
    private Rectangle line2Operation1;
    @FXML
    private Rectangle line2Operation2;
    @FXML
    private Rectangle line2Operation3;
    @FXML
    private Rectangle line3Operation1;
    @FXML
    private Rectangle line3Operation2;
    @FXML
    private Rectangle line3Operation3;
    @FXML
    private Rectangle line4Operation1;
    @FXML
    private Rectangle line4Operation2;
    @FXML
    private Rectangle line4Operation3;
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
    private Button clear;

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
    public void handleClearAction(ActionEvent event){
        line1_ops = new ArrayList(3);
        line2_ops = new ArrayList(3);
        line3_ops = new ArrayList(3);
        line4_ops = new ArrayList(3);
        
        line1Operation1.setFill(Paint.valueOf("blue"));
        line1Operation1.setOpacity(0.1);
        line1Operation2.setFill(Paint.valueOf("blue"));
        line1Operation2.setOpacity(0.1);
        line1Operation3.setFill(Paint.valueOf("blue"));
        line1Operation3.setOpacity(0.1);
        
        line2Operation1.setFill(Paint.valueOf("blue"));
        line2Operation1.setOpacity(0.1);
        line2Operation2.setFill(Paint.valueOf("blue"));
        line2Operation2.setOpacity(0.1);
        line2Operation3.setFill(Paint.valueOf("blue"));
        line2Operation3.setOpacity(0.1);
        
        line3Operation1.setFill(Paint.valueOf("blue"));
        line3Operation1.setOpacity(0.1);
        line3Operation2.setFill(Paint.valueOf("blue"));
        line3Operation2.setOpacity(0.1);
        line3Operation3.setFill(Paint.valueOf("blue"));
        line3Operation3.setOpacity(0.1);
        
        line4Operation1.setFill(Paint.valueOf("blue"));
        line4Operation1.setOpacity(0.1);
        line4Operation2.setFill(Paint.valueOf("blue"));
        line4Operation2.setOpacity(0.1);
        line4Operation3.setFill(Paint.valueOf("blue"));
        line4Operation3.setOpacity(0.1);
    }
    
    @FXML
    private void handleRunAction(ActionEvent event) {
        // Calculate the size of the arrows for the circles
        String[] q1s = input1.getText().split(",");
        String[] q2s = input2.getText().split(",");
        String[] q3s = input3.getText().split(",");
        String[] q4s = input4.getText().split(",");
        double[] q1 = new double[q1s.length];
        double[] q2 = new double[q2s.length];
        double[] q3 = new double[q3s.length];
        double[] q4 = new double[q4s.length];
        for (int i = 0; i < q1s.length; i++) {
            q1[i] = Math.sqrt(Double.parseDouble(q1s[i]) / 100);
            q2[i] = Math.sqrt(Double.parseDouble(q2s[i]) / 100);
            q3[i] = Math.sqrt(Double.parseDouble(q3s[i]) / 100);
            q4[i] = Math.sqrt(Double.parseDouble(q4s[i]) / 100);
        }
        double[] stateVector = Matrix.tensorVectors(q1, q2);
        stateVector = Matrix.tensorVectors(stateVector, q3);
        stateVector = Matrix.tensorVectors(stateVector, q4);
        state = new State(stateVector);
        
        // Reading which funtion is which
        System.out.println(line1_ops);
        
        System.out.println(Arrays.toString(state.getState()));
        
    }

    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    static private String fileURL(String relativePath) {
        return new File(relativePath).toURI().toString();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Images
        Image hadamard_image = new Image(fileURL("./assets/hadamard_image.jpg"));
        Image swap_image = new Image(fileURL("./assets/swap_image.jpg"));
        Image cnot_image = new Image(fileURL("./assets/cnot_image.jpg"));
        Image measurer_image = new Image(fileURL("./assets/measurer_image.jpg"));

        // Shapes
        hadamard.setFill(new ImagePattern(hadamard_image));
        cnot.setFill(new ImagePattern(cnot_image));
        swap.setFill(new ImagePattern(swap_image));
        measurer.setFill(new ImagePattern(measurer_image));

        // Shape (operation) drag detected methods
        hadamard.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = hadamard.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(hadamard_image);

                db.setContent(content);
                current_op = "h";
                line4Operation1.setDisable(false);
                line4Operation2.setDisable(false);
                line4Operation3.setDisable(false);
                event.consume();
            }

        });

        cnot.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = cnot.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(cnot_image);

                db.setContent(content);
                line4Operation1.setDisable(true);
                line4Operation2.setDisable(true);
                line4Operation3.setDisable(true);
                current_op = "cnot";
                event.consume();
            }

        });

        swap.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = swap.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(swap_image);

                db.setContent(content);
                line4Operation1.setDisable(true);
                line4Operation2.setDisable(true);
                line4Operation3.setDisable(true);
                current_op = "swap";
                event.consume();
            }

        });

        measurer.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = measurer.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(measurer_image);

                db.setContent(content);
                current_op = "m";
                line4Operation1.setDisable(false);
                line4Operation2.setDisable(false);
                line4Operation3.setDisable(false);
                event.consume();
            }

        });

        // Target detection (drag over)
        line1Operation1.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line1Operation1 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line1Operation2.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line1Operation2 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line1Operation3.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line1Operation3 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line2Operation1.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line2Operation1 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line2Operation2.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line2Operation2 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line2Operation3.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line2Operation3 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line3Operation1.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line3Operation1 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line3Operation2.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line3Operation2 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line3Operation3.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line3Operation3 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line4Operation1.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line4Operation1 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line4Operation2.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line4Operation2 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        line4Operation3.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getGestureSource() != line1Operation1 && event.getDragboard().hasImage()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        // On drag entered
        // On drag dropped
        line1Operation1.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line1Operation1.setFill(new ImagePattern(db.getImage()));
                    line1Operation1.setOpacity(1);
                    
                    line1_ops.add(0,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line1Operation2.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line1Operation2.setFill(new ImagePattern(db.getImage()));
                    line1Operation2.setOpacity(1);
                    
                    line1_ops.add(1,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line1Operation3.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line1Operation3.setFill(new ImagePattern(db.getImage()));
                    line1Operation3.setOpacity(1);
                    
                    line1_ops.add(2,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line2Operation1.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line2Operation1.setFill(new ImagePattern(db.getImage()));
                    line2Operation1.setOpacity(1);
                    
                    line2_ops.add(0,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line2Operation2.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line2Operation2.setFill(new ImagePattern(db.getImage()));
                    line2Operation2.setOpacity(1);
                    
                    line2_ops.add(1,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line2Operation3.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line2Operation3.setFill(new ImagePattern(db.getImage()));
                    line2Operation3.setOpacity(1);
                    
                    line2_ops.add(2,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line3Operation1.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line3Operation1.setFill(new ImagePattern(db.getImage()));
                    line3Operation1.setOpacity(1);
                    
                    line3_ops.add(0,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line3Operation2.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line3Operation2.setFill(new ImagePattern(db.getImage()));
                    line3Operation2.setOpacity(1);
                   
                    line3_ops.add(1,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line3Operation3.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    line3Operation3.setFill(new ImagePattern(db.getImage()));
                    line3Operation3.setOpacity(1);
                    
                    line3_ops.add(2,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line4Operation1.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage() && !db.getImage().equals(swap_image) && !db.getImage().equals(cnot_image)) {
                    line4Operation1.setFill(new ImagePattern(db.getImage()));
                    line4Operation1.setOpacity(1);
                    
                    line4_ops.add(0,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line4Operation2.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage() && !db.getImage().equals(swap_image) && !db.getImage().equals(cnot_image)) {
                    line4Operation2.setFill(new ImagePattern(db.getImage()));
                    line4Operation2.setOpacity(1);
                   
                    line4_ops.add(1,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        line4Operation3.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                Dragboard db = event.getDragboard();
                boolean success = false;
                if (is_cnot_or_swap) {
                    line4Operation3.setDisable(true);
                } 
                else if (db.hasImage()) {
                    line4Operation3.setFill(new ImagePattern(db.getImage()));
                    line4Operation3.setOpacity(1);
                    
                    line4_ops.add(2,current_op);
                    success = true;
                }

                event.setDropCompleted(success);

                event.consume();
            }
        });

        // Source Drag Done
        hadamard.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getTransferMode() == TransferMode.MOVE) {

                }
                event.consume();
            }
        });

        cnot.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getTransferMode() == TransferMode.MOVE) {
                }
                event.consume();
            }
        });

        swap.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getTransferMode() == TransferMode.MOVE) {
                }
                event.consume();
            }
        });

        measurer.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                if (event.getTransferMode() == TransferMode.MOVE) {

                }
                event.consume();
            }
        });

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
    }
}
