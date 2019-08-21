package com.ipnoise.ocp8.clock;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * Created by klaas on 6-4-2017.
 */
public class DrawClock extends Application {



    private static final double W = 300;
    private static final double H = 300;
    private static final double D = 20 ; //diameter
    private static final double MINUTE_DIAL_LENGTH = 50;



    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("Drawing Operations Test");
        DoubleProperty x  = new SimpleDoubleProperty();
        DoubleProperty y  = new SimpleDoubleProperty();

        Canvas canvas = new Canvas(W, H);

        Timeline timeLine = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(x,0),
                        new KeyValue(y,0)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(x,W-D),
                        new KeyValue(y,H-D)
                )
        );

        timeLine.setAutoReverse(true);
        timeLine.setCycleCount(Timeline.INDEFINITE);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, W, H);
                gc.setFill(Color.FORESTGREEN);
                gc.fillOval(
                        x.doubleValue(),
                        H-D/2,
                        D/2,
                        D/2
                );
                // clock
                gc.setLineWidth(1);
                gc.strokeOval(D,D,W-2*D,H-2*D);
                // minuten
                gc.setLineWidth(2);

                gc.strokeLine(W/2,H/2-80,W/2 ,H/2);
                gc.strokeLine(W/2,x.doubleValue(),x.doubleValue() ,H/2);// x2 en y2 moeten anders worden
                // uur
                // xpos ypos
                gc.setLineWidth(4);
                gc.strokeLine(W/2,H/2-MINUTE_DIAL_LENGTH,W/2 ,H/2);


            }

//                gc.strokeOval(50,50,200,200);
//                gc.setLineWidth(3);
//                gc.strokeLine(centerX,centerY-75,centerX ,centerY);
//                gc.setLineWidth(4);
//                gc.strokeLine(centerX,centerY-50,centerX ,centerY);
        };

        Group root = new Group();

      //  GraphicsContext gc = canvas.getGraphicsContext2D();
        // drawShapes(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        timer.start();
        timeLine.play();

    }

//    private void drawShapes(GraphicsContext gc) {
//        double centerX = 150.;
//        double centerY = 150.;
//        gc.strokeOval(50,50,200,200);
//        gc.setLineWidth(3);
//        gc.strokeLine(centerX,centerY-75,centerX ,centerY);
//        gc.setLineWidth(4);
//        gc.strokeLine(centerX,centerY-50,centerX ,centerY);
//    }

    public static void main(String[] args) {
        launch(args);
    }
}