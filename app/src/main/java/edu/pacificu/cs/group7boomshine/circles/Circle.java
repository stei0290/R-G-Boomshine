package edu.pacificu.cs.group7boomshine.circles;

import edu.pacificu.cs.group7boomshine.Shape;

/**
 * Circle - Circle class
 *
 * @author GarretKatayama
 */
public class Circle extends Shape
{
    private float mRadius;

    /**
     * Circle - Constructor for Circle
     *
     * @param xCoordinate - X Coordinate for center of Circle
     * @param yCoordinate - Y coordinate for center of Circle
     * @param radius      - Radius of circle
     * @param color       - ARBG value of color
     */
    public Circle (float xCoordinate, float yCoordinate, float radius,
                   int color)
    {
        super (xCoordinate, yCoordinate, color);
        mRadius = radius;
    }

// --Commented out by Roman unused function
//  /**
//   * Circe - Copy constructor for circle
//   * @param otherCircle - Circle to be copied
//   */
//  public Circle (Circle otherCircle)
//  {
//    super.setXCoordinate (otherCircle.getXCoordinate ());
//    super.setYCoordinate (otherCircle.getYCoordinate ());
//    this.mRadius = otherCircle.mRadius;
//    super.setColor (otherCircle.getColor ());
//  }
// --Commented out by Roman


    /**
     * getRadius  - Returns radius of circle
     *
     * @return mRadius - Radius of circle
     */
    public final float getRadius ()
    {
        return mRadius;
    }

    /**
     * setXCoordinate - sets X coordinate of circle
     *
     * @param xCoordinate - X coordinate to set shape to
     */
    public void setXCoordinate (float xCoordinate)
    {
        super.setXCoordinate (xCoordinate);
    }

    /**
     * setXYCoordinate - sets Y coordinate of circle
     *
     * @param yCoordinate - Y coordinate to set shape to
     */
    public void setYCoordinate (float yCoordinate)
    {
        super.setYCoordinate (yCoordinate);
    }

    /**
     * setRadius  - Sets circles radius
     *
     * @param radius - Radius to be set
     */
    public void setRadius (float radius)
    {
        mRadius = radius;
    }

    /**
     * isCollided - Determines if two circles have collided
     *
     * @param otherCircle - Circle to be checked if collided
     * @return bIsCollided - True if circles have collided
     */
    public boolean isCollided (Circle otherCircle)
    {
        boolean bIsCollided = false;

        float collisionDistance = this.getRadius () + otherCircle.getRadius ();
        float xDistance = super.getXCoordinate () -
                otherCircle.getXCoordinate ();
        float yDistance = super.getYCoordinate () -
                otherCircle.getYCoordinate ();
        float distance = (float) Math.sqrt ((xDistance * xDistance) +
                (yDistance * yDistance));

        if (distance <= collisionDistance)
        {
            bIsCollided = true;
        }
        return bIsCollided;
    }
}
