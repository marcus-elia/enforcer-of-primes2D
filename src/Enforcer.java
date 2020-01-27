import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
The Enforcer is the object controlled by the player. It stores a HashMap sending each
prime less than 999 to a boolean, to know whether we have "enforced" the prime or not.
When we touch a prime number, that prime is now enforced. Once we have enforced a prime,
any time we shoot a composite number, the number will be divided by that prime. If the player
shoots an unenforced prime, nothing happens. If a composite number hits the player, the
player loses health.
 */

public class Enforcer extends ActiveCircle
{
    private KeyDirection movementDirection;
    private int edgeCushion;  // How close the player has to be to the wall to scroll instead of moving.

    // Shooting
    private int timeSinceLastShot;
    private int cooldown;

    // Prime management
    private HashMap<Integer, Boolean> isPrimeKnown;
    private int score;
    private int health;

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
        this.createIsPrimeKnown();
        score = 0;
        health = 100;
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
    //         Initialization Functions
    //
    // ==========================================
    // ------------------------------------------
    public void createIsPrimeKnown()
    {
        isPrimeKnown = new HashMap<Integer, Boolean>();
        isPrimeKnown.put(2, false);
        for(int n = 3; n < 1000; n++)
        {
            if(Number.isPrime(n))
            {
                isPrimeKnown.put(n, false);
            }
        }
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
    public HashMap<Integer, Boolean> getIsPrimeKnown()
    {
        return isPrimeKnown;
    }
    public int getScore()
    {
        return score;
    }
    public int getHealth()
    {
        return health;
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
    public void setPrimeAsKnown(Integer p)
    {
        isPrimeKnown.replace(p, true);
    }
    public void setScore(int input)
    {
        score = input;
    }
    public void setHealth(int input)
    {
        if(health < 0)
        {
            health = 0;
        }
        health = input;
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
        if(timeSinceLastShot == cooldown)
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
        Point clickPoint = new Point(mx, my);
        double theta = center.angleToOtherPoint(clickPoint);
        double distanceAway = radius + 5 + 1;
        Projectile p = new Projectile(manager, center.getPointFromHere(distanceAway, theta), 5, theta,
                4, 0, 0, Color.white, this, true);
        bullets.add(p);
        manager.addGameObject(p);
    }

    public void checkForCollisionsWithNumbers()
    {
        for(Number num : manager.getNumbers())
        {
            if(MovableCircle.areColliding(this, num))
            {
                if(num.getIsPrime())
                {
                    // Set the isPrimeKnown for this prime to be true. If it already
                    // was true, then add that much to our score.
                    if(isPrimeKnown.put(num.getNumber(), true))
                    {
                        score += num.getNumber();
                    }
                    else
                    {
                        System.out.println("New Prime Found: " + num.getNumber());
                    }
                    num.setNeedsToBeRemoved(true);
                }
                // If we crash into a composite number, take damage
                else
                {
                    this.setHealth(health - 1);
                    System.out.println("Health: " + health);
                }
            }
        }
    }
}
