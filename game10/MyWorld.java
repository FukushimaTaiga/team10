
import greenfoot.*;  // World, Actor, GreenfootImage, Greenfoot
public class MyWorld extends World {
   private GreenfootImage bgImage;  // original background image
   private int bgX = 0;             // scroll position
   private int scrollSpeed = 2;      // pixels per frame (adjust to taste)
   
   private GreenfootSound backgroundMusic = new GreenfootSound("backsound.mp3");

   // ===== Distance Score (Option A) =====
   private long distancePx = 0;     // distance traveled in pixels
   // ===== Game Over Flag =====
   private boolean isGameOver = false;

   // ===== Asteroid Spawning (NEW) =====
   private long lastSpawnTime = 0;       // last time an asteroid was spawned (ms)
   private int spawnIntervalMs = 1000;   // spawn every 1000 ms (1 second)
   private int spawnOffsetRight = 300;   // spawn to the right of the screen so it slides in

   public MyWorld() {
       super(1200, 800, 1);
       // ====== BACKGROUND SETUP ======
       bgImage = new GreenfootImage("istockphoto-1403514917-612x612 (1).jpg");
       drawScrollingBackground();
       // ====== OBJECTS SETUP ======
       addObject(new UFO(), 150, getHeight() / 2);

       // Optional: seed some asteroids initially
       for (int i = 0; i < 5; i++) {
           int x = getWidth() + Greenfoot.getRandomNumber(spawnOffsetRight + 200); // slightly random
           int y = Greenfoot.getRandomNumber(getHeight());
           addObject(new asteroid(), x, y);
       }

       // Initialize spawn timer
       lastSpawnTime = System.currentTimeMillis();

       updateDistanceText();
   }

   public void act() {
       if (isGameOver) return;

       scrollBackground();

       if (!backgroundMusic.isPlaying()) {
           backgroundMusic.playLoop(); // Play the music in a loop
       }

       // Option A: distance increases as the world scrolls
       distancePx += scrollSpeed;
       updateDistanceText();

       // ===== Spawn asteroid every second (NEW) =====
       spawnAsteroidsOnInterval();
   }

   // ===== NEW: Spawning helper =====
   private void spawnAsteroidsOnInterval() {
       long now = System.currentTimeMillis();
       if (now - lastSpawnTime >= spawnIntervalMs) {
           // X: off-screen to the right so it scrolls in; Y: random within world
           int x = getWidth() + Greenfoot.getRandomNumber(spawnOffsetRight);
           int y = Greenfoot.getRandomNumber(getHeight());
           addObject(new asteroid(), x, y);
           lastSpawnTime = now;
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
}
