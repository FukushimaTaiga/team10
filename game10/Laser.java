import greenfoot.*;

public class Laser extends Actor {
    private double x, y;
    private int speed = 10;

    public Laser() {
        // create a tall vertical laser
        GreenfootImage img = new GreenfootImage(4, 16);
        img.setColor(Color.RED);
        img.fillRect(0, 0, 4, 16);
        setImage(img);

        // ensures the image faces UP
        setRotation(270);   // 270 = facing upward in Greenfoot
    }

    @Override
    protected void addedToWorld(World world) {
        x = getX();
        y = getY();
    }

    public void act() {
        moveLeft();
        checkCollision();
        checkOutOfBounds();
    }

    private void moveLeft() {
        x += speed;
        setLocation((int)x, (int)y);
    }

    private void checkCollision() {
        // CHANGE THIS to match your actual asteroid class name
        Actor asteroid = getOneIntersectingObject(asteroid.class);

        if (asteroid != null) {
            getWorld().removeObject(asteroid);
            getWorld().removeObject(this);
        }
    }

    private void checkOutOfBounds() {
        World w = getWorld();
        if (w == null) return; // already removed

        if (x >1200) {
            w.removeObject(this);
        }
    }
}

