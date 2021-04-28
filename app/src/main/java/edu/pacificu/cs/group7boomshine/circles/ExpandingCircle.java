package edu.pacificu.cs.group7boomshine.circles;

import android.graphics.Color;

public class ExpandingCircle extends Circle
{
  private float mExpansionRate;

  public ExpandingCircle (float xCoordinate, float yCoordinate, float radius, Color color, float expansionRate)
  {
    super (xCoordinate, yCoordinate, radius, color);

    mExpansionRate = expansionRate;
  }

  public void setExpansionRate (float expansionRate)
  {
    mExpansionRate = expansionRate;
  }

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
