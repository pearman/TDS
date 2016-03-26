/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author mattb
 */
public class Entity extends Sprite{
    float health;
    float speed;

    public Entity(float health, float speed) {
        this.health = health;
        this.speed = speed;
    }    

    public float getHeath() {
        return health;
    }

    public void setHeath(float heath) {
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
}
