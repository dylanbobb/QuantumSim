package mcgillphys19;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for quantum simulator
 * 
 * @author Drop Table Team
 */

// Test Comment
public class McGillPhys19 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/mcgillphys19/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/mcgillphys19/label.css").toExternalForm());
        stage.setTitle("QuBits");
        stage.minWidthProperty().bind(scene.widthProperty());
        stage.minHeightProperty().bind(scene.heightProperty());
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
