package edu.pacificu.cs.group7boomshine;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

import edu.pacificu.cs.group7boomshine.circles.ExpandingCircle;
import edu.pacificu.cs.group7boomshine.circles.MovingCircle;

/**
 * Boomshine Class
 */
public class Boomshine
{
  private final int MAX_ATTEMPTS = 3;

  private int mOverallScore;
  private int mLevel;
  private int mAttempt;
  private int mHitsNeeded;
  private int mHits;
  private boolean mbFired;
  private Random mRandom;

  private ArrayList<MovingCircle> maMovingCircles;
  private ArrayList<ExpandingCircle> maExpandingCircles;

  private int mNumMovingCircles;
  private int mNumExpandingCircles;


  /**
   * Constructor for Boomshine
   */
  public Boomshine ()
  {
    final int ONE = 1;
    mOverallScore = 0;
    mLevel = ONE;
    mAttempt = ONE;
    mHitsNeeded = mLevel;
    mHits = 0;
    mbFired = false;
    mRandom = new Random ();
    mNumMovingCircles = 0;
    mNumExpandingCircles = 0;
  }

  /**
   * Overridden constructor for Boomshine
   * @param seed  - Seed to use to generate randomness
   */
  public Boomshine (int seed)
  {
    final int ONE = 1;
    mOverallScore = 0;
    mLevel = ONE;
    mAttempt = ONE;
    mHitsNeeded = mLevel;
    mHits = 0;
    mbFired = false;
    mRandom = new Random (seed);
    mNumMovingCircles = 0;
    mNumExpandingCircles = 0;
  }

  /**
   * getOverallScore
   * @return mOverallScore  - Score of game
   */
  public int getOverallScore ()
  {
    return mOverallScore;
  }

  /**
   * getLevel - Gets current level of game;
   * @return  - mLevel
   */
  public final int getLevel ()
  {
    return mLevel;
  }

  /**
   * getAttemptsRemaining - Gets attempts user has left to pass level
   * @return  - Number of attempts user has
   */
  public final int getAttemptsRemaining ()
  {
    final int ONE = 1;
    int attempts;
    attempts = MAX_ATTEMPTS - mAttempt + ONE;
    return attempts;
  }

  /**
   * getHitsNeeded  - Returns number of hits needed to pass level
   * @return  mHitsNeeded - Number of hits needed to pass level
   */
  public final int getHitsNeeded ()
  {
    return mHitsNeeded;
  }

  /**
   * getHits  - Returns current hits
   * @return  mHits - Curent hits user has obtained
   */
  public final int getHits ()
  {
    return mHits;
  }

  /**
   * userMadeCircle - Keeps track if user has placed their circle
   * @return !mbFired  - True if user has placed their circle
   */
  public boolean userMadeCircle ()
  {
    return !mbFired;
  }

  /**
   * incrementLevel - Increments level
   */
  public void incrementLevel ()
  {
    mOverallScore += mHits;
    mLevel++;
    mAttempt = 1;
    mHitsNeeded = mLevel;
    mHits = 0;
    mbFired = false;
  }

  /**
   * gameIsDone - Determines if game is done
   * @return true if user has made a circle and there  are no
   *          expanding circles left
   */
  public boolean gameIsDone ()
  {
    boolean bGameIsDone = false;

    if (0 == mNumExpandingCircles && mbFired)
    {
      bGameIsDone = true;
    }

    return bGameIsDone;
  }

  /**
   * incrementAttempt - Increments the attempts user has made
   * @return  true  - if user has not exceeded max attempts
   */
  public boolean incrementAttempt ()
  {
    final int ONE = 1;
    if (MAX_ATTEMPTS > mAttempt)
    {
      mAttempt++;
      mHits = 0;
      mbFired = false;

      return true;
    }

    else
    {
      mAttempt = MAX_ATTEMPTS + ONE;
      return false;
    }
  }

  /**
   * initializeCircles  - Initializes array of circles
   */
  public void initializeCircles ()
  {
    maMovingCircles = new ArrayList<> ();
    mNumMovingCircles = 0;
    maExpandingCircles = new ArrayList<> ();
    mNumExpandingCircles = 0;
  }

