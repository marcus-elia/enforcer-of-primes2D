import java.awt.*;

public class Projectile extends MovableCircle
{
    private Color color;
    private ActiveCircle owner;
    private boolean isPlayerBullet;  // true = from Player, false = from AI Number

    public Projectile(GameManager inputManager, Point inputCenter, double inputRadius, double inputAngle,
                      double inputCurSpeed, double inputMaxSpeed, double inputAcceleration,
                      Color inputColor, ActiveCircle inputOwner, boolean inputIsPlayerBullet)
    {
        super(inputManager, inputCenter, inputRadius, inputAngle, inputCurSpeed, inputMaxSpeed, inputAcceleration);
        color = inputColor;
        owner = inputOwner;
        isPlayerBullet = inputIsPlayerBullet;
    }

    @Override
    public void tick()
    {
        this.move();
        this.checkBorders();
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
    public ActiveCircle getOwner()
    {
        return owner;
    }
    public boolean getIsPlayerBullet()
    {
        return isPlayerBullet;
    }

    // ------------------------------------------
    // ==========================================
    //
    //                Setters
    //
    // ==========================================
    // ------------------------------------------
    public void setOwner(ActiveCircle input)
    {
        owner = input;
    }
    public void setIsPlayerBullet(boolean input)
    {
        isPlayerBullet = input;
    }


    // ------------------------------------------
    // ==========================================
    //
    //                Movement
    //
    // ==========================================
    // ------------------------------------------

    // If the projectile has hit a border, remove it.
    public void checkBorders()
    {
        if(this.checkAndCorrectBottomBorder() || this.checkAndCorrectLeftBorder() ||
        this.checkAndCorrectTopBorder() || this.checkAndCorrectRightBorder())
        {
            this.setNeedsToBeRemoved(true);
        }
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
