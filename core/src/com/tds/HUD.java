/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tds;

/**
 *
 * @author mattb
 */
public class HUD {
    private int totalScore;
    private int currentLevel;
    
    /**
     * @author KeisterBun
     */
    public HUD() {
        totalScore = 0;
        currentLevel = 1;
    }
    
    /**
     * @author KeisterBun
     */
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    /**
     * @author KeisterBun
     * @return int
     */
    public int getTotalScore() {
        return totalScore;
    }
    
    /**
     * @author KeisterBun
     */
    public void incrementCurrentLevel() {
        currentLevel++;
    }
    
    /**
     * @author KeisterBun
     * @param newTotal 
     */
    public void setTotalScore(int newTotal) {
        totalScore = newTotal;
    }
}
