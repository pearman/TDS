package com.tds;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;

public class TDS extends ApplicationAdapter {
    HUD hud;
    SpriteBatch batch;
    Admin admin;
    Texture img;
    float posx;
    float posy;
    float mouseX, mouseY;
    float speed;
    BitmapFont pen;
    Virus v1;
    Wall[] walls;

    @Override
    public void create () {
        hud = new HUD();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        
        admin = new Admin(1, 3, 1, 200, img);
        posx = Gdx.graphics.getWidth()/2 - admin.getWidth()/2;
        posy = Gdx.graphics.getHeight()/2 - admin.getHeight()/2;
        admin.setPosition(posx, posy);
        admin.scale(.2f);
        
        Texture virusTexture = new Texture("bluevirus.jpg");
        v1 = new Virus(virusTexture);
        v1.setPosition(40, 40);
        
        pen = new BitmapFont();
        pen.setColor(Color.BLACK);
        
        walls = new Wall[4];
        
        int gap = 200;
        int wallWidth = 50;
        int worldHeight = Gdx.graphics.getHeight();
        int worldWidth = Gdx.graphics.getWidth();
        //Bottem Wall
        Wall temp = new Wall();
        temp.setSize(worldWidth - gap, wallWidth);
        temp.setPosition(gap/2, 0);
        walls[0] = temp;
        temp = new Wall();
        temp.setSize(worldWidth - gap, wallWidth);
        temp.setPosition(gap/2, worldHeight - wallWidth);
        walls[1] = temp;
        temp = new Wall();
        temp.setSize(wallWidth, worldHeight - gap);
        temp.setPosition(0, gap/2);
        walls[2] = temp;
        temp = new Wall();
        temp.setSize(wallWidth, worldHeight - gap);
        temp.setPosition(worldWidth - wallWidth, gap/2);
        walls[3] = temp;
    }

    @Override
    public void render () {
        admin.processMovement();   
        
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            System.exit(0);
        }
                
        
        batch.begin();
        for(Wall wall : walls){
            if(wall != null){
                wall.draw(batch);
            }
        }
        admin.draw(batch);
        admin.bullets.draw(batch);
        v1.draw(batch);
        hud.drawHud(batch, pen);
        
        batch.end();
    }
}
