package edu.pacificu.cs.group7boomshine;

import android.os.CountDownTimer;

public class BoomshineTimer
{
  private final long COUNT_DOWN_DURATION = 30000;
  private final long MILLI = 1000;

  private long mSecondsRemaining;
  private boolean mbCountDownComplete;
  private CountDownTimer mCountDownTimer;

  public BoomshineTimer ()
  {

  }

  public void startTimer ()
  {
    mbCountDownComplete = false;

    mCountDownTimer = new CountDownTimer (COUNT_DOWN_DURATION, MILLI)
    {
      @Override
      public void onTick (long millisecondsRemaining)
      {
        mSecondsRemaining = millisecondsRemaining / 1000;
      }

      @Override
      public void onFinish ()
      {
        mSecondsRemaining = 0;
        mbCountDownComplete = true;
      }
    }.start ();
  }

  public void stopTimer ()
  {
    mCountDownTimer.cancel ();
  }

  public long getSecondsRemaining ()
  {
    return mSecondsRemaining;
  }

  public boolean isCountDownComplete ()
  {
    return mbCountDownComplete;
  }
}
