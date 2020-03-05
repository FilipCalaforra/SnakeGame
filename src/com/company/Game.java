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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Random;

public class Game extends Application {
    public static int width = 20;
    public static int heigth = 20;
    public static int cell_size = 25;
    public static int speed = 10;
    static boolean gameOver = false;
    static int foodcolor = 0;
    static int FoodX = 0;
    static int FoodY = 0;

    Dir direction = Dir.LEFT;
    Food food = new Food();
    Random rand = new Random();
    Snake snake = new Snake();
    //Initialize Objects

    VBox root = new VBox();




    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            generateFood();
            Scene scene = new Scene(root,width*cell_size,heigth*cell_size);
            Canvas canvas = new Canvas(width*cell_size,heigth*cell_size);
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


            //EVENT FOR CONTROLLING THE SNAKE WITH W-A-S-D KEYS INPUT
            scene.addEventFilter(KeyEvent.KEY_PRESSED, key ->{
                if (key.getCode() == KeyCode.W) {
                    direction = Dir.UP;
                }
                if (key.getCode() == KeyCode.A) {
                    direction = Dir.LEFT;
                }
                if (key.getCode() == KeyCode.S) {
                    direction = Dir.DOWN;
                }
                if (key.getCode() == KeyCode.D) {
                    direction = Dir.RIGHT;
                }
            });

            root.getChildren().add(canvas);
            primaryStage.setTitle("Snake");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public  void generateFood(){

        start: while(true){
        /*
            FoodX = rand.nextInt();
            FoodY = rand.nextInt();
            */
            food.setFoodX(rand.nextInt(width));
            food.setFoodY(rand.nextInt(heigth));


            for(Cell canvas : snake.getSnakeList()){
                if(canvas.x == food.getFoodX() && canvas.y==food.getFoodY()){
                    continue start;
                }
            }
            foodcolor = rand.nextInt(5);
            speed++;
            break;

        }
    }
    public void tick(GraphicsContext gc){

        if(gameOver){
            gc.setFill(Color.MEDIUMBLUE);
            gc.setFont(new Font("",50));
            gc.fillText("GAME OVER",100,250);
            return;
        }
        for(int i = snake.getSnakeList().size() - 1; i>=1; i--){
            snake.getSnakeList().get(i).x = snake.getSnakeList().get(i-1).x;
            snake.getSnakeList().get(i).y = snake.getSnakeList().get(i-1).y;
        }
        switch(direction){
            case UP:
                snake.getSnakeList().get(0).y--;
                if(snake.getSnakeList().get(0).y<0){
                    gameOver=true;
                }break;
            case DOWN:
                snake.getSnakeList().get(0).y++;
                if(snake.getSnakeList().get(0).y>heigth){
                    gameOver=true;
                }break;
            case LEFT:
                snake.getSnakeList().get(0).x--;
                if(snake.getSnakeList().get(0).x<0){
                    gameOver=true;
                }break;
            case RIGHT:
                snake.getSnakeList().get(0).x++;
                if(snake.getSnakeList().get(0).x>width){
                    gameOver=true;
                }break;
        }

        if(food.getFoodX() == snake.getSnakeList().get(0).x && food.getFoodY() == snake.getSnakeList().get(0).y ){
            snake.getSnakeList().add(new Cell(-1,-1));
            generateFood();
        }

        //self destroy
        for(int i=1; i<snake.getSnakeList().size();i++){
            if(snake.getSnakeList().get(0).x == snake.getSnakeList().get(i).x && snake.getSnakeList().get(0).y==snake.getSnakeList().get(i).y){
                gameOver = true;

            }
        }
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,width*cell_size,heigth*cell_size);

        //random foodcolor
        Color fc = Color.WHITE;

        switch(foodcolor){
            case 0: fc = Color.PURPLE;
                break;
            case 1: fc = Color.LIGHTBLUE;
                break;
            case 2: fc = Color.YELLOWGREEN;
                break;
            case 3: fc = Color.GREEN;
                break;
            case 4: fc = Color.ORANGE;
                break;
        }
        gc.setFill(fc);
        gc.fillOval(food.getFoodX()*cell_size,food.getFoodY()*cell_size,cell_size,cell_size);
        //snake
        for(Cell canvas : snake.getSnakeList()){

            gc.setFill(Color.DARKSEAGREEN);
            gc.fillRect(canvas.x*cell_size,canvas.y*cell_size,cell_size-1,cell_size-1);
            gc.setFill(Color.GREEN);
            gc.fillRect(canvas.x*cell_size,canvas.y*cell_size,cell_size-2,cell_size-2);
        }
    }
}

