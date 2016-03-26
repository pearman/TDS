/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;

import com.tds.Intity;
/**
 *
 * @author mattb
 */
public class Admin extends Intity{   
    float strength;
    int lives;

    public Admin(float strength, int lives, float health, float speed) {
        super(health, speed);
        this.strength = strength;
        this.lives = lives;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
