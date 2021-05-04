package edu.pacificu.cs.group7boomshine;

import android.os.CountDownTimer;

public class BoomshineTimer
{
  private final long MILLI = 1000;

  private long mSecondsRemaining;
  private boolean mbCountDownComplete;
  private CountDownTimer mCountDownTimer;

  /**
   * BoomshineTimer - Constructor  of timer for Boomshine game
   */
  public BoomshineTimer ()
  {
  }

  /**
   * starTimer  - Starts tiemr count down
   * @param duration  - Time to start at
   */
  public void startTimer (long duration)
  {
    mbCountDownComplete = false;

    mCountDownTimer = new CountDownTimer (duration, MILLI)
    {
      /**
       * onTick - Decrements time after a tick
       * @param millisecondsRemaining - Time remaining
       */
      @Override
      public void onTick (long millisecondsRemaining)
      {
        mSecondsRemaining = millisecondsRemaining / MILLI;
      }

      /**
       * onFinish - Handles logic of when timer is done
       */
      @Override
      public void onFinish ()
      {
        mSecondsRemaining = 0;
        mbCountDownComplete = true;
      }
    }.start ();
  }

  /**
   * stopTimer  - Stops timer
   */
  public void stopTimer ()
  {
    mCountDownTimer.cancel ();
  }

  /**
   * getSeconsRemainig  - Returns seconds remining
   * @return  mSecondsRemaining - Seconds left in timer
   */
  public final long getSecondsRemaining ()
  {
    return mSecondsRemaining;
  }

  /**
   * isCountDownComplete  - Determines if countdown is done
   * @return  mbCountDownComplete - True if timer is finished
   */
  public boolean isCountDownComplete ()
  {
    return mbCountDownComplete;
  }
}
