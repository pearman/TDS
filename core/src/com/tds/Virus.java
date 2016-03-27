/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author mattb
 */
public class Virus extends Entity{

    public Virus(Texture texture) {
        super(1, 200, texture, 0, 0, 32, 32);
        setX(100);
        setY(100);        
    }
    
    public Virus(Texture texture, float speed, int startx, int starty ) {
        super(1, speed, texture, 0, 0, 32, 32);
        setX(startx);
        setY(starty);
    }
    
    /**
     * @author KeisterBun
     * @param adminX
     * @param adminY 
     */
    public void move(float adminX, float adminY){
        float dirX =  adminX - getX();
        float dirY =  adminY - getY();
        double angle = Math.atan2(-dirX, dirY);
        float x, y;
        
        x = (float)Math.cos(angle + 90f) * getSpeed();
        y = (float)Math.sin(angle + 90f) * getSpeed();
        this.translate(x, y);
    }
    
}
