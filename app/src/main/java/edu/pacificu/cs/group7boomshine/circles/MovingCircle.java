package edu.pacificu.cs.group7boomshine.circles;

import android.graphics.Color;

public class MovingCircle extends Circle
{
  private float mXRate;
  private float mYRate;

  public MovingCircle (float xCoordinate, float yCoordinate, float radius, Color color)
  {
    super (xCoordinate, yCoordinate, radius, color);

    mXRate = 0;
    mYRate = 0;
  }

  public void setXRate (float xRate)
  {
    mXRate = xRate;
  }

  public void setYRate (float yRate)
  {
    mYRate = yRate;
  }

  public void setSpeed (float newSpeed)
  {
    float oldSpeed = (float) Math.sqrt ((mXRate * mXRate) + (mYRate * mYRate));
    float speedMultiplier = newSpeed / oldSpeed;

    mXRate *= speedMultiplier;
    mYRate *= speedMultiplier;
  }

  public void reflectX ()
  {
    mXRate = -mXRate;
  }

  public void reflectY ()
  {
    mYRate = -mYRate;
  }

  public void move ()
  {
    super.setXCoordinate (super.getXCoordinate () + mXRate);
    super.setYCoordinate (super.getYCoordinate () + mYRate);
  }
}
