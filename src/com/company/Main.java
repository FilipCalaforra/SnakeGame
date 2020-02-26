package com.company;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    private final int NUMBEROFSQUARES = 15;
    Pane pane = new Pane();
    Pane StartScreen = new Pane();
    BorderPane bp = null;
    @Override
    public void start(Stage primaryStage) throws Exception {
        bp = new BorderPane(StartScreen);
        Scene scene = new Scene(bp,600,600);
        Group root = new Group();
        Label menu = new Label("Snake");
        menu.setLayoutX(270);
        Button start = new Button("Play");
        start.setLayoutX(270);
        start.setLayoutY(40);
        Button scoreboard = new Button("Scoreboard");
        scoreboard.setLayoutX(270);
        scoreboard.setLayoutY(80);
        StartScreen.getChildren().addAll(menu,start,scoreboard);
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            bp.setCenter(pane);
            }
        });
        Canvas canvas = new Canvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
            for (int row = 0; row < NUMBEROFSQUARES; row++) {
                for (int col = 0; col < NUMBEROFSQUARES; col++) {
                    Rectangle border = new Rectangle(40,40);
                    border.setStroke(Color.BLACK);
                    border.setFill(Color.WHITE);
                    border.setTranslateX(row*40);
                    border.setTranslateY(col*40);
                    pane.getChildren().add(border);
                }
            }


        System.out.println("kuba sucks");

        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();


        }
        // write your code here
    }

