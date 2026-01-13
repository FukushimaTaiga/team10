import greenfoot.*;

public class TitleWorld extends World
{
    public TitleWorld()
    {    
        super(1200, 800, 1);
        setBackground("Titlescreen (1).jpg");
    }

    public void act()
    {
        if (Greenfoot.isKeyDown("enter") || Greenfoot.mouseClicked(null))
        {
            Greenfoot.setWorld(new MyWorld());
        }
    }
}

