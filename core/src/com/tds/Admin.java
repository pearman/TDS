
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author mattb
 */
public class Admin extends Entity{   
    int lives;
    ParticleSystem bullets;
    float aTimer = 0;
    int frame = 0;
    float oldX, oldY;
    
    ArrayList<Texture> textures = new ArrayList<Texture>();

    public Admin(float strength, int lives, float health, float speed, 
            Texture texture) {
        super(health, speed, texture, 0, 0, 256, 256);
        
        setScale(0.25f);
        
        this.lives = lives;
        bullets = new ParticleSystem(new Texture("Bullet.png"));
        textures.add(new Texture("frontBase.png"));
        textures.add(new Texture("frontLeft.png"));
        textures.add(new Texture("frontRight.png"));
        textures.add(new Texture("sideBase.png"));
        textures.add(new Texture("sideLeft.png"));
        textures.add(new Texture("sideRight.png"));
        textures.add(new Texture("backBase.png"));
        
        setTexture(textures.get(0));
    }

    
    public Admin(float strength, int lives, float health, float speed) {
        super(health, speed);
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
    
    public void processMovement(ArrayList<Virus> enemies){
        oldX = getX();
        oldY = getY();
        this.setOriginCenter();
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        
        boolean keyPressed = false;
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            keyPressed = true;
            this.setX(this.getX() - Gdx.graphics.getDeltaTime() * getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            keyPressed = true;
            this.setX(this.getX() + Gdx.graphics.getDeltaTime() * getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            keyPressed = true;
            this.setY(this.getY() + Gdx.graphics.getDeltaTime() * getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            keyPressed = true;
            this.setY(this.getY() - Gdx.graphics.getDeltaTime() * getSpeed());
        }
        
        float dirX =  mouseX - getX() - getWidth()/2;
        float dirY =  mouseY - getY() - getHeight()/2;
        double angle = Math.atan2(-dirX, dirY);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
            bullets.shoot(4, 0.1f, (float)Math.toDegrees(angle), getX() + 100, getY() + 100, 20);
                
        this.boundingCircle.setPosition(this.getX(), this.getY());
        
        if(keyPressed) {
            aTimer += Gdx.graphics.getDeltaTime();
            if(aTimer > 0.1f) {
                frame = (frame + 1) % 3;
                aTimer = 0;
            }
        }
        
        float range = (float)(Math.PI/4);
        if(Math.abs(angle - 0) < range) {
            setTexture(textures.get(6));
        }
        if(Math.abs(angle - Math.PI/2) < range) { 
            setTexture(textures.get(3 + frame));
            setFlip(true, false);
        }
        if(Math.abs(angle - Math.PI) < range) { 
            setTexture(textures.get(0 + frame));
        }
        if(Math.abs(angle + Math.PI / 2) < range) { 
            setTexture(textures.get(3 + frame));
            setFlip(false, false);
        }
        
        //setRotation((float)Math.toDegrees(angle));
        
        bullets.process(enemies);
    }
    
    void draw(SpriteBatch batch) {
        super.draw(batch);
    }
    
    void wallCollison(Wall wall){
        Rectangle r1 = wall.getBoundingRectangle();
        Rectangle r2 = this.getBoundingRectangle();
        
        if(r1.overlaps(r2)){
            setX(oldX);
            setY(oldY);
        }
    }
}
