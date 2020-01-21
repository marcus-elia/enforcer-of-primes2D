import java.awt.*;

public abstract class GameObject
{
    public GameObject(){};

    public abstract void tick();
    public abstract void render(Graphics2D g2d);

    public abstract boolean getNeedsToBeRemoved();

    public abstract void moveDown(double amount);
    public abstract void moveUp(double amount);
    public abstract void moveLeft(double amount);
    public abstract void moveRight(double amount);

    public abstract void removeSelf();
}
