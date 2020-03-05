package com.company;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Apple {

    Circle goodApple = new Circle(30);
    public Circle Apple(int x, int y) {
        Image im = new Image("C:\\Users\\filip\\IdeaProjects\\Snakegame3\\src\\com\\company\\photos\\images.jpg");
        goodApple.setFill(Color.GREEN);
      //  goodApple.setFill(new ImagePattern(im));
        goodApple.setLayoutX(x);
        goodApple.setLayoutY(y);
         /*badApple = new Circle(30);
        badApple.setFill(new ImagePattern(im));
        badApple.setLayoutX(x);
        badApple.setLayoutY(y);*/




    return goodApple;}


}
