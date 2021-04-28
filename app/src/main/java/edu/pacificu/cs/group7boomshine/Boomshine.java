package edu.pacificu.cs.group7boomshine;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

import edu.pacificu.cs.group7boomshine.circles.ExpandingCircle;
import edu.pacificu.cs.group7boomshine.circles.MovingCircle;

public class Boomshine
{
  private final int MAX_ATTEMPTS = 3;

  private int mOverallScore;
  private int mLevel;
  private int mAttempt;
  private int mHitsNeeded;
  private int mHits;

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

  public void incrementLevel ()
  {
    mOverallScore += mHits;
    mLevel++;
    mAttempt = 1;
    mHitsNeeded = mLevel;
    mHits = 0;
  }

  public boolean gameIsDone ()
  {
    boolean bGameIsDone = false;

    if (0 == mNumExpandingCircles)
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

      return true;
    }

    else
    {
      return false;
    }
  }

  public void incrementHits ()
  {
    mHits++;
  }

  public void initializeCircles ()
  {
    maMovingCircles = new ArrayList<> ();
    maExpandingCircles = new ArrayList<> ();
  }

  public void createRandomMovingCircles (int xBoundary, int yBoundary)
  {
    final int MOVING_CIRCLE_RADIUS = 15;
    
    Random random = new Random (0);
    Color color = new Color ();
    int xCoordinate;
    int yCoordinate;

    for (int i = 0; i < mLevel * 2; ++i)
    {
      xCoordinate = random.nextInt (xBoundary);
      yCoordinate = random.nextInt (yBoundary);

      maMovingCircles.add (new MovingCircle (xCoordinate, yCoordinate, MOVING_CIRCLE_RADIUS, color));
      mNumMovingCircles++;
    }
  }

  public void createUserExpandingCircle (float xCoordinate, float yCoordinate)
  {
    final float EXPANDING_CIRCLE_INITIAL_RADIUS = 10;
    final float EXPANSION_RATE = 5;

    Color color = new Color ();

    maExpandingCircles.add (new ExpandingCircle (xCoordinate, yCoordinate, EXPANDING_CIRCLE_INITIAL_RADIUS, color, EXPANSION_RATE));
    mNumExpandingCircles++;
  }

  public void iterateFrame ()
  {
    final float MAX_RADIUS = 50;
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
    final float EXPANSION_RATE = 5;

    float xCoordinate;
    float yCoordinate;
    float initialRadius;
    Color color;

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
        }
      }
    }
  }
}
