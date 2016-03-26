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

public class TDS extends ApplicationAdapter {
	SpriteBatch batch;
        Sprite sprite;
	Texture img;
        int posx;
        int posy;
        int speed;
	
	@Override
	public void create () {
            batch = new SpriteBatch();
            img = new Texture("badlogic.jpg");
            sprite = new Sprite(img, 0, 0, 64, 64);
            posx = 0;
            posy = 0;
            speed = 10;
	}

	@Override
	public void render () {
            if(Gdx.input.isKeyPressed(Keys.A))
                posx -= Gdx.graphics.getDeltaTime() * speed;
            if(Gdx.input.isKeyPressed(Keys.D))
                posx += Gdx.graphics.getDeltaTime() * speed;
            if(Gdx.input.isKeyPressed(Keys.W)) 
                posy += Gdx.graphics.getDeltaTime() * speed;
            if(Gdx.input.isKeyPressed(Keys.S)) 
                posy -= Gdx.graphics.getDeltaTime() * speed;
                
            
            System.out.println(Integer.toString(posx));
            
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(sprite, posx, posy);
            batch.end();
        }
}
