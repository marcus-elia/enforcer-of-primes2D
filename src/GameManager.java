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

    private Enforcer enforcer;

    // Which direction the user is holding the wasd keys
    private KeyDirection currentKeyDirection;

    public GameManager(int inputWidth, int inputHeight)
    {
        width = inputWidth;
        height = inputHeight;
        currentMode = GameMode.Playing;
        gameObjects = new ArrayList<GameObject>();
        lineWidth = 10;
        this.initializeEnforcer();
        this.initializeBorders();
        currentKeyDirection = KeyDirection.None;
    }

    public void tick()
    {
        if(currentMode == GameMode.Playing)
        {
            this.moveBasedOnKeyInput();
            for(GameObject obj : gameObjects)
            {
                obj.tick();
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
        overlap = enforcer.getCenter().y - bottomBorder + enforcer.getRadius() + lineWidth/2.0;
        if(overlap > 0)
        {
            enforcer.moveUp(overlap);
        }
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
        overlap = topBorder + enforcer.getRadius() - enforcer.getCenter().y + lineWidth/2.0;
        if(overlap > 0)
        {
            enforcer.moveDown(overlap);
        }
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
        overlap = leftBorder + enforcer.getRadius() - enforcer.getCenter().x + lineWidth/2.0;
        if(overlap > 0)
        {
            enforcer.moveRight(overlap);
        }
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
        overlap = enforcer.getCenter().x - rightBorder + enforcer.getRadius() + lineWidth/2.0;
        if(overlap > 0)
        {
            enforcer.moveLeft(overlap);
        }
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
}
