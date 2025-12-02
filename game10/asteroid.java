import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class asteroid extends Actor
{
    public void act() 
    {
        int A = 0;
        int B = 359;
        int C = A + (int)(Math.random()*((B-A)+1));
        setRotation(C);
        move(5);
        
    }    
}
