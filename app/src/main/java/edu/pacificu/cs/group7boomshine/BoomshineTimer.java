package edu.pacificu.cs.group7boomshine;

import android.os.CountDownTimer;

/**
 * BoomshineTimer   -   Timer implementation for Boomshine
 *
 * @author GarretKatayama
 */
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
     * starTimer  - Starts timer count down
     *
     * @param level - Time to start at
     */
    public void startTimer (int level)
    {
        final int MINUTE = 61;
        final int MULTIPLIER = 6;
        long duration;
        mbCountDownComplete = false;
        duration = (MINUTE - (MULTIPLIER * level)) * MILLI;
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
     * getSecondsRemaining  - Returns seconds remaining
     *
     * @return mSecondsRemaining - Seconds left in timer
     */
    public final long getSecondsRemaining ()
    {
        return mSecondsRemaining;
    }

    /**
     * isCountDownComplete  - Determines if countdown is done
     *
     * @return mbCountDownComplete - True if timer is finished
     */
    public boolean isCountDownComplete ()
    {
        return mbCountDownComplete;
    }
}
