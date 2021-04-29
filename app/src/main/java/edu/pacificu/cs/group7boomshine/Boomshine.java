package edu.pacificu.cs.group7boomshine;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.ColorInt;
import edu.pacificu.cs.group7boomshine.circles.ExpandingCircle;
import edu.pacificu.cs.group7boomshine.circles.MovingCircle;

public class Boomshine
{
  private static final String TAG = "TAG";
  private final int MAX_ATTEMPTS = 3;

  private int mOverallScore;
  private int mLevel;
  private int mAttempt;
  private int mHitsNeeded;
  private int mHits;
  private boolean mbFired;

  private ArrayList<MovingCircle> maMovingCircles;
  private ArrayList<ExpandingCircle> maExpandingCircles;

  private int mNumMovingCircles;
  private int mNumExpandingCircles;

  public Boomshine ()
  {
    mOverallScore = 0;
    mLevel = 1;
    mAttempt = 1;
    mHitsNeeded = mLevel;
    mHits = 0;
    mbFired = false;

    mNumMovingCircles = 0;
    mNumExpandingCircles = 0;
  }

  public int getOverallScore ()
  {
    return mOverallScore;
  }

  public int getLevel ()
  {
    return mLevel;
  }

  public int getAttempt ()
  {
    return mAttempt;
  }

  public int getHitsNeeded ()
  {
    return mHitsNeeded;
  }

  public int getHits ()
  {
    return mHits;
  }

  public boolean userMadeCircle ()
  {
    return mbFired;
  }

  public void incrementLevel ()
  {
    mOverallScore += mHits;
    mLevel++;
    mAttempt = 1;
    mHitsNeeded = mLevel;
    mHits = 0;
    mbFired = false;
  }

  public boolean gameIsDone ()
  {
    boolean bGameIsDone = false;

    if (0 == mNumExpandingCircles && mbFired)
    {
      bGameIsDone = true;
    }

    return bGameIsDone;
  }

  public boolean incrementAttempt ()
  {
    if (MAX_ATTEMPTS > mAttempt)
    {
      mAttempt++;
      mHits = 0;
      mbFired = false;

      return true;
    }

    else
    {
      return false;
    }
  }

  public void initializeCircles ()
  {
    maMovingCircles = new ArrayList<> ();
    mNumMovingCircles = 0;
    maExpandingCircles = new ArrayList<> ();
    mNumExpandingCircles = 0;
  }

  public void createRandomMovingCircles (int xBoundary, int yBoundary)
  {
    final int MOVING_CIRCLE_RADIUS = 40;
    final int MAX_RGB = 255;
    final int MAX_SPEED = 30;
    
    Random random = new Random ();
    int xCoordinate;
    int yCoordinate;
    int randomColor;
    int xRate;
    int yRate;

    for (int i = 0; i < mLevel * 2; ++i)
    {
      xCoordinate = MOVING_CIRCLE_RADIUS + random.nextInt (xBoundary - MOVING_CIRCLE_RADIUS - MOVING_CIRCLE_RADIUS);
      yCoordinate = MOVING_CIRCLE_RADIUS + random.nextInt (yBoundary - MOVING_CIRCLE_RADIUS - MOVING_CIRCLE_RADIUS);
      randomColor = Color.argb (MAX_RGB, random.nextInt (MAX_RGB + 1), random.nextInt (MAX_RGB + 1), random.nextInt (MAX_RGB + 1));
      xRate = random.nextInt (MAX_SPEED);
      yRate = random.nextInt (MAX_SPEED);

      maMovingCircles.add (new MovingCircle (xCoordinate, yCoordinate, MOVING_CIRCLE_RADIUS, randomColor));
      maMovingCircles.get (i).setXRate (xRate);
      maMovingCircles.get (i).setYRate (yRate);
      mNumMovingCircles++;
    }
  }

  public void createUserExpandingCircle (float xCoordinate, float yCoordinate)
  {
    final float EXPANDING_CIRCLE_INITIAL_RADIUS = 10;
    final int COLOR = Color.BLUE;
    final float EXPANSION_RATE = 5;

    maExpandingCircles.add (new ExpandingCircle (xCoordinate, yCoordinate, EXPANDING_CIRCLE_INITIAL_RADIUS, COLOR, EXPANSION_RATE));
    mNumExpandingCircles++;
    mbFired = true;
  }

  public void iterateFrame ()
  {
    final float MAX_RADIUS = 200;
    final float CONTRACTION_RATE = -10;

    for (int i = 0; i < mNumMovingCircles; ++i)
    {
      maMovingCircles.get (i).move ();
    }

    for (int j = 0; j < mNumExpandingCircles; ++j)
    {
      maExpandingCircles.get (j).expand ();

      if (MAX_RADIUS <= maExpandingCircles.get (j).getRadius ())
      {
        maExpandingCircles.get (j).setExpansionRate (CONTRACTION_RATE);
      }

      if (maExpandingCircles.get (j).isContracted ())
      {
        maExpandingCircles.remove (j);
        mNumExpandingCircles--;
      }
    }
  }

  public void processCollisions ()
  {
    final float EXPANSION_RATE = 3;

    float xCoordinate;
    float yCoordinate;
    float initialRadius;
    int color;

    for (int j = 0; j < mNumExpandingCircles; ++j)
    {
      for (int i = 0; i < mNumMovingCircles; ++i)
      {
        if (maExpandingCircles.get (j).isCollided (maMovingCircles.get (i)))
        {
          xCoordinate = maMovingCircles.get (i).getXCoordinate ();
          yCoordinate = maMovingCircles.get (i).getYCoordinate ();
          initialRadius = maMovingCircles.get (i).getRadius ();
          color = maMovingCircles.get (i).getColor ();

          maExpandingCircles.add (new ExpandingCircle (xCoordinate, yCoordinate, initialRadius, color, EXPANSION_RATE));
          mNumExpandingCircles++;

          maMovingCircles.remove (i);
          mNumMovingCircles--;
          mHits++;
        }
      }
    }
  }

  public void processReflections (float xBoundary, float yBoundary)
  {
    for (int i = 0; i < mNumMovingCircles; ++i)
    {
      if (0 >= maMovingCircles.get (i).getXCoordinate () - maMovingCircles.get (i).getRadius ()
              || xBoundary <= maMovingCircles.get (i).getXCoordinate () + maMovingCircles.get (i).getRadius ())
      {
        maMovingCircles.get (i).reflectX ();
      }

      if (0 >= maMovingCircles.get (i).getYCoordinate () - maMovingCircles.get (i).getRadius ()
              || yBoundary <= maMovingCircles.get (i).getYCoordinate () + maMovingCircles.get (i).getRadius ())
      {
        maMovingCircles.get (i).reflectY ();
      }
    }
  }

  public ArrayList<MovingCircle> getMovingCircles ()
  {
    return maMovingCircles;
  }

  public ArrayList<ExpandingCircle> getExpandingCircles ()
  {
    return maExpandingCircles;
  }
}
