import greenfoot.*;  // World, Actor, GreenfootImage, Greenfoot
public class MyWorld extends World {
   private GreenfootImage bgImage;  // original background image
   private int bgX = 0;             // scroll position
   private int scrollSpeed = 2;     // pixels per frame (adjust to taste)
   // ===== Distance Score (Option A) =====
   private long distancePx = 0;     // distance traveled in pixels
   // ===== Game Over Flag =====
   private boolean isGameOver = false;
   
   // ===== スポーン用タイマー =====
    private SimpleTimer spawnTimer = new SimpleTimer();
    private int spawnIntervalMs = 700;  // 出現間隔（ミリ秒）

   public MyWorld() {
       super(1200, 800, 1);
       // ====== BACKGROUND SETUP ======
       bgImage = new GreenfootImage("istockphoto-1403514917-612x612 (1).jpg");
       drawScrollingBackground();
       // ====== OBJECTS SETUP ======
       addObject(new UFO(), 150, getHeight() / 2);
       for (int i = 0; i < 5; i++) {
           int A = getWidth();
           int B = getWidth() + 300;
           int x = A + (int)(Math.random() * ((B - A) + 1));
           int C = 0;
           int D = 800;
           int y = C + (int)(Math.random() * ((D - C) + 1));
           addObject(new asteroid(), x, y);
       }
       updateDistanceText();
       addAsteroid();
       spawnTimer.mark();
   }
   public void act() {
       if (isGameOver) return;
       scrollBackground();
       // Option A: distance increases as the world scrolls
       distancePx += scrollSpeed;
       updateDistanceText();
       
       if (spawnTimer.millisElapsed() >= spawnIntervalMs) {
            addAsteroid();
            spawnTimer.mark();  // 次のカウント開始
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
   private void addAsteroid() {
        asteroid a = new asteroid();
        int worldW = getWidth();
        int worldH = getHeight();

        // 画像の半幅（右外から入場するために使用）
        GreenfootImage img = a.getImage();
        int halfW = (img != null) ? img.getWidth() / 2 : 0;

        int y = Greenfoot.getRandomNumber(worldH); // 適当な縦位置
        int startX = worldW + halfW;               // 右の外から入場
        addObject(a, startX, y);
    }
}