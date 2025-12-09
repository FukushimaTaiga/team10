import greenfoot.*;

public class asteroid extends Actor
{
   private int rotationSpeed;  // 回転速度
   public asteroid()
   {
       // -10〜10の中でランダムに回転速度を決める
       rotationSpeed = Greenfoot.getRandomNumber(21) - 10;
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