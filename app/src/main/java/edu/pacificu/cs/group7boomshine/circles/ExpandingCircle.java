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
    super.setRadius (super.getRadius () + mExpansionRate);
  }

}
