package edu.pacificu.cs.group7boomshine.circles;

import android.graphics.Color;

public class Circle
{
  private float mXCoordinate;
  private float mYCoordinate;
  private float mRadius;
  private Color mColor;

  public Circle (float xCoordinate, float yCoordinate, float radius, Color color)
  {
    mXCoordinate = xCoordinate;
    mYCoordinate = yCoordinate;
    mRadius = radius;
    mColor = color;
  }

  public Circle (Circle otherCircle)
  {
    this.mXCoordinate = otherCircle.mXCoordinate;
    this.mYCoordinate = otherCircle.mYCoordinate;
    this.mRadius = otherCircle.mRadius;
    this.mColor = otherCircle.mColor;
  }

  public float getXCoordinate ()
  {
    return mXCoordinate;
  }

  public float getYCoordinate ()
  {
    return mYCoordinate;
  }

  public float getRadius ()
  {
    return mRadius;
  }

  public void setXCoordinate (float xCoordinate)
  {
    mXCoordinate = xCoordinate;
  }

  public void setYCoordinate (float yCoordinate)
  {
    mYCoordinate = yCoordinate;
  }

  public void setRadius (float radius)
  {
    mRadius = radius;
  }
}
