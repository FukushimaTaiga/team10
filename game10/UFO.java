
import greenfoot.*;  // World, Actor, GreenfootImage, Greenfoot

public class UFO extends Actor {
    // Precise positions and velocities (double for smooth movement)
    private double x, y;
    private double vx = 0.0, vy = 0.0;

    // Tunable movement parameters
    private double accel = 0.4;      // acceleration per frame while key is held
    private double friction = 0.15;  // deceleration per frame when key is released
    private double maxSpeed = 5.0;   // cap on speed in any direction

    public UFO() {
        // Scale image once (adjust to taste)
        GreenfootImage img = getImage();
        img.scale(img.getWidth() / 8, img.getHeight() / 8); // 50% size
        setImage(img);
    }

    @Override
    protected void addedToWorld(World world) {
        // Initialize precise position to current location
        x = getX();
        y = getY();
    }

    public void act() {
        handleInput();
        applyFrictionWhenNoInput();
        clampSpeed();
        updatePosition();
        clampToWorldBounds(); // or swap to wrap-around, see comment
        setLocation((int)Math.round(x), (int)Math.round(y));
    }

    /** Read keys and apply acceleration (no rotation). */
    private void handleInput() {
        boolean left  = Greenfoot.isKeyDown("left");
        boolean right = Greenfoot.isKeyDown("right");
        boolean up    = Greenfoot.isKeyDown("up");
        boolean down  = Greenfoot.isKeyDown("down");

        // Horizontal acceleration
        if (left && !right) {
            vx -= accel;
        } else if (right && !left) {
            vx += accel;
        }

        // Vertical acceleration
        if (up && !down) {
            vy -= accel; // up is negative Y in screen coords
        } else if (down && !up) {
            vy += accel;
        }
    }

    /** Apply friction on each axis only if no key is pressed for that axis. */
    private void applyFrictionWhenNoInput() {
        boolean left  = Greenfoot.isKeyDown("left");
        boolean right = Greenfoot.isKeyDown("right");
        boolean up    = Greenfoot.isKeyDown("up");
        boolean down  = Greenfoot.isKeyDown("down");

        // Horizontal friction (if neither left nor right is held)
        if (!left && !right) {
            vx = applyFriction(vx, friction);
        }

        // Vertical friction (if neither up nor down is held)
        if (!up && !down) {
            vy = applyFriction(vy, friction);
        }
    }

    /** Friction helper: nudges velocity toward zero by a fixed amount. */
    private double applyFriction(double v, double f) {
        if (v > 0) {
            v = Math.max(0, v - f);
        } else if (v < 0) {
            v = Math.min(0, v + f);
        }
        return v;
    }

    /** Prevent speed from exceeding maxSpeed in either axis. */
    private void clampSpeed() {
        if (vx >  maxSpeed) vx =  maxSpeed;
        if (vx < -maxSpeed) vx = -maxSpeed;
        if (vy >  maxSpeed) vy =  maxSpeed;
        if (vy < -maxSpeed) vy = -maxSpeed;
    }

    /** Integrate velocity into position. */
    private void updatePosition() {
        x += vx;
        y += vy;
    }

    /** Keep the UFO inside the world (top-left inclusive, bottom-right inclusive). */
    private void clampToWorldBounds() {
        World w = getWorld();
        int width = w.getWidth();
        int height = w.getHeight();

        // Clamp X
        if (x < 0) {
            x = 0;
            vx = 0; // stop on wall; remove if you want to slide
        } else if (x > width - 1) {
            x = width - 1;
            vx = 0;
        }

        // Clamp Y
        if (y < 0) {
            y = 0;
            vy = 0;
        } else if (y > height - 1) {
            y = height - 1;
            vy = 0;
        }

        /* Alternative: wrap-around edges (like Asteroids)
        if (x < 0) x = width - 1;
        if (x > width - 1) x = 0;
        if (y < 0) y = height - 1;
        if (y > height - 1) y = 0;
        */
    }
}

