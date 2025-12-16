import greenfoot.*;

public class UFO extends Actor {
    private double x, y;
    private double vx = 0, vy = 0;

    private double accel = 0.4;
    private double friction = 0.15;
    private double maxSpeed = 5.0;

    // --- NEW: Cooldown system ---
    private long lastShotTime = 0;        // time of last laser shot
    private long cooldown = 2000;         // 2000 ms = 2 seconds
    private boolean isCoolingDown = false;

    // --- NEW: Indicator image for cooldown ---
    private GreenfootImage normalImg;
    private GreenfootImage cooldownImg;

    public UFO() {
        
        normalImg   = new GreenfootImage("ufo.png");
        cooldownImg = new GreenfootImage("ufo_red.png");

       
        int scaledW = normalImg.getWidth() / 8;
        int scaledH = normalImg.getHeight() / 8;
        normalImg.scale(scaledW, scaledH);
        cooldownImg.scale(scaledW, scaledH);

        setImage(normalImg);

    }

    @Override
    protected void addedToWorld(World world) {
        x = getX();
        y = getY();
    }

    public void act() {
        handleInput();
        shootLaser();
        updateCooldownIndicator();

        applyFrictionWhenNoInput();
        clampSpeed();
        updatePosition();
        clampToWorldBounds();

        setLocation((int)x, (int)y);
    }

    // ------------------------------------------------------
    // SHOOT LASER WITH COOLDOWN
    // ------------------------------------------------------
    private void shootLaser() {
        long now = System.currentTimeMillis();

        // Check for cooldown
        if (now - lastShotTime < cooldown) {
            isCoolingDown = true;
            return;
        }

        // Only shoot if SPACE is pressed
        if (Greenfoot.isKeyDown("space")) {
            isCoolingDown = true;              // enter cooldown
            lastShotTime = now;                // timestamp
            getWorld().addObject(new Laser(), getX(), getY() - getImage().getHeight() / 2);
        }
    }

    // ------------------------------------------------------
    // UPDATE UFO LOOK DURING COOLDOWN
    // ------------------------------------------------------
    private void updateCooldownIndicator() {
        long now = System.currentTimeMillis();

        if (now - lastShotTime >= cooldown) {
            isCoolingDown = false;
            setImage(normalImg);       // restore normal
        } else {
            setImage(cooldownImg);     // show red border
        }
    }

    // ------------------------------------------------------
    // MOVEMENT (unchanged)
    // ------------------------------------------------------
    private void handleInput() {
        boolean left  = Greenfoot.isKeyDown("left");
        boolean right = Greenfoot.isKeyDown("right");
        boolean up    = Greenfoot.isKeyDown("up");
        boolean down  = Greenfoot.isKeyDown("down");

        if (left && !right) vx -= accel;
        else if (right && !left) vx += accel;

        if (up && !down) vy -= accel;
        else if (down && !up) vy += accel;
    }

    private void applyFrictionWhenNoInput() {
        boolean left  = Greenfoot.isKeyDown("left");
        boolean right = Greenfoot.isKeyDown("right");
        boolean up    = Greenfoot.isKeyDown("up");
        boolean down  = Greenfoot.isKeyDown("down");

        if (!left && !right) vx = applyFriction(vx, friction);
        if (!up && !down) vy = applyFriction(vy, friction);
    }

    private double applyFriction(double v, double f) {
        if (v > 0) v = Math.max(0, v - f);
        else if (v < 0) v = Math.min(0, v + f);
        return v;
    }

    private void clampSpeed() {
        if (vx >  maxSpeed) vx =  maxSpeed;
        if (vx < -maxSpeed) vx = -maxSpeed;
        if (vy >  maxSpeed) vy =  maxSpeed;
        if (vy < -maxSpeed) vy = -maxSpeed;
    }

    private void updatePosition() {
        x += vx;
        y += vy;
    }

    private void clampToWorldBounds() {
        World w = getWorld();
        int width = w.getWidth();
        int height = w.getHeight();

        if (x < 0) { x = 0; vx = 0; }
        else if (x > width - 1) { x = width - 1; vx = 0; }

        if (y < 0) { y = 0; vy = 0; }
        else if (y > height - 1) { y = height - 1; vy = 0; }
    }
}

