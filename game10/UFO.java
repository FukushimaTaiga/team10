import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class UFO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UFO extends Actor
{
    /**
     * Act - do whatever the UFO wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
         if(Greenfoot.isKeyDown("right")){
            setRotation(180);
            move(4);
        }  if(Greenfoot.isKeyDown("left")){
            setRotation(0);
            move(4);
        }  if(Greenfoot.isKeyDown("up")){
            setRotation(90);
            move(4);
        }  if(Greenfoot.isKeyDown("down")){
            setRotation(-90);
            move(4);
        } 
        
        /*Actor asteroid = getOneIntersectingObject(asteroid.class );
       
        if( asteroid != null ){
          getWorld().showText( "GAME OVER", 400, 200 );
          Greenfoot.stop();
        }*/
    }    
}
