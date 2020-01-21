import java.awt.*;
import java.util.ArrayList;

public class GameManager
{
    private int width, height;

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
        this.initializeEnforcer();
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
            for(GameObject obj : gameObjects)
            {
                obj.render(g2d);
            }
        }
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
                32, 0, 0, 2, .1, 96);
        gameObjects.add(enforcer);
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
            for(int i = 1; i < gameObjects.size(); i++)
            {
                gameObjects.get(i).moveUp(amount);
            }
        }
    }

    public void moveEnforcerUp(double amount)
    {
        enforcer.moveUp(amount);
        double overlap = enforcer.checkAndCorrectTop();

        // If the player tries to move too far up, scroll everything else down
        if(overlap > 0)
        {
            for(int i = 1; i < gameObjects.size(); i++)
            {
                gameObjects.get(i).moveDown(amount);
            }
        }
    }

    public void moveEnforcerLeft(double amount)
    {
        enforcer.moveLeft(amount);
        double overlap = enforcer.checkAndCorrectLeft();

        // If the player tries to move too far left, scroll everything else right
        if(overlap > 0)
        {
            for(int i = 1; i < gameObjects.size(); i++)
            {
                gameObjects.get(i).moveRight(amount);
            }
        }
    }

    public void moveEnforcerRight(double amount)
    {
        enforcer.moveRight(amount);
        double overlap = enforcer.checkAndCorrectRight();

        // If the player tries to move too far right, scroll everything else left
        if(overlap > 0)
        {
            for(int i = 1; i < gameObjects.size(); i++)
            {
                gameObjects.get(i).moveLeft(amount);
            }
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
