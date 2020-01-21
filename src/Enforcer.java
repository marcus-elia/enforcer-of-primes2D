import java.awt.*;
import java.util.ArrayList;

public class Enforcer extends ActiveCircle
{
    private KeyDirection movementDirection;
    private int edgeCushion;  // How close the player has to be to the wall to scroll instead of moving.

    // Shooting
    private int timeSinceLastShot;
    private int cooldown;

    public Enforcer(GameManager inputManager,
                    Point inputCenter, double inputRadius, double inputAngle, double inputCurSpeed,
                    double inputMaxSpeed, double inputAcceleration,
                    int inputEdgeCushion)
    {
        super(inputManager, inputCenter, inputRadius, inputAngle, inputCurSpeed, inputMaxSpeed,
                inputAcceleration);
        movementDirection = KeyDirection.None;
        edgeCushion = inputEdgeCushion;
        timeSinceLastShot = 0;
        cooldown = 50;
        bullets = new ArrayList<Projectile>();
    }

    @Override
    public void tick()
    {
        if(timeSinceLastShot < cooldown)
        {
            timeSinceLastShot++;
        }
    }

    @Override
    public void render(Graphics2D g2d)
    {
        g2d.setColor(Color.BLUE);
        g2d.fill(circle);
    }

    // ------------------------------------------
    // ==========================================
    //
    //                 Getters
    //
    // ==========================================
    // ------------------------------------------

    public KeyDirection getMovementDirection()
    {
        return movementDirection;
    }
    public int getEdgeCushion()
    {
        return edgeCushion;
    }

    // ------------------------------------------
    // ==========================================
    //
    //                 Setters
    //
    // ==========================================
    // ------------------------------------------

    public void setMovementDirection(KeyDirection input)
    {
        movementDirection = input;
    }
    public void setEdgeCushion(int input)
    {
        edgeCushion = input;
    }
    public void setCooldown(int input)
    {
        cooldown = input;
    }


    // ------------------------------------------
    // ==========================================
    //
    //            Movement Functions
    //
    // ==========================================
    // ------------------------------------------


    // These functions check if the Enforcer is too close to an edge. If so, they move it
    // and return the amount it was moved.
    public double checkAndCorrectLeft()
    {
        double overlap = radius + edgeCushion - center.x;
        if(overlap > 0)
        {
            this.moveRight(overlap);
            return overlap;
        }
        return 0;
    }
    public double checkAndCorrectRight()
    {
        double overlap = center.x - (manager.getWidth() - radius - edgeCushion);
        if(overlap > 0)
        {
            this.moveLeft(overlap);
            return overlap;
        }
        return 0;
    }
    public double checkAndCorrectTop()
    {
        double overlap = radius + edgeCushion - center.y;
        if(overlap > 0)
        {
            this.moveDown(overlap);
            return overlap;
        }
        return 0;
    }
    public double checkAndCorrectBottom()
    {
        double overlap = center.y - (manager.getHeight() - radius - edgeCushion);
        if(overlap > 0)
        {
            this.moveUp(overlap);
            return overlap;
        }
        return 0;
    }

    // ------------------------------------------
    // ==========================================
    //
    //             Game Management
    //
    // ==========================================
    // ------------------------------------------
    public void removeSelf()
    {
        System.out.println("Why are you trying to remove the player?");
    }

    public void reactToClick(int mx, int my)
    {
        if(timeSinceLastShot < cooldown)
        {
            this.createBullet(mx, my);
            timeSinceLastShot = 0;
        }
    }

    public void createBullet(int mx, int my)
    {
        // If the click is too close to the player
        if(Point.distanceFormula(center.x, center.y, mx, my) < radius + 5 || !manager.isInBounds(mx, my))
        {
            return;
        }
        Point bulletCenter = new Point(mx, my);
        double theta = center.angleToOtherPoint(bulletCenter);
        bullets.add(new Projectile(manager, new Point(mx, my), 5, theta,
                4, 0, 0, Color.white, this ));
    }
}
