import java.awt.*;
import java.util.ArrayList;

public abstract class ActiveCircle extends MovableCircle
{
    protected ArrayList<Projectile> bullets;

    public ActiveCircle(GameManager inputManager, Point inputCenter, double inputRadius, double inputAngle,
                        double inputCurSpeed, double inputMaxSpeed, double inputAcceleration)
    {
        super(inputManager, inputCenter, inputRadius, inputAngle, inputCurSpeed, inputMaxSpeed, inputAcceleration);
    }

    @Override
    public abstract void tick();

    @Override
    public abstract void render(Graphics2D g2d);

    public ArrayList<Projectile> getBullets()
    {
        return bullets;
    }

    @Override
    public abstract void removeSelf();
}
