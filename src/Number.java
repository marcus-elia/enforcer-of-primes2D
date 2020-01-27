import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

// The Number class is the enemy, a circle with a number on it

public class Number extends ActiveCircle
{
    private PositiveInteger number;
    private boolean isPrime;
    private int fontSize;  // changes based on the number of digits

    // Where it is trying to go
    private Point target;

    private int health; // if you can't factor the number, shooting it enough will kill it
    private Color color;

    public Number(GameManager inputManager, Point inputCenter, double inputRadius, double inputAngle,
                  double inputCurSpeed, double inputMaxSpeed, double inputAcceleration,
                  int inputNumber)
    {
        super(inputManager, inputCenter, inputRadius, inputAngle, inputCurSpeed, inputMaxSpeed, inputAcceleration);
        number = new PositiveInteger(inputNumber);
        fontSize = Number.getFontSize(number.getValue());
        this.setRandomTarget(3*radius);
        isPrime = number.getIsPrime();
        health = 100;
        color = Color.RED;
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
        g2d.setColor(color);
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
        int pixelLength = g2d.getFontMetrics().stringWidth(number.toString());
        g2d.drawString(number.toString(), (int)center.x - pixelLength/2, (int)center.y + fontSize/2 - 3);
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
    public PositiveInteger getNumber()
    {
        return number;
    }
    public int getValue()
    {
        return number.getValue();
    }
    public boolean getIsPrime()
    {
        return isPrime;
    }
    public int getHealth()
    {
        return health;
    }
    public Color getColor()
    {
        return color;
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
    public void setIsPrime(boolean input)
    {
        isPrime = input;
    }
    public void setNumber(int input)
    {
        number.setValueAndUpdateFactorization(input);
    }
    public void setColor(Color newColor)
    {
        color = newColor;
    }
    public void setHealth(int input) // Reduce the alpha when health is lower
    {
        health = input;
        this.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(health / 100.0 * 256)));
    }




    // ------------------------------------------
    // ==========================================
    //
    //               Movement
    //
    // ==========================================
    // ------------------------------------------

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

    // Try to divide the PositiveInteger object by n. Returns true if division happened,
    // false if not.
    public boolean dividePositiveIntegerBy(int n)
    {
        boolean divided = number.divideBy(n);
        if(divided)
        {
            this.setIsPrime(number.getIsPrime());
            if(number.getValue() == 1)
            {
                this.setNeedsToBeRemoved(true);
            }
        }
        return divided;
    }

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
            manager.getEnforcer().reactToShootingNumber(this);
        }
        else
        {
            this.setNeedsToBeRemoved(true);
        }
    }
}
