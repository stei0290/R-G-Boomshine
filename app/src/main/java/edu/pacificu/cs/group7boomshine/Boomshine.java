package edu.pacificu.cs.group7boomshine;

import android.graphics.Color;

import java.util.Random;

import edu.pacificu.cs.group7boomshine.circles.Circle;
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

  private MovingCircle maMovingCircles[];
  private ExpandingCircle maExpandingCircles[];

  private int mNumMovingCircles;
  private int mNumExpandingCircles;

  public Boomshine ()
  {
    mOverallScore = 0;
    mLevel = 1;
    mAttempt = 1;
    mHitsNeeded = mLevel;
    mHits = 0;

    mNumMovingCircles = mLevel * 2;
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

  private void initializeCircles ()
  {
    maMovingCircles = new MovingCircle[mNumMovingCircles];
    maExpandingCircles = new ExpandingCircle[mNumMovingCircles + 1];
  }

  private void createRandomMovingCircles (int xBoundary, int yBoundary)
  {
    final int MOVING_CIRCLE_RADIUS = 15;
    
    Random random = new Random (0);
    Color color = new Color ();
    int xCoordinate;
    int yCoordinate;

    for (int i = 0; i < mNumMovingCircles; ++i)
    {
      xCoordinate = random.nextInt (xBoundary);
      yCoordinate = random.nextInt (yBoundary);

      maMovingCircles[i] = new MovingCircle (xCoordinate, yCoordinate, MOVING_CIRCLE_RADIUS, color);
    }
  }

  private void createUserExpandingCircle (float xCoordinate, float yCoordinate)
  {
    final int EXPANDING_CIRCLE_INITIAL_RADIUS = 10;
    final int EXPANSION_RATE = 5;

    Color color = new Color ();

    maExpandingCircles[0] = new ExpandingCircle (xCoordinate, yCoordinate, EXPANDING_CIRCLE_INITIAL_RADIUS, color, EXPANSION_RATE);
    mNumExpandingCircles = 1;
  }

  private void iterateFrame ()
  {
    for (int i = 0; i < mNumMovingCircles; ++i)
    {
      maMovingCircles[i].move ();
    }

    for (int j = 0; j < mNumExpandingCircles; ++j)
    {
      maExpandingCircles[j].expand ();
    }
  }
}
