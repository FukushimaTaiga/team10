import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class asteroid extends Actor
{   
    private int rotationSpeed; 
    public asteroid() {
        // Scale image once (adjust to taste)
        rotationSpeed = Greenfoot.getRandomNumber(21) - 10;
        GreenfootImage img = getImage();
        img.scale(img.getWidth() / 2, img.getHeight() / 2); // 50% size
        setImage(img);
    }
    
     public void act()
    {
       // 回転
       turn(rotationSpeed);
       // 左へ移動
       setRotation(180);
       move(3);
       // 画面外に出たら削除
       if (getX() <= 0) {
           getWorld().removeObject(this);
       }
    }
}

