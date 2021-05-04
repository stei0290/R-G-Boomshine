package edu.pacificu.cs.group7boomshine.circles;

/**
 * ExpandingCircle  - Circle with an expanding radius
 */
public class ExpandingCircle extends Circle
{
  private float mExpansionRate;

  /**
   * ExpandingCircle  - Constructor for ExpandingCircle
   * @param xCoordinate - X coordinate for circle
   * @param yCoordinate - Y coordinate for circle
   * @param radius  - Radius for circle
   * @param color - Color of circle
   * @param expansionRate - Rate of radius expansion
   */
  public ExpandingCircle (float xCoordinate, float yCoordinate, float radius, int color, float expansionRate)
  {
    super (xCoordinate, yCoordinate, radius, color);

    mExpansionRate = expansionRate;
  }

  /**
   * setExpansionRate - Sets expansion rate
   * @param expansionRate - Rate of expansion
   */
  public void setExpansionRate (float expansionRate)
  {
    mExpansionRate = expansionRate;
  }

  /**
   * Expand - Expands circles' radius
   */
  public void expand ()
  {
    float newRadius = super.getRadius () + mExpansionRate;

    if (0 <= newRadius)
    {
      super.setRadius (newRadius);
    }
    else
    {
      super.setRadius (0);
    }
  }

  /**
   * isContracted - Determines if Circle has a radius of 0
   * @return  true if radius is 0
   */
  public boolean isContracted ()
  {
    boolean bIsContracted = false;

    if (0 == super.getRadius ())
    {
      bIsContracted = true;
    }

    return bIsContracted;
  }
}
