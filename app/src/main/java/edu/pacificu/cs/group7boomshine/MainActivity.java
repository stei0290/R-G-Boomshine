package edu.pacificu.cs.group7boomshine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * MainActivity - MainActivty clasds
 */
public class MainActivity extends AppCompatActivity
{
private BoomshineAndroidView mBoomshineAndroidView;

  /**
   * onCreate - Overridden function for onCreate
   * @param savedInstanceState
   */
  @Override
  protected void onCreate (Bundle savedInstanceState)
  {
    super.onCreate (savedInstanceState);
     mBoomshineAndroidView = new BoomshineAndroidView (this);
    setContentView (mBoomshineAndroidView);
  }

  /**
   * restartGame  - Restarts the game.
   */
  public void restartGame () {
    mBoomshineAndroidView = new BoomshineAndroidView ( this);
    setContentView (mBoomshineAndroidView);
  }
}