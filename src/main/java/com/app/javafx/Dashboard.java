package com.app.javafx;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Dashboard extends Application {

    private static final int MAX_VALUE = 100;
    private static final int UPDATE_INTERVAL_MS = 100; // Update interval in milliseconds

    private ProgressBar vuMeter1;
    private ProgressBar vuMeter2;
    private Random random;

    @Override
    public void start(Stage primaryStage) {
        vuMeter1 = new ProgressBar();
        vuMeter1.setStyle("-fx-accent: red;"); // Customize color if needed
        vuMeter2 = new ProgressBar();
        vuMeter2.setStyle("-fx-accent: blue;"); // Customize color if needed

        HBox root = new HBox(10);
        root.getChildren().addAll(vuMeter1, vuMeter2);

        Scene scene = new Scene(root, 300, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("VU Meter Dashboard");
        primaryStage.show();

        random = new Random();

        // Start updating VU meters
        new AnimationTimer() {
            long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (now - lastUpdate >= UPDATE_INTERVAL_MS * 1_000_000) {
                    updateVUMeters();
                    lastUpdate = now;
                }
            }
        }.start();
    }

    private void updateVUMeters() {
        double value1 = random.nextDouble();
        double value2 = random.nextDouble();

        vuMeter1.setProgress(value1);
        vuMeter2.setProgress(value2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
