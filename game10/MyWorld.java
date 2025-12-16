import greenfoot.*;  // World, Actor, GreenfootImage, Greenfoot
public class MyWorld extends World {
   private GreenfootImage bgImage;  // original background image
   private int bgX = 0;             // scroll position
   private int scrollSpeed = 2;     // pixels per frame (adjust to taste)
   // ===== Distance Score (Option A) =====
   private long distancePx = 0;     // distance traveled in pixels
   // ===== Game Over Flag =====
   private boolean isGameOver = false;
   
   private int spawnTick = 0;           // 経過フレーム
   private int spawnInterval = 20;      // スポーン間隔（フレーム）
   private int baseMaxPerTick = 3;      // 通常スポーン時の最大数（0〜3の範囲で出す）
   private int burstChancePercent = 8;  // バースト（まとめて大量）発生確率（%）

   public MyWorld() {
       super(1200, 800, 1);
       // ====== BACKGROUND SETUP ======
       bgImage = new GreenfootImage("istockphoto-1403514917-612x612 (1).jpg");
       drawScrollingBackground();
       // ====== OBJECTS SETUP ======
       addObject(new UFO(), getWidth() / 2, getHeight() / 2);
       for (int i = 0; i < 5; i++) {
           int A = 0;
           int B = 1200;
           int x = A + (int)(Math.random() * ((B - A) + 1));
           int C = 0;
           int D = 800;
           int y = C + (int)(Math.random() * ((D - C) + 1));
           addObject(new asteroid(), x, y);
       }
       updateDistanceText();
   }
   public void act() {
       if (isGameOver) return;
       scrollBackground();
       // Option A: distance increases as the world scrolls
       distancePx += scrollSpeed;
       updateDistanceText();
       
       // ====== 永遠スポーン（右から左へ） ======
       spawnTick++;
       if (spawnTick % spawnInterval == 0) {
           spawnRandomAsteroids();

           // 難易度を少しずつ上げる例（間隔を縮める。最小8フレーム）
           if (spawnTick % (60 * 10) == 0 && spawnInterval > 8) { // 10秒ごと
               spawnInterval--;
           }
       }

   }
   private void updateDistanceText() {
       long meters = distancePx / 10; // 10 px = 1 m (adjust if you want)
       showText("Distance: " + meters + " m", 110, 30);
   }
   // Call this when the UFO hits an asteroid
   public void gameOver() {
       if (isGameOver) return; // prevent double-calls
       isGameOver = true;
       long meters = distancePx / 10;
       // Show game over text (center of screen)
       showText("GAME OVER", getWidth() / 2, getHeight() / 2);
       showText("Final Distance: " + meters + " m", getWidth() / 2, getHeight() / 2 + 50);
       Greenfoot.stop(); // freeze the game
   }
   /** Move the background image left and redraw it tiled across the world. */
   private void scrollBackground() {
       bgX -= scrollSpeed;
       int imgW = bgImage.getWidth();
       if (bgX <= -imgW) {
           bgX += imgW;
       }
       drawScrollingBackground();
   }
   /** Draw the background so it scrolls seamlessly. */
   private void drawScrollingBackground() {
       GreenfootImage bg = getBackground();
       bg.clear();
       int imgW = bgImage.getWidth();
       int x = bgX;
       while (x < getWidth()) {
           bg.drawImage(bgImage, x, 0);
           x += imgW;
       }
   }
   private void spawnRandomAsteroids() {
   // 通常は 0〜baseMaxPerTick の個数でスポーン
       int count = Greenfoot.getRandomNumber(baseMaxPerTick + 1); // 0,1,2,3

       // ときどきバースト（まとめて 5〜8 個）
       if (Greenfoot.getRandomNumber(100) < burstChancePercent) {
           count = 5 + Greenfoot.getRandomNumber(4); // 5,6,7,8
       }

       for (int i = 0; i < count; i++) {
           asteroid a = new asteroid();

           // 右端のさらに外から出すと自然に流れ込む
           int spawnX = getWidth() + 40;

           // Y は上下に少し余白を持たせてランダム
           int margin = 20;
           int spawnY = margin + Greenfoot.getRandomNumber(getHeight() - margin * 2);

           addObject(a, spawnX, spawnY);
       }
   }

}

    
    


   
