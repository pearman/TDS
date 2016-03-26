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
    Sprite sprite;
    Texture img;
    float posx;
    float posy;
    float mouseX, mouseY;
    float speed;
    BitmapFont pen;

    @Override
    public void create () {
        hud = new HUD();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img, 0, 0, 64, 64);
        posx = 0;
        posy = 0;
        speed = 100;
        pen = new BitmapFont();
        pen.setColor(Color.BLACK);
    }

    @Override
    public void render () {
        mouseX = Gdx.input.getX();
        mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        if(Gdx.input.isKeyPressed(Keys.A))
            posx -= Gdx.graphics.getDeltaTime() * speed;
        if(Gdx.input.isKeyPressed(Keys.D))
            posx += Gdx.graphics.getDeltaTime() * speed;
        if(Gdx.input.isKeyPressed(Keys.W)) 
            posy += Gdx.graphics.getDeltaTime() * speed;
        if(Gdx.input.isKeyPressed(Keys.S)) 
            posy -= Gdx.graphics.getDeltaTime() * speed;

        float dirX =  mouseX - posx;
        float dirY =  mouseY - posy;
        double angle = Math.atan2(-dirX, dirY);

        sprite.setX(posx);
        sprite.setY(posy);

        sprite.setRotation((float)Math.toDegrees(angle));
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        pen.draw(batch, Double.toString(dirX), 50, 250);
        pen.draw(batch, Double.toString(mouseX), 100, 250);
        pen.draw(batch, Double.toString(dirY), 50, 300);
        pen.draw(batch, Double.toString(mouseY), 100, 300);
        pen.draw(batch, Double.toString(angle), 50, 200);
        sprite.draw(batch);
        
        batch.end();
    }
}
