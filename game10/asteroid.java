
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class asteroid extends Actor {   
    private int rotationSpeed;   // 1フレームあたりの回転量（度）
    private int driftSpeed = 3;  // 左方向への移動速度（ピクセル）

    public asteroid() {
        // -10〜+10 の範囲で回転速度をランダムに決定（0もあり）
        rotationSpeed = Greenfoot.getRandomNumber(21) - 10;

        // 画像を一度だけ縮小（50%）
        GreenfootImage img = getImage();
        if (img != null) {
            img.scale(img.getWidth() / 2, img.getHeight() / 2);
            setImage(img);
        }
    } 
    
    public void act() {
        turn(rotationSpeed);

        // 左へ移動
        int nextX = getX() - driftSpeed;

        // 端に触れたら消す（クランプされても発火する）
        if (nextX <= 0 ) {
            World w = getWorld();
            if (w != null) {
                w.removeObject(this);
            }
            return;
        }

    setLocation(nextX, getY());
}

}