  /**
   * createRandomMovingCircles  - Creates moving circles
   * @param xBoundary - X axis boundary
   * @param yBoundary - Y axis boundary
   */
  public void createRandomMovingCircles (int xBoundary, int yBoundary)
  {
    final int ONE = 1;
    final int TWO = 2;
    final int MOVING_CIRCLE_RADIUS = 40;
    final int MAX_RGB = 255;
    final int MAX_SPEED = 25;
    int numCircles;
    int xCoordinate;
    int yCoordinate;
    int randomColor;
    int xRate;
    int yRate;

    numCircles= mLevel * TWO;
    for (int i = 0; i < numCircles; ++i)
    {
      xCoordinate = MOVING_CIRCLE_RADIUS + mRandom.nextInt
              (xBoundary - MOVING_CIRCLE_RADIUS - MOVING_CIRCLE_RADIUS);
      yCoordinate = MOVING_CIRCLE_RADIUS + mRandom.nextInt
              (yBoundary - MOVING_CIRCLE_RADIUS - MOVING_CIRCLE_RADIUS);
      randomColor = Color.argb (MAX_RGB, mRandom.nextInt
              (MAX_RGB + ONE), mRandom.nextInt
              (MAX_RGB + ONE), mRandom.nextInt (MAX_RGB + ONE));
      do {
        xRate = (mRandom.nextInt (MAX_SPEED)) / mLevel;
      }  while (xRate == 0);
      do {
        yRate = (mRandom.nextInt (MAX_SPEED)) / mLevel;
      }while (yRate == 0);

      maMovingCircles.add (new MovingCircle
              (xCoordinate, yCoordinate, MOVING_CIRCLE_RADIUS, randomColor));
      maMovingCircles.get (i).setXRate (xRate);
      maMovingCircles.get (i).setYRate (yRate);
      mNumMovingCircles++;
    }
  }

  /**
   * createUserExpandingCircle  - Creates users circle
   * @param xCoordinate - X coordinate for circle
   * @param yCoordinate - Y coordinate for circle
   */
  public void createUserExpandingCircle (float xCoordinate, float yCoordinate)
  {
    final float EXPANDING_CIRCLE_INITIAL_RADIUS = 10;
    final int COLOR = Color.BLUE;
    final float EXPANSION_RATE = 5;
    final float multiplier = (float) 0.2;
    float expansionRate;
    expansionRate = EXPANSION_RATE * mLevel * multiplier;
    maExpandingCircles.add (new ExpandingCircle (xCoordinate,
            yCoordinate, EXPANDING_CIRCLE_INITIAL_RADIUS, COLOR, expansionRate));
    mNumExpandingCircles++;
    mbFired = true;
  }

  /**
   * iterateFrame - Moves circles for a frame
   */
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

  /**
   * processCollision - Handles logic for when circle collide
   */
  public void processCollisions ()
  {
    final float RATE_MULTIPLIER = (float) 0.3;
    final float MULTIPLIER = (float) 5;
    float expansionRate;
    float xCoordinate;
    float yCoordinate;
    float initialRadius;
    int color;
    expansionRate = mLevel * RATE_MULTIPLIER * MULTIPLIER;
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

          maExpandingCircles.add (new ExpandingCircle (xCoordinate, yCoordinate,
                  initialRadius, color, expansionRate));
          mNumExpandingCircles++;

          maMovingCircles.remove (i);
          mNumMovingCircles--;
          mHits++;
        }
      }
    }
  }

  /**
   * processReflections - Handles logic for reflecting a MovingCircle
   * @param xBoundary - xBoundary of screen
   * @param yBoundary - yBoundary of screen
   */
  public void processReflections (float xBoundary, float yBoundary)
  {
    for (int i = 0; i < mNumMovingCircles; ++i)
    {
      if (0 >= maMovingCircles.get (i).getXCoordinate ()
              - maMovingCircles.get (i).getRadius ()
              || xBoundary <= maMovingCircles.get (i).getXCoordinate ()
              + maMovingCircles.get (i).getRadius ())
      {
        maMovingCircles.get (i).reflectX ();
      }

      if (0 >= maMovingCircles.get (i).getYCoordinate ()
              - maMovingCircles.get (i).getRadius ()
              || yBoundary <= maMovingCircles.get (i).getYCoordinate ()
              + maMovingCircles.get (i).getRadius ())
      {
        maMovingCircles.get (i).reflectY ();
      }
    }
  }

  /**
   * getMovingCirlces - Gets array of MovingCircles
   * @return  maMovingCircles - Array list of MovingCircles
   */
  public final ArrayList<MovingCircle> getMovingCircles ()
  {
    return maMovingCircles;
  }

  /**
   * getExpandingCircles  - Gets array of ExpandingCircles
   * @return  maExpandingCircles  - Array list of ExpandingCircles
   */
  public final ArrayList<ExpandingCircle> getExpandingCircles ()
  {
    return maExpandingCircles;
  }
}
