import java.awt.*;

public class Enforcer extends MovableCircle
{
    private KeyDirection movementDirection;
    private int edgeCushion;  // How close the player has to be to the wall to scroll instead of moving.

    public Enforcer(GameManager inputManager,
                    Point inputCenter, double inputRadius, double inputAngle, double inputCurSpeed,
                    double inputMaxSpeed, double inputAcceleration,
                    int inputEdgeCushion)
    {
        super(inputManager, inputCenter, inputRadius, inputAngle, inputCurSpeed, inputMaxSpeed,
                inputAcceleration);
        movementDirection = KeyDirection.None;
        edgeCushion = inputEdgeCushion;
    }

    @Override
    public void tick() {

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
}
