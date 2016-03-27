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
import java.util.Random;

public class TDS extends ApplicationAdapter {
    HUD hud;
    SpriteBatch batch;
    Admin admin;
    Texture img;
    float posx;
    float posy;
    float mouseX, mouseY;
    float speed;
    int level;
    BitmapFont pen;
    Virus v1;
    ArrayList<Virus> virusList;
    Wall[] walls;
    Texture virusTexture;
    
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
        
        virusTexture = new Texture("bluevirus.jpg");
        
        virusList = new ArrayList<Virus>();
        level = 1;
        
        generateLevel(level);
        //v1 = new Virus(virusTexture);
        //v1.setPosition(40, 40);
        
        pen = new BitmapFont();
        pen.setColor(Color.YELLOW);

        
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
        admin.processMovement(virusList);   
        //v1.move(admin.getX() + admin.getWidth()/2, 
          //      admin.getY() + admin.getHeight()/2);
        Gdx.gl.glClearColor(.1f, .1f, .1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            System.exit(0);
        }
        hud.setCurrentLives(admin.getLives());
        

        for(Wall wall : walls){
            admin.wallCollison(wall);
        }
        
        batch.begin();
        for(Wall wall : walls){
            if(wall != null){
                wall.draw(batch);
            }
        }
        
        admin.draw(batch);
        admin.bullets.draw(batch);

        for(Virus v : virusList) {
            v.draw(batch);
            v.move(admin.getX() + admin.getWidth()/2, 
                    admin.getY() + admin.getHeight()/2);
            if( admin.getBoundingRectangle().overlaps(v.getBoundingRectangle()) ) {
                System.out.println("hit");
                v.setStatus(false);
                admin.setPosition(Gdx.graphics.getWidth()/2, 
                        Gdx.graphics.getHeight()/2);
                admin.setLives(admin.getLives()-1);
                if( admin.getLives() <= 0 ){
                    System.exit(0);
                }
            }
        }
        
        //v1.draw(batch);
        //v1.move(admin.getX(), admin.getY());
        hud.drawHud(batch, pen);
        
        batch.end();
        for(int i = virusList.size() - 1; i >= 0; i-- ) {
            if(virusList.get(i).getStatus() != true) {
                hud.setTotalScore(hud.getTotalScore() + 1);
                virusList.remove(virusList.get(i));                
            }
        }
        
        if(virusList.size() == 0){
            level += 1;
            hud.incrementCurrentLevel();
            generateLevel(level);
        }
    }
    
    void generateLevel(int levelNumber){
        int numberVirus = levelNumber*2 + levelNumber;
        for(int i = 0; i < numberVirus; i++) {
            v1 = new Virus(virusTexture);
            virusList.add(v1);
        }
        Random rand = new Random();
        for(Virus v : virusList){
            int pos = rand.nextInt() % 4;
            switch(pos){
                case 0:
                    v.setPosition(40, 40);
                    break;
                case 1:
                    v.setPosition(40, Gdx.graphics.getHeight() - 40);
                    break;
                case 2:
                    v.setPosition(Gdx.graphics.getWidth() - 40, 40);
                    break;
                case 3:
                    v.setPosition(Gdx.graphics.getWidth() - 40, 
                        Gdx.graphics.getHeight() -40);
                    break;
                default:
                    v.setPosition(Gdx.graphics.getWidth() - 40, 
                        Gdx.graphics.getHeight() -40);
                    break;
            }       
        }
    }
}
