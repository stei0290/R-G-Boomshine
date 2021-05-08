package edu.pacificu.cs.group7boomshine.circles;

/**
 * MovingCircle - Class for a Moving Circle
 *
 * @author GarretKatayama
 */
public class MovingCircle extends Circle
{
    private float mXRate;
    private float mYRate;

    /**
     * MovingCircle - MovingCircle constructor
     *
     * @param xCoordinate - X coordinate to set center to
     * @param yCoordinate - Y coordinate to set center to
     * @param radius      - Radius of Circle
     * @param color       - Color of Circle
     */
    public MovingCircle (float xCoordinate, float yCoordinate, float radius,
                         int color)
    {
        super (xCoordinate, yCoordinate, radius, color);

        mXRate = 0;
        mYRate = 0;
    }

    /**
     * setXRate - Sets rate of movement on X axis
     *
     * @param xRate - Rate of X movement
     */
    public void setXRate (float xRate)
    {
        mXRate = xRate;
    }

    /**
     * setYRate - Rate of movement on Y axis
     *
     * @param yRate - Rate of Y movement
     */
    public void setYRate (float yRate)
    {
        mYRate = yRate;
    }

    /**
     * getYRate - Returns Y rate of movement
     *
     * @return mYRate  - Y rate of movement
     */
    public final float getYRate ()
    {
        return mYRate;
    }

    /**
     * getXRate - Returns X rate of movement
     *
     * @return mXRate  - X rate of movement
     */
    public final float getXRate ()
    {
        return mXRate;
    }

    /**
     * reflectX - Reflects direction of movement on X axis
     */
    public void reflectX ()
    {
        mXRate = -mXRate;
    }

    /**
     * reflectY - Reflects direction of Y movement
     */
    public void reflectY ()
    {
        mYRate = -mYRate;
    }

    /**
     * move - Moves circle across screen
     */
    public void move ()
    {
        super.setXCoordinate (super.getXCoordinate () + mXRate);
        super.setYCoordinate (super.getYCoordinate () + mYRate);
    }
}
