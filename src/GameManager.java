import java.awt.*;
import java.util.ArrayList;

public class GameManager
{
    // The visible window dimensions
    private int width, height;

    // The absolute boundaries of the level
    private int leftBorder, topBorder, rightBorder, bottomBorder;
    private int lineWidth;

    private GameMode currentMode;

    private ArrayList<GameObject> gameObjects;
    private ArrayList<Number> numbers;
    private ArrayList<Projectile> bullets;

    private Enforcer enforcer;

    // Which direction the user is holding the wasd keys
    private KeyDirection currentKeyDirection;


    private Point clickToReactTo;  // if a click has happened, react to it in the next frame

    public GameManager(int inputWidth, int inputHeight)
    {
        width = inputWidth;
        height = inputHeight;
        currentMode = GameMode.Playing;

        gameObjects = new ArrayList<GameObject>();
        numbers = new ArrayList<Number>();
        bullets = new ArrayList<Projectile>();
        lineWidth = 10;
        this.initializeEnforcer();
        this.initializeBorders();
        currentKeyDirection = KeyDirection.None;
        clickToReactTo = null;
        this.spawnRandomNumber();
    }

    public void tick()
    {
        if(currentMode == GameMode.Playing)
        {
            // Have the player move and things scroll
            this.moveBasedOnKeyInput();

            // Find objects that need to be removed
            ArrayList<GameObject> toRemove = new ArrayList<GameObject>();

            // Have every object update
            for(GameObject obj : gameObjects)
            {
                obj.tick();
                if(obj.getNeedsToBeRemoved()) // Check if it needs to be removed
                {
                    toRemove.add(obj);
                }
            }

            // Iterate through the objects that need to be removed
            for(GameObject obj : toRemove)
            {
                obj.removeSelf();
            }

            // If there was a click before this frame, have the enforcer create a bullet
            if(clickToReactTo != null)
            {
                enforcer.reactToClick((int)clickToReactTo.x, (int)clickToReactTo.y);
                clickToReactTo = null;
            }

            // Create new numbers if needed
            if(numbers.size() < 5)
            {
                this.spawnRandomNumber();
            }
        }
    }
    public void render(Graphics2D g2d)
    {
        if(currentMode == GameMode.Playing)
        {
            this.drawBorders(g2d);
            for(GameObject obj : gameObjects)
            {
                obj.render(g2d);
            }
        }
    }

