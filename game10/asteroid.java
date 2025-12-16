
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

        // 次の位置
        int nextX = getX() - driftSpeed;
        int y = getY();

        // 画像の半幅・半高
        GreenfootImage img = getImage();
        int halfW = (img != null) ? img.getWidth() / 2 : 0;
        int halfH = (img != null) ? img.getHeight() / 2 : 0;

        int worldW = w.getWidth();
        int worldH = w.getHeight();

        // 画面外判定（完全に外へ出たときのみ）
        boolean offLeft   = nextX < -halfW;
        boolean offRight  = nextX > worldW + halfW;
        boolean offTop    = y < -halfH;
        boolean offBottom = y > worldH + halfH;

        if (offLeft || offRight || offTop || offBottom) {
            w.removeObject(this);
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

