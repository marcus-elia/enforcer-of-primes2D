import java.awt.*;
import java.awt.geom.AffineTransform;

// The Number class is the enemy, a circle with a number on it

public class Number extends ActiveCircle
{
    private int number;
    private boolean isPrime;
    private int fontSize;  // changes based on the number of digits

    // Where it is trying to go
    private Point target;

    public Number(GameManager inputManager, Point inputCenter, double inputRadius, double inputAngle,
                  double inputCurSpeed, double inputMaxSpeed, double inputAcceleration,
                  int inputNumber)
    {
        super(inputManager, inputCenter, inputRadius, inputAngle, inputCurSpeed, inputMaxSpeed, inputAcceleration);
        number = inputNumber;
        fontSize = Number.getFontSize(number);
        this.setRandomTarget(3*radius);
    }

    @Override
    public void tick()
    {
        // If very close to the target, go right to it
        if(center.distanceToPoint(target) < curSpeed)
        {
            this.moveLeft(target.x - center.x);
            this.moveDown(target.y - center.y);
            this.setRandomTarget(3*radius);
        }
        // Otherwise, move
        else
        {
            this.move();
        }

        this.checkAndCorrectBorders();
    }


    // ------------------------------------------
    // ==========================================
    //
    //                Rendering
    //
    // ==========================================
    // ------------------------------------------

    @Override
    public void render(Graphics2D g2d)
    {
        g2d.setColor(Color.RED);
        g2d.fill(circle);
        this.drawNumber(g2d);
    }

    // Determine what the font size should be based on how many digits long the number is
    public static int getFontSize(int n)
    {
        if(n < 10)
        {
            return 32;
        }
        else if(n < 100)
        {
            return 24;
        }
        else if(n < 1000)
        {
            return 18;
        }
        return -1;
    }

    public void drawNumber(Graphics2D g2d)
    {
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Tahoma", Font.BOLD, fontSize));
        int pixelLength = g2d.getFontMetrics().stringWidth(Integer.toString(number));
        g2d.drawString(Integer.toString(number), (int)center.x - pixelLength/2, (int)center.y + fontSize/2 - 3);
    }



    // ------------------------------------------
    // ==========================================
    //
    //                 Getters
    //
    // ==========================================
    // ------------------------------------------
    public Point getTarget()
    {
        return target;
    }

    // ------------------------------------------
    // ==========================================
    //
    //                Setters
    //
    // ==========================================
    // ------------------------------------------
    public void setTarget(Point input)
    {
        target = input;
    }


    // ------------------------------------------
    // ==========================================
    //
    //                 Math
    //
    // ==========================================
    // ------------------------------------------

    public static boolean isPrime(int n)
    {
        if(n == 2 || n == 3)
        {
            return true;
        }
        if(n % 2 == 0)
        {
            return false;
        }
        int upperBound = (int)Math.floor(Math.sqrt(n)) + 1;
        for(int i = 3; i < upperBound; i += 2)
        {
            if(n % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    // Functions to move the object in a direction by a specified amount
    // Overriding these because the target needs to move
    public void moveDown(double amount)
    {
        center.y += amount;
        AffineTransform move = new AffineTransform();
        move.translate(0, amount);
        circle = move.createTransformedShape(circle);
        target.y += amount;
    }
    public void moveUp(double amount)
    {
        center.y -= amount;
        AffineTransform move = new AffineTransform();
        move.translate(0, -amount);
        circle = move.createTransformedShape(circle);
        target.y -= amount;
    }
    public void moveRight(double amount)
    {
        center.x += amount;
        AffineTransform move = new AffineTransform();
        move.translate(amount, 0);
        circle = move.createTransformedShape(circle);
        target.x += amount;
    }
    public void moveLeft(double amount)
    {
        center.x -= amount;
        AffineTransform move = new AffineTransform();
        move.translate(-amount, 0);
        circle = move.createTransformedShape(circle);
        target.x -= amount;
    }


    // ------------------------------------------
    // ==========================================
    //
    //                   AI
    //
    // ==========================================
    // ------------------------------------------

    public Point getRandomPoint(double distance)
    {
        double randomAngle = Math.random()*2*Math.PI;
        Point p = center.getPointFromHere(distance, randomAngle);
        while(!manager.isInBounds(p, radius))
        {
            randomAngle = Math.random()*2*Math.PI;
            p = center.getPointFromHere(distance, randomAngle);
        }
        return p;
    }

    // When the target changes, the angle has to change
    public void updateAngle()
    {
        angle = center.angleToOtherPoint(target);
        this.updateVxVy();
    }

    public void setRandomTarget(double distance)
    {
        this.setTarget(getRandomPoint(distance));
        this.updateAngle();
    }



    // ------------------------------------------
    // ==========================================
    //
    //              Game Management
    //
    // ==========================================
    // ------------------------------------------

    @Override
    public void removeSelf()
    {
        manager.getGameObjects().remove(this);
        manager.getNumbers().remove(this);
    }

    // When a projectile has hit this, react
    public void getHitByProjectile(Projectile proj)
    {
        if(proj.getIsPlayerBullet())
        {
            this.setNeedsToBeRemoved(true);
        }
        else
        {

        }
    }
}
