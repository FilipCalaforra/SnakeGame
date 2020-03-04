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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.LinkedList;

public class Main extends Application {
    static int width = 20;
    static int heigth = 20;
    static int cornersize = 25;
    static int speed = 10;
    int foodX = 0;
    int foodY = 0;
    static boolean gameOver = false;
    LinkedList<Cell> snake = new LinkedList<>();
    Dir direction = Dir.LEFT;
  //  private final int NUMBEROFSQUARES = 15;






    @Override
    public void start(Stage primaryStage) throws Exception {

        try{
            Pane pane = new Pane();
            Pane StartScreen = new Pane();
            VBox root = new VBox();
            Scene scene = new Scene(root,width*cornersize,heigth*cornersize);
            Canvas canvas = new Canvas(width*cornersize,heigth*cornersize);


            BorderPane bp = new BorderPane(StartScreen);
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
                    if(now - lastTick > 1000000000 / speed){
                        lastTick = now;
                        tick(gc);
                    }
                }
            }.start();

            //ADDS STARTING PARTS FOR THR SNAKE
            snake.add(new Cell(width/2,heigth/2));
            snake.add(new Cell(width/2,heigth/2));
            snake.add(new Cell(width/2,heigth/2));


            canvas.setOnKeyPressed(new KeyHandler());

            root.getChildren().add(canvas);

            primaryStage.setTitle("SNAKE GAME");
            primaryStage.setScene(scene);
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
    public void tick(GraphicsContext gc){
        if(gameOver){
            gc.setFill(Color.RED);
            gc.setFont(new Font("",50));
            gc.fillText("GAME OVER",100,250);
            return;
        }
        for(int i = snake.size() - 1; i>=1; i--){
            snake.get(i).x = snake.get(i-1).x;
            snake.get(i).y = snake.get(i-1).y;
        }
        switch(direction){
            case UP:
                snake.get(0).y--;
                if(snake.get(0).y<0){
                    gameOver=true;
                }break;
            case DOWN:
                snake.get(0).y++;
                if(snake.get(0).y>heigth){
                    gameOver=true;
                }break;
            case LEFT:
                snake.get(0).x--;
                if(snake.get(0).x<0){
                    gameOver=true;
                }break;
            case RIGHT:
                snake.get(0).x++;
                if(snake.get(0).x>width){
                    gameOver=true;
                }break;
        }
        /*
        if(foodX == snake.get(0).x && foodY == snake.get(0).y){
            snake.add(new Corner(-1,-1));



        }

         */
        //self destroy
        for(int i=1; i<snake.size();i++){
            if(snake.get(0).x == snake.get(i).x && snake.get(0).y==snake.get(i).y){
                gameOver = true;
            }
        }
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,width*cornersize,heigth*cornersize);


        //snake
        for(Cell canvas : snake){
            gc.setFill(Color.GRAY);
            gc.fillRect(canvas.x*cornersize,canvas.y*cornersize,cornersize-1,cornersize-1);
            gc.setFill(Color.GRAY);
            gc.fillRect(canvas.x*cornersize,canvas.y*cornersize,cornersize-2,cornersize-2);

        }
    }
}

