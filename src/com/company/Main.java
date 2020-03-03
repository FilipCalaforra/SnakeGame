package com.company;

import javafx.animation.AnimationTimer;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
    static int width = 20;
    static int heigth = 20;
    static int cornersize = 25;
    static int speed = 5;
    static boolean gameOver = false;


    private final int NUMBEROFSQUARES = 15;




    Pane pane = new Pane();
    Pane StartScreen = new Pane();
    BorderPane bp = null;
    @Override
    public void start(Stage primaryStage) throws Exception {

        try{
            VBox root = new VBox();
            Scene scene = new Scene(root,width*cornersize,heigth*cornersize);
            Canvas canvas = new Canvas(width*cornersize,heigth*cornersize);
            bp = new BorderPane(StartScreen);
            Scene scene2 = new Scene(bp,600,600);
            Group start_root = new Group();
            Label menu = new Label("Snake");
            Button scoreboard = new Button("Scoreboard");
            Button start = new Button("Play");

            menu.setLayoutX(270);
            start.setLayoutX(270);
            start.setLayoutY(40);
            scoreboard.setLayoutX(270);
            scoreboard.setLayoutY(80);
            StartScreen.getChildren().addAll(menu,start,scoreboard);

            start.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    bp.setCenter(pane);
                }
            });

            GraphicsContext gc = canvas.getGraphicsContext2D();

            new AnimationTimer(){
                long lastTick = 0;
                public void handle(long now){
                    if(lastTick == 0){
                        lastTick = now;
                        tick(gc);
                        return;
                    }
                    if(now - lastTick > 100000000 / speed){
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start();

            canvas.setOnKeyPressed(new KeyHandler());

            root.getChildren().add(canvas);

            primaryStage.setTitle("SNAKE GAME");
            primaryStage.setScene(scene2);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
        /*
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




        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();

         */
        }
        private void tick(GraphicsContext gc){

        }
    }

