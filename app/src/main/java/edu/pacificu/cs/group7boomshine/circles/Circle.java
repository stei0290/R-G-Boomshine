package edu.pacificu.cs.group7boomshine.circles;

/**
 * Circle - Circle class
 */
public class Circle
{
  private float mXCoordinate;
  private float mYCoordinate;
  private float mRadius;
  private final int mColor;

  /**
   * Circle - Constructor for Circle
   * @param xCoordinate - X Coordinate for center of circle
   * @param yCoordinate
   * @param radius
   * @param color
   */
  public Circle (float xCoordinate, float yCoordinate, float radius, int color)
  {
    mXCoordinate = xCoordinate;
    mYCoordinate = yCoordinate;
    mRadius = radius;
    mColor = color;
  }

  /**
   * Cirlce - Copy constructor for circle
   * @param otherCircle - Circle to be copied
   */
  public Circle (Circle otherCircle)
  {
    this.mXCoordinate = otherCircle.mXCoordinate;
    this.mYCoordinate = otherCircle.mYCoordinate;
    this.mRadius = otherCircle.mRadius;
    this.mColor = otherCircle.mColor;
  }

  /**
   * getXCoordinate - Returns x coordinate
   * @return  mXCoordinate  - X coordinate of circle
   */
  public final float getXCoordinate ()
  {
    return mXCoordinate;
  }

  /**
   * getYCoordinate - Returns Y coordinate
   * @return  mYCoordiante  - Y coordinate of circle
   */
  public final float getYCoordinate ()
  {
    return mYCoordinate;
  }

  /**
   * getRadius  - Returns radius of circle
   * @return  mRadius - Radius of circle
   */
  public final float getRadius ()
  {
    return mRadius;
  }

  /**
   * getColor - Returns color of circle
   * @return  mColor  - Color of circle
   */
  public final int getColor ()
  {
    return mColor;
  }

  /**
   * setXCoordinate - sets X coordinate of circle
   * @param xCoordinate - X coordinate to set center to
   */
  public void setXCoordinate (float xCoordinate)
  {
    mXCoordinate = xCoordinate;
  }

  /**
   * setYCoordinate - Sets Y coordinate of circle
   * @param yCoordinate - Y coordinate to set center to
   */
  public void setYCoordinate (float yCoordinate)
  {
    mYCoordinate = yCoordinate;
  }

  /**
   * setRadius  - Sets circles radius
   * @param radius  - Radius to be set
   */
  public void setRadius (float radius)
  {
    mRadius = radius;
  }

  /**
   * isCollided - Dewterimines if two circles have collided
   * @param otherCircle - Circle to be checked if collided
   * @return  bIsCollided - True if circles have collided
   */
  public boolean isCollided (Circle otherCircle)
  {
    boolean bIsCollided = false;

    float collisionDistance = this.getRadius () + otherCircle.getRadius ();
    float xDistance = this.getXCoordinate () - otherCircle.getXCoordinate ();
    float yDistance = this.getYCoordinate () - otherCircle.getYCoordinate ();
    float distance = (float) Math.sqrt ((xDistance * xDistance) + (yDistance * yDistance));

    if (distance <= collisionDistance)
    {
      bIsCollided = true;
    }
    return bIsCollided;
  }
}
