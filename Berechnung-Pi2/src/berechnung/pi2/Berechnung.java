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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Berechnung extends Application {

    GuiProject gui = new GuiProject(1,100);

    double inside;
    double outside;
    
    int delay, stepsize,step;

    private static final int MAX = 1200;
    private static final int MARGIN = 50;

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    private Random random = new Random();

    private Label label = new Label("Label");

    private GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    public Berechnung() {
        inside = 0;
        outside = 0;
        delay = 1000;
        stepsize = 1;
        step = 0;
    }

    @Override
    public void start(Stage primaryStage) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double size = Math.min(MAX,
                Math.min(primaryScreenBounds.getHeight(), primaryScreenBounds.getWidth()))
                - MARGIN;

        primaryStage.setTitle("Drawing Operations Test");
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    scheduler.shutdown();
                    stop();
                } catch (Exception ex) {
                    System.err.println("stop failed: " + ex);
                    ex.printStackTrace();
                }
            }
        });

        Group root = new Group();
        Canvas canvas = new Canvas(size, size);
        gc = canvas.getGraphicsContext2D();
        label.setTextFill(Color.web("#000000"));
        label.setFont(new Font("Arial", 30));
        label.setContentDisplay(ContentDisplay.RIGHT);

        BoxBlur blur = new BoxBlur();
        blur.setWidth(1);
        blur.setHeight(1);
        blur.setIterations(1);
        gc.setEffect(blur);

        drawShapes(gc, size);

        ObservableList<Node> top = root.getChildren();
        top.add(canvas);
        top.add(label);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                calc(stepsize, size);
                step ++;

                    delay = gui.textd();
                    stepsize = gui.texts();
                    if(step > 1000/delay) {

                        scheduler.scheduleAtFixedRate(task, delay, delay, TimeUnit.MILLISECONDS);
                        
                    }


            
            }
        };

        scheduler.scheduleAtFixedRate(task, 1, 1, TimeUnit.MILLISECONDS);
    }

    private void drawShapes(GraphicsContext gc, double size) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1.5);
        gc.moveTo(0, 0);
        gc.arc(0, 0, size, size, 0.0d, -90.0d);
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

    private void calc(int num, double size) {
        System.out.println("calc(" + num + ")");

        for (int i = 0; i < num; ++i) {
            double x = random.nextDouble() * size;
            double y = random.nextDouble() * size;
            double pi = inside / (outside + inside) * 4;
            String msg = "\n \n \n \n                                                                                                                     Inside: " + inside
                    + "\n                                                                                                                      Total:  " + (inside + outside)
                    + "\n                                                                                                                      => Pi: " + pi;

            if (x * x + y * y > size * size) {
                // pointAt(x, y, Color.GREEN);
                render(msg, x, y, Color.GREEN);
                ++outside;
            } else {
                // pointAt(x, y, Color.RED);
                render(msg, x, y, Color.RED);
                ++inside;
                gui.setin(inside);
                gui.setout(outside);
                gui.setpi(pi);
            }
        }
    }

    private void render(String msg, double x, double y, Paint c) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                pointAt(x, y, c);
                label.setText(msg);
                System.out.println(msg);
                
                
                
                        
            }
        });
    }
}
