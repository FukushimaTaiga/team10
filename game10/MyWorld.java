import greenfoot.*;  // World, Actor, GreenfootImage, Greenfoot
public class MyWorld extends World {
   private GreenfootImage bgImage;  // original background image
   private int bgX = 0;             // scroll position
   private int scrollSpeed = 2;     // pixels per frame (adjust to taste)
   // ===== Distance Score (Option A) =====
   private long distancePx = 0;     // distance traveled in pixels
   public MyWorld() {
       // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
       super(1200, 800, 1);
       // ====== BACKGROUND SETUP ======
       // Put your image (e.g. "space.png") into the images folder
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
           int y = C + (int)(Math.random() * ((D - C) + 1));  // fixed from A→C
           addObject(new asteroid(), x, y);
       }
       // Show initial score
       updateDistanceText();
   }
   public void act() {
       scrollBackground();
       // ===== Option A: distance increases as the world scrolls =====
       distancePx += scrollSpeed;
       updateDistanceText();
   }
   private void updateDistanceText() {
       // Convert pixels → meters (example: 10 px = 1 m)
       long meters = distancePx / 10;
       showText("Distance: " + meters + " m", 110, 30);
   }
   /** Move the background image left and redraw it tiled across the world. */
   private void scrollBackground() {
       bgX -= scrollSpeed;
       int imgW = bgImage.getWidth();
       // Loop position when one full image has scrolled past
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
       // Start drawing from bgX and tile until world width is covered
       int x = bgX;
       while (x < getWidth()) {
           bg.drawImage(bgImage, x, 0);
           x += imgW;
       }
   }
}

    
    


   
