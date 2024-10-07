package finleycrowther.au.physicssim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;
import java.util.Objects;

public class PhysicsSim extends Application {
    public static Scene mainScene;

    @Override
    public void start(Stage stage) throws Exception {
        mainScene = new Scene(FXMLLoader.load(Objects.requireNonNull(PhysicsSim.class.getResource("Main.fxml"))));
        stage.setScene(mainScene);
        stage.setTitle("Physics Simulation");

        // Forced aspect ratio
        // Bind the width and height properties to maintain a 16:9 aspect ratio
        stage.minWidthProperty().bind(mainScene.heightProperty().multiply(16.0 / 9.0));
        stage.minHeightProperty().bind(mainScene.widthProperty().multiply(9.0 / 16.0));

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
