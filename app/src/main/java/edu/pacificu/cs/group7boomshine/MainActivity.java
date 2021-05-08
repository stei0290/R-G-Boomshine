package edu.pacificu.cs.group7boomshine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * MainActivity - MainActivity class
 *
 * @author RomanStein
 */
public class MainActivity extends AppCompatActivity
{
    private BoomshineAndroidView mBoomshineAndroidView;

    /**
     * onCreate - Overridden function for onCreate
     *
     * @param savedInstanceState - Saved state of activity
     */
    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        mBoomshineAndroidView = new BoomshineAndroidView (this);
        setContentView (mBoomshineAndroidView);
    }

    /**
     * restartGame  - Restarts the game
     */
    public void restartGame ()
    {
        mBoomshineAndroidView = new BoomshineAndroidView (this);
        setContentView (mBoomshineAndroidView);
    }
}