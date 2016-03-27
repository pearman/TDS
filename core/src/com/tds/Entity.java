/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
/**
 *
 * @author mattb
 */
public class Entity extends Sprite{
    float health;
    float speed;
    float vx, vy;
    Circle boundingCircle;

    public Circle getBoundingCircle() {
        return boundingCircle;
    }
    
    public Entity(float health, float speed, Texture texture, 
            int srcX, int srcY, int srcWidth, int srcHeight) {
        super(texture, srcX, srcY, srcWidth, srcHeight);
        boundingCircle = new Circle(getX(), getY(), srcWidth);
        this.health = health;
        this.speed = speed;
    }

   
    public Entity(float health, float speed) {
        super();
        this.health = health;
        this.speed = speed;
    }    

    public float getHealth() {
        return health;
    }

    public void setHealth(float heath) {
        this.health = heath;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public Boolean checkHealth(){
        if(health > 0){
            return true;
        }
        return false;
    }
    
    public Boolean checkCollision(Entity e){
        Circle c1 = e.getBoundingCircle();
        Circle c2 = this.getBoundingCircle();
        return c1.overlaps(c2);
    }
}
