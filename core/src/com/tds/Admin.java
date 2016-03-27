/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

/**
 *
 * @author mattb
 */
public class Admin extends Entity{   
    int lives;
    ParticleSystem bullets;

    public Admin(float strength, int lives, float health, float speed, 
            Texture texture) {
        super(health, speed, texture, 0, 0, 64, 64);
        this.lives = lives;
        bullets = new ParticleSystem(texture);
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
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            bullets.shoot(4, getRotation(), getX(), getY(), 20);
                
        this.boundingCircle.setPosition(this.getX(), this.getY());
        
        setRotation((float)Math.toDegrees(angle));
        
        bullets.process();
    }
    
//    public Boolean checkCollision(Entity e){
//        //Circle c1 = e.getBoundingCircle();
//        //Circle c2 = this.getBoundingCircle();
//        //return c1.overlaps(c2);
//    }
//}
}