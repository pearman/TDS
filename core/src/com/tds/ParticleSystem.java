/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;

/**
 *
 * @author Gabe
 */
public class ParticleSystem {
    float vx, vy;
    float rateTimer = 0;
    Texture texture;
    ArrayList<Entity> particles = new ArrayList<Entity>();
    
    public ParticleSystem(Texture pTex) {
        texture = pTex;
    }
    
    void shoot(float secondsOfLife, float fireRate, float angle, float x, float y, float speed) {
        if(rateTimer > fireRate) {
            Entity e = new Entity(secondsOfLife, speed, texture, 0, 0, 64, 64);;
            e.setTexture(texture);
            e.vx = (float)Math.cos(Math.toRadians(angle) + (float)(Math.PI / 2)) * speed;
            e.vy = (float)Math.sin(Math.toRadians(angle) + (float)(Math.PI / 2)) * speed;
            e.health = secondsOfLife;
            e.setRotation(angle);
            e.setPosition(x, y);
            particles.add(e);
            rateTimer = 0;
        }
    }
    
    void process() {
        float time = Gdx.graphics.getDeltaTime();
        rateTimer += time;
        for(int i = particles.size() - 1; i >= 0; i--) {
            particles.get(i).translate(particles.get(i).vx, particles.get(i).vy);
            System.out.println(particles.get(i).getX());
            particles.get(i).health -= time;
            if(particles.get(i).health <= 0) {
                particles.remove(i);
            }
        }
    }
    
    void draw(SpriteBatch batch) {
        for(Entity e : particles) {
            e.draw(batch);
        }
    }

}
