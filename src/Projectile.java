import java.awt.*;

public class Projectile extends MovableCircle
{
    private Color color;
    private ActiveCircle owner;

    public Projectile(GameManager inputManager, Point inputCenter, double inputRadius, double inputAngle,
                      double inputCurSpeed, double inputMaxSpeed, double inputAcceleration,
                      Color inputColor, ActiveCircle inputOwner)
    {
        super(inputManager, inputCenter, inputRadius, inputAngle, inputCurSpeed, inputMaxSpeed, inputAcceleration);
        color = inputColor;
        owner = inputOwner;
    }

    @Override
    public void tick()
    {
        this.move();
    }

    @Override
    public void render(Graphics2D g2d)
    {
        g2d.setColor(color);
        g2d.fill(circle);
    }

    // ------------------------------------------
    // ==========================================
    //
    //                Getters
    //
    // ==========================================
    // ------------------------------------------
    public MovableCircle getOwner()
    {
        return owner;
    }



    // ------------------------------------------
    // ==========================================
    //
    //              Game Management
    //
    // ==========================================
    // ------------------------------------------

    public void removeSelf()
    {
        this.manager.getGameObjects().remove(this);
        this.owner.getBullets().remove(this);
    }
}
