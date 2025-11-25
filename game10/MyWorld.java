
import greenfoot.*;  // World, Actor, GreenfootImage, Greenfoot

public class MyWorld extends World {

    public MyWorld() {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1); 
        addObject(new UFO(), getWidth()/2, getHeight()/2);
    }
}

