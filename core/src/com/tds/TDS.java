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
        float posx;
        float posy;
        float speed;
	
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
                posx -= speed;
            if(Gdx.input.isKeyPressed(Keys.D))
                posx += speed;
            if(Gdx.input.isKeyPressed(Keys.W)) 
                posy += speed;
            if(Gdx.input.isKeyPressed(Keys.S)) 
                posy -= speed;
            
            sprite.setX(posx);
            sprite.setY(posy);
            Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            sprite.draw(batch);
            batch.end();
        }
}
