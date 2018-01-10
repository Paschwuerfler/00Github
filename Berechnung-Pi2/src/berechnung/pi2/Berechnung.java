/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package berechnung.pi2;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Berechnung extends Application {
    
    double inside;
    double outside;

    private static final int MAX = 900;

    private Random random = new Random();

    private GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    public Berechnung() {
        inside = 0;
        outside = 0;
    }

    @Override
    public void start(Stage primaryStage) {

        
        
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(MAX, MAX);
        gc = canvas.getGraphicsContext2D();

        BoxBlur blur = new BoxBlur();
        blur.setWidth(1);
        blur.setHeight(1);
        blur.setIterations(1);
        gc.setEffect(blur);

        drawShapes(gc);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        /*
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                calc(400);
                return null;
            }
        };

        task.setOnSucceeded(event -> {
            // calc(400);
            // new Thread(task).run();
        });
         */
        Runnable task = new Runnable() {
            @Override
            public void run() {
                calc(1);
            }
        };
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
        scheduler.scheduleAtFixedRate(task, 1, 10, TimeUnit.MILLISECONDS);
    }

    private void drawShapes(GraphicsContext gc) {

        
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1.5);
        /*
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
         */
        // gc.strokeOval(0, 0, 400, 400);

        gc.moveTo(0, 0);
        gc.arc(0, 0, MAX, MAX, 0.0d, -90.0d);
        gc.stroke();



    }

    private void pointAt(double x, double y, Paint c) {
        //  Paint old = gc.getStroke();
        gc.beginPath();
        gc.setStroke(c);

        gc.moveTo(x - 10, y);
        gc.lineTo(x + 10, y);
        gc.moveTo(x, y - 10);
        gc.lineTo(x, y + 10);
        gc.stroke();
        //  gc.setStroke(old);
    }

    private void calc(int num) {
        System.out.println("calc(" + num + ")");

        for (int i = 0; i < num; ++i) {
            double x = random.nextDouble() * MAX;
            double y = random.nextDouble() * MAX;
            if (x * x + y * y > MAX * MAX) {
                pointAt(x, y, Color.GREEN);
                ++outside;
            } else {
                pointAt(x, y, Color.RED);
                ++inside;
            }
        }
        double pi = inside / (outside + inside) * 4;
        System.out.println(" Inside: " + inside + " Total: " + (inside + outside) + " => Pi: " + pi);
    }
}
