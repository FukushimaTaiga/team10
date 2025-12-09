
import greenfoot.*;  // World, Actor, GreenfootImage, Greenfoot

public class MyWorld extends World {

    public MyWorld() {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 

        addObject(new UFO(), getWidth()/2, getHeight()/2);
        
        for(int i = 0; i < 5; i++)
        {
            int A = 0;
            int B = 1200;
            int x = A + (int)(Math.random()*((B-A)+1));
        
            int C = 0;
            int D = 800;
            int y = A + (int)(Math.random()*((D-A)+1));
            addObject(new asteroid(), x, y);
        }

    }
    
}

