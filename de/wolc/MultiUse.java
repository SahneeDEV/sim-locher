package de.wolc;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;


public class MultiUse{
        /**
     * Holt sich die größe aller angeschlossenen Monitore
     * @return int[width_screen1, height_screen1, width_screen2, height_screen2,...]
     */
    public int[] GetScreenSize(){
        //Define 
        GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] graphDev = graphEnv.getScreenDevices();
        
        //Variables
        int[] screenSizes = new int[(graphDev.length * 2)];
        int count = 0;
        
        for(int i = 0; i < graphDev.length; i++){
            screenSizes[count] = graphDev[i].getDisplayMode().getWidth();
            screenSizes[count + 1] = graphDev[i].getDisplayMode().getHeight();
            count = count + 2;
        }
        
        return screenSizes;
    }
}