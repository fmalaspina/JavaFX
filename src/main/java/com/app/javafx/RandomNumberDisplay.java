package com.app.javafx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Random;

public class RandomNumberDisplay extends Application {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Random Number Display");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.show();

        // Generate random numbers and display them
        Random random = new Random();
        new Thread(() -> {
            while (true) {
                double x = random.nextDouble() * WIDTH;
                double y = random.nextDouble() * HEIGHT;
                int randomNumber = random.nextInt(100); // Adjust the range as needed
                gc.clearRect(0, 0, WIDTH, HEIGHT); // Clear the screen
                gc.fillText(Integer.toString(randomNumber), x, y); // Display the random number
                try {
                    Thread.sleep(100); // Adjust the speed of displaying random numbers
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}