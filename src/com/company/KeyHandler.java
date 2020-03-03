package com.company;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {
    KeyEvent key;

    Dir direction = Dir.LEFT;
    @Override
    public void handle(KeyEvent keyEvent) {
        if(key.getCode() == KeyCode.W){
            direction = Dir.UP;
        }
        if(key.getCode() == KeyCode.A){
            direction = Dir.LEFT;
        }
        if(key.getCode() == KeyCode.S){
            direction = Dir.DOWN;
        }
        if(key.getCode() == KeyCode.D){
            direction = Dir.RIGHT;
        }
    }
}
