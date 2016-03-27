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
    }

    @Override
    public void render () {
        admin.processMovement();   
        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        admin.draw(batch);
        v1.draw(batch);
        batch.end();
    }
}