    // Draw the 4 borders of the level as white lines
    public void drawBorders(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(lineWidth));
        g2d.drawLine(leftBorder, topBorder, leftBorder, bottomBorder);
        g2d.drawLine(leftBorder, topBorder, rightBorder, topBorder);
        g2d.drawLine(rightBorder, topBorder, rightBorder, bottomBorder);
        g2d.drawLine(rightBorder, bottomBorder, leftBorder, bottomBorder);
    }

    // ------------------------------------------
    // ==========================================
    //
    //         Initialization Functions
    //
    // ==========================================
    // ------------------------------------------

    public void initializeEnforcer()
    {
        enforcer = new Enforcer(this, new Point(width / 2, height / 2),
                32, 0, 2, 2, .1, 96);
        gameObjects.add(enforcer);
    }
    public void initializeBorders()
    {
        leftBorder = -512;
        topBorder = -256;
        rightBorder = width + 512;
        bottomBorder = height + 256;
    }

    // ------------------------------------------
    // ==========================================
    //
    //              Game Management
    //
    // ==========================================
    // ------------------------------------------
    public void addGameObject(GameObject obj)
    {
        gameObjects.add(obj);
    }
    public void addBullet(Projectile proj)
    {
        bullets.add(proj);
        gameObjects.add(proj);
    }
    public void addNumber(Number num)
    {
        numbers.add(num);
        gameObjects.add(num);
    }

    // Returns true if the point p is not within radius of any current object
    public boolean isOpen(Point p, double radius)
    {
        for(Number num : numbers)
        {
            if(p.distanceToPoint(num.getCenter()) < radius + num.getRadius())
            {
                return false;
            }
        }
        for(Projectile proj : bullets)
        {
            if(p.distanceToPoint(proj.getCenter()) < radius + proj.getRadius())
            {
                return false;
            }
        }
        return p.distanceToPoint(enforcer.getCenter()) < radius + enforcer.getRadius();
    }

    public Point getRandomPointInBounds(int radius)
    {
        double x = Math.random() * (rightBorder - leftBorder - 2*radius) + leftBorder + radius;
        double y = Math.random() * (bottomBorder - topBorder - 2*radius) + topBorder + radius;
        return new Point(x, y);
    }

    public int getRandomNumber()
    {
        return (int)(Math.random()*997.9 + 2);
    }

    public void spawnRandomNumber()
    {
        int radius = 32;
        int numTries = 5;
        for(int i = 0; i < numTries; i++)
        {
            Point center = this.getRandomPointInBounds(radius);
            if(this.isOpen(center, radius))
            {
                this.addNumber(new Number(this, center, radius,
                        0, 0, 0, 0, this.getRandomNumber()));
                return;
            }
        }
    }


    // ------------------------------------------
    // ==========================================
    //
    //                Getters
    //
    // ==========================================
    // ------------------------------------------
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public GameMode getCurrentMode()
    {
        return currentMode;
    }
    public ArrayList<GameObject> getGameObjects()
    {
        return gameObjects;
    }
    public Enforcer getEnforcer()
    {
        return enforcer;
    }
    public ArrayList<Number> getNumbers()
    {
        return numbers;
    }
    public ArrayList<Projectile> getBullets()
    {
        return bullets;
    }
    public KeyDirection getCurrentKeyDirection()
    {
        return currentKeyDirection;
    }
    public int getLeftBorder()
    {
        return leftBorder;
    }
    public int getTopBorder()
    {
        return topBorder;
    }
    public int getRightBorder()
    {
        return rightBorder;
    }
    public int getBottomBorder()
    {
        return bottomBorder;
    }
    public int getLineWidth()
    {
        return lineWidth;
    }

    // ------------------------------------------
    // ==========================================
    //
    //                Setters
    //
    // ==========================================
    // ------------------------------------------
    public void setWidth(int input)
    {
        width = input;
    }
    public void setHeight(int input)
    {
        height = input;
    }
    public void setCurrentMode(GameMode input)
    {
        currentMode = input;
    }
    public void setCurrentKeyDirection(KeyDirection input)
    {
        currentKeyDirection = input;
    }
    public void setLineWidth(int input)
    {
        lineWidth = input;
    }



    // ------------------------------------------
    // ==========================================
    //
    //               User Input
    //
    // ==========================================
    // ------------------------------------------
    public void moveEnforcerDown(double amount)
    {
        enforcer.moveDown(amount);
        double overlap = enforcer.checkAndCorrectBottom();

        // If the player tries to move too far down, scroll everything else up
        if(overlap > 0)
        {
            bottomBorder -= amount;
            topBorder -= amount;
            for(int i = 1; i < gameObjects.size(); i++)
            {
                gameObjects.get(i).moveUp(amount);
            }
        }

        // Hitting the bottom border
        enforcer.checkAndCorrectBottomBorder();
    }

    public void moveEnforcerUp(double amount)
    {
        enforcer.moveUp(amount);
        double overlap = enforcer.checkAndCorrectTop();

        // If the player tries to move too far up, scroll everything else down
        if(overlap > 0)
        {
            bottomBorder += amount;
            topBorder += amount;
            for(int i = 1; i < gameObjects.size(); i++)
            {
                gameObjects.get(i).moveDown(amount);
            }
        }

        // Hitting the top border
        enforcer.checkAndCorrectTopBorder();
    }

    public void moveEnforcerLeft(double amount)
    {
        enforcer.moveLeft(amount);
        double overlap = enforcer.checkAndCorrectLeft();

        // If the player tries to move too far left, scroll everything else right
        if(overlap > 0)
        {
            leftBorder += amount;
            rightBorder += amount;
            for(int i = 1; i < gameObjects.size(); i++)
            {
                gameObjects.get(i).moveRight(amount);
            }
        }

        // Hitting the left border
        enforcer.checkAndCorrectLeftBorder();
    }

    public void moveEnforcerRight(double amount)
    {
        enforcer.moveRight(amount);
        double overlap = enforcer.checkAndCorrectRight();

        // If the player tries to move too far right, scroll everything else left
        if(overlap > 0)
        {
            leftBorder -= amount;
            rightBorder -= amount;
            for(int i = 1; i < gameObjects.size(); i++)
            {
                gameObjects.get(i).moveLeft(amount);
            }
        }

        // Hitting the right border
        enforcer.checkAndCorrectRightBorder();
    }


    public void moveBasedOnKeyInput()
    {
        if(currentKeyDirection == KeyDirection.None)
        {
            return;
        }
        else if(currentKeyDirection == KeyDirection.Up)
        {
            this.moveEnforcerUp(enforcer.getCurSpeed());
        }
        else if(currentKeyDirection == KeyDirection.Right)
        {
            this.moveEnforcerRight(enforcer.getCurSpeed());
        }
        else if(currentKeyDirection == KeyDirection.Down)
        {
            this.moveEnforcerDown(enforcer.getCurSpeed());
        }
        else if(currentKeyDirection == KeyDirection.Left)
        {
            this.moveEnforcerLeft(enforcer.getCurSpeed());
        }
        else
        {
            double diagSpeed = Math.sqrt(enforcer.getCurSpeed());
            if(currentKeyDirection == KeyDirection.UpRight)
            {
                this.moveEnforcerUp(diagSpeed);
                this.moveEnforcerRight(diagSpeed);
            }
            else if(currentKeyDirection == KeyDirection.DownRight)
            {
                this.moveEnforcerDown(diagSpeed);
                this.moveEnforcerRight(diagSpeed);
            }
            else if(currentKeyDirection == KeyDirection.DownLeft)
            {
                this.moveEnforcerDown(diagSpeed);
                this.moveEnforcerLeft(diagSpeed);
            }
            else if(currentKeyDirection == KeyDirection.UpLeft)
            {
                this.moveEnforcerUp(diagSpeed);
                this.moveEnforcerLeft(diagSpeed);
            }
        }
    }

    public void reactToClick(int mx, int my)
    {
        clickToReactTo = new Point(mx, my);
    }

    // For clicks. Returns true if the coordinates are inside the border,
    // and false if not
    public boolean isInBounds(int mx, int my)
    {
        return mx > leftBorder + lineWidth && mx < rightBorder - lineWidth &&
                my < bottomBorder - lineWidth && my > topBorder + lineWidth;
    }

    // Returns true if the given point is at least distance away from any edge
    public boolean isInBounds(Point p, double distance)
    {
        return p.x > leftBorder + lineWidth + distance &&
                p.x < rightBorder - lineWidth - distance &&
                p.y < bottomBorder - lineWidth - distance &&
                p.y > topBorder + lineWidth + distance;

    }
}
