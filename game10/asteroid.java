
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class asteroid extends Actor {   
    private int rotationSpeed;   // 1フレームあたりの回転量（度）
    private int driftSpeed = 3;  // 左方向への移動速度（ピクセル）

    public asteroid() {
        rotationSpeed = Greenfoot.getRandomNumber(21) - 10;

        GreenfootImage img = getImage();
        if (img != null) {
            img.scale(img.getWidth() / 2, img.getHeight() / 2);
            setImage(img);
        }
    } 
    
    public void act() {
        // 回転
        turn(rotationSpeed);

        World w = getWorld();
        if (w == null) return;


        // 端に触れたら消す（クランプされても発火する）
        if (nextX <= 0 ) {
            World w = getWorld();
            if (w != null) {
                w.removeObject(this);
            }

            return;
        }

        // 実際に移動
        setLocation(nextX, y);
        CheckOutOfBonds();
    }
    
    public void CheckOutOfBonds(){
        World w = getWorld();
        
     
        
        if(this.getX()<=0){
            w.removeObject(this);
        }
}
}

