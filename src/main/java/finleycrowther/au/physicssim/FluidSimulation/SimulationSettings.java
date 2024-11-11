package finleycrowther.au.physicssim.FluidSimulation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SimulationSettings extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox Content = new VBox(10);
        HBox SmoothingRadiusBox = new HBox(10);
        Text SmoothingRadius = new Text("Smoothing Radius");
        Text value = new Text("75");
        SmoothingRadiusBox.getChildren().addAll(SmoothingRadius, value);
        Slider smoothingRadius = new Slider(0, 100, 75);
        smoothingRadius.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Fluid2D.setSmoothingRadius(newValue.doubleValue());
                value.setText(String.valueOf(newValue.intValue()));
            }
        });
        HBox ParticleCountBox = new HBox(10);
        Text ParticleCount = new Text("Particle Count");
        Text value_particle = new Text("200");
        ParticleCountBox.getChildren().addAll(ParticleCount, value_particle);
        Slider particleCount = new Slider(0, 1000, 200);
        particleCount.valueProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Fluid2D.setParticleCount(newValue.intValue());
                value_particle.setText(String.valueOf(newValue.intValue()));
            }
        });
        Content.getChildren().addAll(SmoothingRadiusBox, smoothingRadius, ParticleCountBox, particleCount);
        Scene root = new Scene(Content);
        primaryStage.setScene(root);
        primaryStage.show();
        primaryStage.setTitle("Simulation Settings");
    }
}
