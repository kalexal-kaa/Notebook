/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notebook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author kaa
 */
public class NoteBook extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLNoteBook.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setTitle(new Language().nameApplication());
        stage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("icon/icon.png")));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((WindowEvent we) -> {
            we.consume();
            FXMLNoteBookController controller=new FXMLNoteBookController();
            controller.exit();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
