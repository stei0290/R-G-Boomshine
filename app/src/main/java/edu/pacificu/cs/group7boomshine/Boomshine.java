package edu.pacificu.cs.group7boomshine;

public class Boomshine
{
  private final int MAX_ATTEMPTS = 3;

  private int mNumCircles;
  private int mNumMovingCircles;
  private int mNumExpandingCircles;

  private int mOverallScore;
  private int mLevel;
  private int mAttempt;
  private int mHitsNeeded;
  private int mHits;

  public Boomshine ()
  {
    mOverallScore = 0;
    mLevel = 1;
    mAttempt = 1;
    mHitsNeeded = mLevel;
    mHits = 0;
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

  public void incrementAttempt ()
  {
    if (MAX_ATTEMPTS > mAttempt)
    {
      mAttempt++;
      mHits = 0;
    }
  }

  public void incrementHits ()
  {
    mHits++;
  }
}
