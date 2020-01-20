import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public abstract class MovableCircle
{
    protected Point center;
    protected double radius;
    protected Shape circle;
    protected double angle;
    protected double curSpeed;
    protected double maxSpeed;
    protected double acceleration;
    protected double vx;
    protected double vy;

    public MovableCircle(Point inputCenter, double inputRadius, double inputAngle, double inputCurSpeed,
                         double inputMaxSpeed, double inputAcceleration, double inputVx, double inputVy)
    {
        center = inputCenter;
        radius = inputRadius;
        circle = new Ellipse2D.Double(center.x - radius, center.y - radius, 2*radius, 2*radius);
        angle = inputAngle;
        curSpeed = inputCurSpeed;
        maxSpeed = inputMaxSpeed;
        acceleration = inputAcceleration;
        this.updateVxVy();
    }

    public abstract void tick();
    public abstract void render(Graphics2D g2d);



    // ------------------------------------------
    // ==========================================
    //
    //                 Getters
    //
    // ==========================================
    // ------------------------------------------

    public Point getCenter()
    {
        return center;
    }
    public double getRadius()
    {
        return radius;
    }
    public Shape getCircle()
    {
        return circle;
    }
    public double getAngle()
    {
        return angle;
    }
    public double getCurSpeed()
    {
        return curSpeed;
    }
    public double getMaxSpeed()
    {
        return maxSpeed;
    }
    public double getAcceleration()
    {
        return acceleration;
    }
    public double getVx()
    {
        return vx;
    }
    public double getVy()
    {
        return vy;
    }

    // ------------------------------------------
    // ==========================================
    //
    //                 Setters
    //
    // ==========================================
    // ------------------------------------------
    public void setAngle(double input)
    {
        angle = input;
    }
    public void setCurSpeed(double input)
    {
        curSpeed = input;
    }
    public void setMaxSpeed(double input)
    {
        maxSpeed = input;
    }
    public void setAcceleration(double input)
    {
        acceleration = input;
    }

    // ------------------------------------------
    // ==========================================
    //
    //            Movement Functions
    //
    // ==========================================
    // ------------------------------------------

    // Given a current speed and an angle, update the velocity
    // components to be consistent with that
    public void updateVxVy()
    {
        vx = curSpeed * Math.cos(angle);
        vy = curSpeed * Math.sin(angle);
    }

    // Move the center of the circle based on the x and y
    // components of velocity
    // Helper function for move()
    public void moveCenter()
    {
        center.x += vx;
        center.y += vy;
    }

    // Move the circle object based on the x and y
    // components of velocity
    // Helper function for move()
    public void moveCircle()
    {
        AffineTransform move = new AffineTransform();
        move.translate(vx, vy);
        circle = move.createTransformedShape(circle);
    }

    public void move()
    {
        this.moveCenter();
        this.moveCircle();
    }



    // If we are not at maxSpeed, accelerate to get closer to it
    public void accelerate()
    {
        if(maxSpeed - curSpeed <= acceleration)
        {
            curSpeed = maxSpeed;
        }
        else
        {
            curSpeed = curSpeed + acceleration;
        }
    }


}
