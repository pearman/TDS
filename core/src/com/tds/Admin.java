/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author mattb
 */
public class Admin extends Entity{   
    int lives;

    public Admin(float strength, int lives, float health, float speed, 
            Texture texture) {
        super(health, speed, texture, 0, 0, 64, 64);
        this.lives = lives;
    }

    
    public Admin(float strength, int lives, float health, float speed) {
        super(health, speed);
        this.lives = lives;
        
        this.setRegion(0, 0, 64, 64);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public void processMovement(){
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            this.setX(this.getX() - Gdx.graphics.getDeltaTime() * getSpeed());
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            this.setX(this.getX() + Gdx.graphics.getDeltaTime() * getSpeed());
        if(Gdx.input.isKeyPressed(Input.Keys.W)) 
            this.setY(this.getY() + Gdx.graphics.getDeltaTime() * getSpeed());
        if(Gdx.input.isKeyPressed(Input.Keys.S)) 
            this.setY(this.getY() - Gdx.graphics.getDeltaTime() * getSpeed());
        
        float dirX =  mouseX - getX() - getWidth()/2;
        float dirY =  mouseY - getY() - getHeight()/2;
        double angle = Math.atan2(-dirX, dirY);
        
        this.boundingCircle.setPosition(this.getX(), this.getY());
        
        setRotation((float)Math.toDegrees(angle));
    }
    
    public Boolean checkCollision(Entity e){
        Rectangle r1 = e.getBoundingRectangle();
        Rectangle r2 = e.getBoundingRectangle();
        
        return r2.overlaps(r1);
    }
}
